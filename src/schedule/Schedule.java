package schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;

import schedule.campus.*;

import com.google.common.collect.ImmutableMap;
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
		/*
		"data/92013/U/001.json",
		"data/92013/U/010.json",
		"data/92013/U/011.json",
		"data/92013/U/013.json",
		"data/92013/U/014.json",
		"data/92013/U/015.json",
		"data/92013/U/016.json",
		"data/92013/U/050.json",
		"data/92013/U/067.json",
		"data/92013/U/070.json",
		"data/92013/U/078.json",
		"data/92013/U/080.json",
		"data/92013/U/081.json",
		"data/92013/U/082.json",
		"data/92013/U/090.json",
		"data/92013/U/098.json",
		"data/92013/U/105.json",
		"data/92013/U/115.json",
		"data/92013/U/117.json",
		"data/92013/U/119.json",
		"data/92013/U/125.json",
		"data/92013/U/126.json",
		"data/92013/U/136.json",
		"data/92013/U/140.json",
		"data/92013/U/146.json",
		"data/92013/U/155.json",
		"data/92013/U/158.json",
		"data/92013/U/160.json",
		"data/92013/U/165.json",
		"data/92013/U/170.json",
		"data/92013/U/175.json",
		"data/92013/U/180.json",
		"data/92013/U/185.json",
		"data/92013/U/189.json",
		"data/92013/U/190.json",
		"data/92013/U/192.json",
		"data/92013/U/195.json",
		"data/92013/U/198.json",
		"data/92013/U/202.json",
		"data/92013/U/203.json",
		"data/92013/U/206.json",
		"data/92013/U/207.json",
		"data/92013/U/214.json",
		"data/92013/U/220.json",
		"data/92013/U/300.json",
		"data/92013/U/332.json",
		"data/92013/U/350.json",
		"data/92013/U/351.json",
		"data/92013/U/353.json",
		"data/92013/U/354.json",
		"data/92013/U/355.json",
		"data/92013/U/356.json",
		"data/92013/U/360.json",
		"data/92013/U/370.json",
		"data/92013/U/372.json",
		"data/92013/U/373.json",
		"data/92013/U/374.json",
		"data/92013/U/375.json",
		"data/92013/U/377.json",
		"data/92013/U/382.json",
		"data/92013/U/390.json",
		"data/92013/U/400.json",
		"data/92013/U/420.json",
		"data/92013/U/440.json",
		"data/92013/U/447.json",
		"data/92013/U/450.json",
		"data/92013/U/460.json",
		"data/92013/U/470.json",
		"data/92013/U/489.json",
		"data/92013/U/490.json",
		"data/92013/U/506.json",
		"data/92013/U/508.json",
		"data/92013/U/510.json",
		"data/92013/U/512.json",
		"data/92013/U/522.json",
		"data/92013/U/533.json",
		"data/92013/U/540.json",
		"data/92013/U/547.json",
		"data/92013/U/550.json",
		"data/92013/U/554.json",
		"data/92013/U/556.json",
		"data/92013/U/560.json",
		"data/92013/U/563.json",
		"data/92013/U/565.json",
		"data/92013/U/567.json",
		"data/92013/U/574.json",
		"data/92013/U/575.json",
		"data/92013/U/580.json",
		"data/92013/U/590.json",
		"data/92013/U/595.json",
		"data/92013/U/606.json",
		"data/92013/U/607.json",
		"data/92013/U/615.json",
		"data/92013/U/620.json",
		"data/92013/U/628.json",
		"data/92013/U/630.json",
		"data/92013/U/632.json",
		"data/92013/U/635.json",
		"data/92013/U/640.json",
		"data/92013/U/650.json",
		"data/92013/U/660.json",
		"data/92013/U/667.json",
		"data/92013/U/670.json",
		"data/92013/U/680.json",
		"data/92013/U/685.json",
		"data/92013/U/690.json",
		"data/92013/U/691.json",
		"data/92013/U/692.json",
		"data/92013/U/694.json",
		"data/92013/U/700.json",
		"data/92013/U/701.json",
		"data/92013/U/704.json",
		"data/92013/U/705.json",
		"data/92013/U/709.json",
		"data/92013/U/711.json",
		"data/92013/U/713.json",
		"data/92013/U/715.json",
		"data/92013/U/718.json",
		"data/92013/U/720.json",
		"data/92013/U/721.json",
		"data/92013/U/725.json",
		"data/92013/U/730.json",
		"data/92013/U/745.json",
		"data/92013/U/750.json",
		"data/92013/U/762.json",
		"data/92013/U/776.json",
		"data/92013/U/787.json",
		"data/92013/U/790.json",
		"data/92013/U/799.json",
		"data/92013/U/810.json",
		"data/92013/U/830.json",
		"data/92013/U/832.json",
		"data/92013/U/833.json",
		"data/92013/U/840.json",
		"data/92013/U/860.json",
		"data/92013/U/880.json",
		"data/92013/U/888.json",
		"data/92013/U/902.json",
		"data/92013/U/904.json",
		"data/92013/U/910.json",
		"data/92013/U/920.json",
		"data/92013/U/940.json",
		"data/92013/U/959.json",
		"data/92013/U/960.json",
		"data/92013/U/965.json",
		"data/92013/U/966.json",
		"data/92013/U/988.json",
		*/
		"data/92013/U/640.json",
		"data/92013/U/198.json",
		"data/92013/U/160.json"
	};
	private static final String capacityFile = "data/roomcapacity.json";
	private static final Map<String, Campus> campus = ImmutableMap.of(
			"COLLEGE AVENUE", new CollegeAve(),
			"BUSCH", new Busch(),
			"LIVINGSTON", new Livingston(),
			"DOUGLAS/COOK", new CookDouglass()
	);
	
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
			
			System.out.println("==========LECTURES==========");
			for (CourseCondensed cc : lectures.values()) {
				System.out.println(cc);
			}
			System.out.println("\n==========RECITATIONS==========");
			for (CourseCondensed cc : recitations.values()) {
				System.out.println(cc);
			}
			
			// assign ideal room to lectures/recitations based on class size and room capacity
			HashMap<CourseCondensed, Classroom> schedule = new HashMap<CourseCondensed, Classroom>();
			ArrayList<CourseCondensed> failed = new ArrayList<CourseCondensed>();
			System.out.println("\n===========ASSIGN LECTURES==========");

			assignRoom(sortedClassrooms, lectures, schedule, failed, "courseInc");
			
			System.out.println("\n===========ASSIGN RECITATIONS==========");
			assignRoom(sortedClassrooms, recitations, schedule, failed, "courseDec");
			
			/*
			for (CourseCondensed cc: schedule.keySet()) {
				System.out.println("Scheduled: " + cc.course.title + "\t" + cc.course.courseNumber + "\t" + 
						cc.section.stopPoint + " " + schedule.get(cc).building + " " + schedule.get(cc).room + " " + schedule.get(cc).capacity);
			}
			*/
			
			
			for (CourseCondensed cc: failed) {
				System.out.println("Failed: " + cc.course.title + "\t" + cc.course.courseNumber + "\t" + 
						cc.section.stopPoint);
			}
			System.out.println("Success: " + schedule.size());
			System.out.println("Failed: " + failed.size());
			
			
		} catch (IOException e) {
			System.out.println(e);
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
	 * @param sortedClassrooms List of classrooms in sorted order
	 * @param courseList List of courses to schedule
	 * @param schedule Schedule to return
	 * @param failed List of failed classes to return
	 * @param sortMode In what order should courses be assigned
	 */
	private static void assignRoom(TreeMap<Integer, Classroom> sortedClassrooms, 
			HashMap<String, CourseCondensed> courseList, HashMap<CourseCondensed, Classroom> schedule, 
			List<CourseCondensed> failed, String sortMode) throws IllegalStateException{
		
		// Set sorting order for room assignment
		CourseCondensed.setSortMode(sortMode);
		
		List<CourseCondensed> classes = new ArrayList<CourseCondensed>(courseList.values());
		Collections.sort(classes);
		for (CourseCondensed cc: classes) {
			int targetCapacity = cc.section.stopPoint;
			MeetingTime mt = cc.section.meetingTimes[0];
			if (mt.meetingDay == null)
				continue;			
			String key = mt.meetingDay + mt.startTime + mt.endTime + mt.pmCode;
			int startPeriod = campus.get(mt.campusName).getPeriod(mt.startTime, mt.pmCode), 
					endPeriod = campus.get(mt.campusName).getPeriod(mt.endTime, mt.pmCode);
			System.out.println(mt.campusName + " " + mt.startTime + " " + mt.endTime + " " + startPeriod +" "+ endPeriod);
			Classroom room = null;
			boolean scheduled = false;
			while ((sortedClassrooms.ceilingKey(targetCapacity) != null) && (startPeriod != -1) && (endPeriod != -1)) {
				room = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
				// TODO(wlynch): Figure out what to do for non-standard start/stop times
				if (room.bookRoom(mt.meetingDay, startPeriod, endPeriod)) {
					schedule.put(cc, room);
					scheduled = true;
					break;
				}
				targetCapacity++;
			}
			if (!scheduled) {
				failed.add(cc);
			}
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
