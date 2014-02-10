package schedule;

import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Schedule {

	private static final String buildingFile = "data/buildings.json"; 
		private static final String[] courseFiles = {
			"data/92013/U/640.json",
			"data/92013/U/198.json",
			"data/92013/U/160.json"
		};

	private static final String capacityFile = "data/roomcapacity_busch.json";

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Gson gson = new Gson();
		try {
			// Read in buildings
			String json = readFile(buildingFile);
			Type collectionType = new TypeToken<Building[]>(){}.getType();
			Building[] buildings = gson.fromJson(json, collectionType);
			System.out.println("Loaded buildings");

			// Read in courses
			ArrayList<Course> courses = new ArrayList<Course>();
			for (String file: courseFiles) {
				courses.addAll(importCourses(gson, file));
			}
			System.out.println("Loaded courses");

			// Read in classrooms
			json = readFile(capacityFile);
			collectionType = new TypeToken<Classroom[]>(){}.getType();
			Classroom[] classrooms = gson.fromJson(json, collectionType);
			TreeMap<Integer, Classroom> sortedClassrooms = buildTree(classrooms);
			System.out.println("Loaded classrooms");

			// HashMaps to store LEC and RECIT
			HashMap<String, CourseCondensed> lectures = new HashMap<String, CourseCondensed>();
			HashMap<String, CourseCondensed> recitations = new HashMap<String, CourseCondensed>();

			separateCourses(courses, lectures, recitations);

//			System.out.println("\n==========LECTURES==========");
//
//			for (CourseCondensed cc : lectures.values()) {
//				System.out.println(cc.course.title + " " + cc.course.courseNumber + " " 
//						+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
//						cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime + " " +
//						cc.section.stopPoint);
//			}
//
//			System.out.println("\n==========RECITATIONS==========");
//
//			for (CourseCondensed cc : recitations.values()) {
//				System.out.println(cc.course.title + " " + cc.course.courseNumber + " " 
//						+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
//						cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime + " " +
//						cc.section.stopPoint);
//			}

			// assign ideal room to lectures/recitations based on class size and room capacity
			System.out.println("\n===========ASSIGN LECTURES==========");
			assignRoom(sortedClassrooms, lectures);

			System.out.println("\n===========ASSIGN RECITATIONS==========");
			assignRoom(sortedClassrooms, recitations);

		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}

	/**
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	private static String readFile(String pathname) throws IOException {
		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while(scanner.hasNextLine()) {        
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}

	private static ArrayList<Course> importCourses(Gson gson, String filename) throws IOException {
		return gson.fromJson(readFile(filename), new TypeToken<ArrayList<Course>>(){}.getType());
	}

	/**
	 * @param classrooms
	 * @return
	 */
	private static TreeMap<Integer,Classroom> buildTree(Classroom[] classrooms) {
		TreeMap<Integer, Classroom> map = new TreeMap<Integer,Classroom>();

		for (Classroom room: classrooms) {
			map.put(room.capacity, room);
		}
		return map;
	}

	/**
	 * @param sortedClassrooms
	 * @param courseList
	 */
	private static void assignRoom(TreeMap<Integer, Classroom> sortedClassrooms, 
			HashMap<String, CourseCondensed> courseList) throws IllegalStateException{

		for (CourseCondensed cc: courseList.values()) {
			int targetCapacity = cc.section.stopPoint;
			MeetingTime mt = cc.section.meetingTimes[0];
			if (mt.meetingDay == null)
				continue;
			String key = mt.meetingDay + mt.startTime + mt.endTime + mt.pmCode;
			Classroom cr = new Classroom();

			try {
				cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
			} catch(NullPointerException e){
				System.out.println(e.getMessage());
			}

			while (cr.booked.containsKey(key)) {
				targetCapacity++;
				if (sortedClassrooms.ceilingKey(targetCapacity) == null) {
					// We have run out of rooms. Oh no!
					throw new IllegalStateException();
				}
				cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
			}
			cr.booked.put(key, true);

			System.out.println(cc.course.title + " " + cc.course.courseNumber + " " + 
					cc.section.stopPoint + " " + cr.building + " " + cr.room + " " + cr.capacity);
		}
	}

	/**
	 * @param courses Array of course objects
	 * @param lectures HashMap storing unique lectures
	 * @param recitations HashMap storing unique recitations
	 */
	private static void separateCourses(ArrayList<Course> courses, HashMap<String, CourseCondensed> lectures, 
			HashMap<String, CourseCondensed> recitations) {

		String prof = "", key = "", recKey = "";

		// sort each MeetingTime based on LECTURE or RECITATIONS
		//System.out.println("\n==========SEPARATION==========");
		
		for (Course c: courses) {
			
			for (Section s: c.sections) {
				// copy of each Section s to preserve recitation stopPoint
				Section sr = new Section(s);
				
				if (s.instructors.length != 0) {
					prof = s.instructors[0].name;
				}

				for (MeetingTime m: s.meetingTimes) {
					
					// if no scheduled meeting time, skip;
					if (m.meetingDay == null) 
						continue;

					// hash key to distinguish unique lectures and recitations
					key = c.courseNumber + m.meetingDay + m.startTime + m.endTime + m.pmCode + prof;
					
					recKey = m.meetingDay + m.startTime + m.endTime + m.pmCode + m.buildingCode + 
							m.roomNumber + prof;
					
					// insert MeetingTime into appropriate hashmap
					if (m.meetingModeCode.equals(MeetingTime.lectureCode)) {
						if (!lectures.containsKey(key)) {
							lectures.put(key, new CourseCondensed(c, s, m));
						}
					} else if (m.meetingModeCode.equals(MeetingTime.recitationCode)) {
						if (!recitations.containsKey(recKey)) {
							recitations.put(recKey, new CourseCondensed(c, sr, m));
						}
					}
				}
			}
		}
		
		boolean first = false; // prevent double counting stopPoint of first sections
		
		// fix class size for lecture sections
		//System.out.println("\n==========AGGREGATION==========");
		for (Course c: courses) {
			
			first = true;
			
			for (Section s: c.sections) {
				
				if (s.instructors.length != 0) {
					prof = s.instructors[0].name;
					first = true;
				}
				
				for (MeetingTime m: s.meetingTimes) {
					
					// if no scheduled meeting time, skip;
					if (m.meetingDay == null) 
						continue;

					// hash key to distinguish unique lectures and recitations
					key = c.courseNumber + m.meetingDay + m.startTime + m.endTime + m.pmCode + prof;
					
					if (m.meetingModeCode.equals(MeetingTime.lectureCode)) {
						if (lectures.containsKey(key)) {
							
							// prevent double counting due to multiple lectures for same section
							if (first == true) {
								lectures.get(key).section.stopPoint = s.stopPoint;
								first = false;
							} else {
								lectures.get(key).section.stopPoint += s.stopPoint;
							}
							break;
						}
					}
				}
			}
		}
	}
}
