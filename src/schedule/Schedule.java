package schedule;

import java.io.IOException;

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
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Gson gson = new Gson();
		try {
			// Read in buildings
			String json = readFile(args[0]);
			Type collectionType = new TypeToken<Building[]>(){}.getType();
			Building[] buildings = gson.fromJson(json, collectionType);
			
			// Read in courses
			json = readFile(args[1]);
			collectionType = new TypeToken<Course[]>(){}.getType();
			Course[] courses = gson.fromJson(json, collectionType);
			
			// Read in classrooms
			json = readFile(args[2]);
			collectionType = new TypeToken<Classroom[]>(){}.getType();
			Classroom[] classrooms = gson.fromJson(json, collectionType);
			TreeMap<Integer, Classroom> sortedClassrooms = buildTree(classrooms);
				
			// HashMaps to store LEC and RECIT
			HashMap<String, CourseCondensed> lectures = new HashMap<String, CourseCondensed>();
			HashMap<String, CourseCondensed> recitations = new HashMap<String, CourseCondensed>();
			
			separateCourses(courses, lectures, recitations);
			
			System.out.println("==========LECTURES==========");
			
			for (CourseCondensed cc : lectures.values()) {
				System.out.println(cc.course.title + " " + cc.course.courseNumber + " " 
						+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
						cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime + " " +
						cc.section.stopPoint);
			}
		
			System.out.println("\n==========RECITATIONS==========");
			
			for (CourseCondensed cc : recitations.values()) {
				System.out.println(cc.course.title + " " + cc.course.courseNumber + " " 
						+ cc.section.number + " " + cc.meetingTime.meetingModeDesc + " " + 
						cc.meetingTime.meetingDay + " " + cc.meetingTime.startTime + " " +
						cc.section.stopPoint);
			}
			
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
				//System.out.println(cc.course.title + " " + targetCapacity);
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
	private static void separateCourses(Course[] courses, HashMap<String, CourseCondensed> lectures, 
			HashMap<String, CourseCondensed> recitations) {
		
		String prof = "", key = "", ptr = "";
		int classSize = 0;
		
		// sort each MeetingTime based on LECTURE or RECITATIONS
		for (Course c: courses) {
			
			for (Section s: c.sections) {
				
				// copy of each Section s to preserve recitation stopPoint
				Section sr = new Section(s);
				
				if (s.instructors.length != 0) {
					prof = s.instructors[0].name;

					if (lectures.containsKey(ptr)) {
//						System.out.println("currentKey: " + key + " prevKey: " + ptr);
						if (! lectures.get(ptr).section.editedCap) {
							lectures.get(ptr).section.stopPoint = classSize;
//							lectures.get(ptr).section.editedCap = true;
//						} else {
//							int prevCap = lectures.get(ptr).section.stopPoint;
//							System.out.println(lectures.get(ptr).course.title + " " + prevCap);
//							lectures.get(ptr).section.stopPoint = prevCap + classSize;
//							System.out.println(lectures.get(ptr).section.stopPoint);
						}
					}
					classSize = s.stopPoint;
				} else {
					classSize += s.stopPoint;
				}
				
				for (MeetingTime m: s.meetingTimes) {
					
					// if no scheduled meeting time, skip;
					if (m.meetingDay == null) 
						continue;
					
					// hash key to distinguish unique lectures and recitations
					key = m.meetingDay + m.startTime + m.endTime + m.pmCode + m.buildingCode + 
							m.roomNumber + prof;
					
					// insert MeetingTime into appropriate hashmap
					if (m.meetingModeCode.equals(MeetingTime.lectureCode)) {
						if (!lectures.containsKey(key)) {
							ptr = key; // keep track of last lecture in hashmap
							//System.out.println(ptr);
							lectures.put(key, new CourseCondensed(c, s, m));
						}
					} else if (m.meetingModeCode.equals(MeetingTime.recitationCode)) {
						if (!recitations.containsKey(key)) {
							recitations.put(key, new CourseCondensed(c, sr, m));
						}
					}
				}
			}
		}		
	}
}