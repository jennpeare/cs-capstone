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
			String prof = "";
			
			// sort each MeetingTime based on LECTURE or RECITATIONS
			for (Course c: courses) {
				for (Section s: c.sections) {
					if (s.instructors.length != 0) {
						prof = s.instructors[0].name;
					}
					for (MeetingTime m: s.meetingTimes) {
						if (m.meetingDay == null) {
							continue;
						}
						
						String key = m.meetingDay + m.startTime + m.endTime + m.pmCode + prof;
						CourseCondensed cc = new CourseCondensed(c, s, m);
						sortCourses(lectures, recitations, cc, key);
					}
				}
			}
			
			for (CourseCondensed cc : lectures.values()) {
				System.out.println(cc.course.title + " " + cc.course.courseNumber + " " 
						+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
						cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime);
			}
		
			// assign ideal room to lectures/recitations based on class size and room capacity
			assignRoom(sortedClassrooms, lectures);
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
			HashMap<String, CourseCondensed> courseList) {
		
		for (CourseCondensed cc: courseList.values()) {
			int targetCapacity = cc.section.stopPoint;
			MeetingTime mt = cc.section.meetingTimes[0];
			if (mt.meetingDay == null)
				continue;
			String key = mt.meetingDay + mt.startTime + mt.endTime + mt.pmCode;
			Classroom cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
			while (cr.booked.containsKey(key)) {
				targetCapacity++;
				cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
			}
			cr.booked.put(key, true);
			
			System.out.println(cc.course.title + " " + cc.course.courseNumber + " " + 
					cc.section.stopPoint + " " + cr.building + " " + cr.room + " " + cr.capacity);
		}
	}

	/**
	 * @param lectures
	 * @param recitations
	 * @param cc
	 * @param key
	 */
	private static void sortCourses(HashMap<String, CourseCondensed> lectures, 
			HashMap<String, CourseCondensed> recitations, CourseCondensed cc, String key) {
		
//		System.out.println("sortCourses: " + cc.course.title + " " + cc.course.courseNumber + " " 
//				+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
//				cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime);
		if (cc.meetingTime.meetingModeCode.equals("02")) { // LEC
			if (!lectures.containsKey(key)) {
				lectures.put(key, cc);
			}
		} else if (cc.meetingTime.meetingModeCode.equals("03")) { // RECIT
			if (!recitations.containsKey(key)) {
				recitations.put(key, cc);
			}
		}
	}
}
