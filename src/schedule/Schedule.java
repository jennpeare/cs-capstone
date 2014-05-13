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
import java.util.logging.Level;
import java.util.logging.Logger;

import schedule.campus.*;
import analysis.Analysis;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Schedule is the main driver of the scheduling process. 
 * It handles the loading of various data files and holds the setting for 
 * various configs such as generic classrooms/size and scheduling options such as affinity/class distribution. 
 *  
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Schedule {

	/* CONFIGS */
	/** Should depts be bound by their affinities? **/
	private static final boolean AFFINITY_BOUND = false;
	
	/** Should intro classes be distributed between all campuses? **/
	private static final boolean DISTRIBUTE_INTRO = true;
	
	/** How many dummy rooms per dept? **/
	private static final int NUMBER_OF_GENERIC_ROOMS = 10;
	
	/** Capacity of each dummy room **/
	private static final int GENERIC_ROOM_CAPACITY = 40;
	
	/* DATA FILES */
	private static final String buildingFile = "data/buildings.json"; 
	private static final String deptAffinityFile = "data/dept_affinity.json";
	private static final String[] courseFiles = {
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
		/*
		"data/92013/U/640.json",
		"data/92013/U/198.json",
		"data/92013/U/160.json"
		*/
	};
	private static final String capacityFile = "data/roomcapacity.json";
	public static final Map<String, Campus> campus = ImmutableMap.of(
			"COLLEGE AVENUE", new CollegeAve(),
			"BUSCH", new Busch(),
			"LIVINGSTON", new Livingston(),
			"DOUGLAS/COOK", new CookDouglass(),
			"DOWNTOWN NB", new Downtown()
	);
	public static final Map<String, Campus> campusAbbrev = ImmutableMap.of(
			"CAC", campus.get("COLLEGE AVENUE"),
			"BUS", campus.get("BUSCH"),
			"LIV", campus.get("LIVINGSTON"),
			"D/C", campus.get("DOUGLAS/COOK"),
			"DNB", campus.get("DOWNTOWN NB")
	);
	private static Logger log;
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		log = Logger.getLogger("my.logger");
		log.setLevel(Level.OFF);
		
		Gson gson = new Gson();
		try {
			// Read in buildings
			String json = readFile(buildingFile);
			Type collectionType = new TypeToken<Building[]>(){}.getType();
			Building[] buildings = gson.fromJson(json, collectionType);
			log.info("Loaded buildings");

			// Read in courses
			ArrayList<Course> courses = new ArrayList<Course>();
			for (String file: courseFiles) {
				courses.addAll(importCourses(gson, file));
			}
			log.info("Loaded courses");

			// Read in classrooms
			json = readFile(capacityFile);
			collectionType = new TypeToken<Classroom[]>(){}.getType();
			Classroom[] classrooms = gson.fromJson(json, collectionType);
			System.out.println(classrooms.length);
			
			// Load campus affinities
			json =  readFile(deptAffinityFile);
			collectionType = new TypeToken<Affinity[]>(){}.getType();
			Affinity[] affinities = gson.fromJson(json, collectionType);
			HashMap<String, ArrayList<Classroom>> genericRooms = new HashMap<String, ArrayList<Classroom>>();
			for (Affinity a : affinities) {
				genericRooms.put(a.department, new ArrayList<Classroom>());
				for (int i = 0; i < NUMBER_OF_GENERIC_ROOMS; i++) {
					genericRooms.get(a.department).add(a.newClassroom(a.department + "generic" + i, GENERIC_ROOM_CAPACITY));
				}
			}
			System.out.println(genericRooms.size());
			
			TreeMap<Integer, ArrayList<Classroom>> sortedClassrooms = buildTree(classrooms);
			log.info("Loaded classrooms");
			
			// HashMaps to store LEC and RECIT
			HashMap<String, CourseCondensed> lectures = new HashMap<String, CourseCondensed>();
			HashMap<String, CourseCondensed> recitations = new HashMap<String, CourseCondensed>();

			separateCourses(courses, lectures, recitations);
			
			// assign ideal room to lectures/recitations based on class size and room capacity
			HashMap<CourseCondensed, Classroom> schedule = new HashMap<CourseCondensed, Classroom>();
			ArrayList<CourseCondensed> failed = new ArrayList<CourseCondensed>();
			log.fine("\n===========ASSIGN LECTURES==========");

			assignRoom(sortedClassrooms, genericRooms, lectures, schedule, failed, "courseInc");
			
			log.fine("\n===========ASSIGN RECITATIONS==========");
			assignRoom(sortedClassrooms, genericRooms, recitations, schedule, failed, "courseDec");
			
			
			for (CourseCondensed cc: schedule.keySet()) {
				log.fine("Scheduled: " + cc.course.title + "\t" + cc.course.courseNumber + "\t" + 
						cc.section.stopPoint + " " + schedule.get(cc).building + " " + schedule.get(cc).room + " " + schedule.get(cc).capacity);
			}
			
			if (AFFINITY_BOUND)
				System.out.println("AFFINTIY_BOUND enabled");
			if (DISTRIBUTE_INTRO)
				System.out.println("DISTRIBUTE_INTRO enabled");
			System.out.println("Number of generic rooms: " + NUMBER_OF_GENERIC_ROOMS);
			System.out.println("Generic room capacity: " + GENERIC_ROOM_CAPACITY);
			Analysis.analyze(schedule);
			
			for (CourseCondensed cc: failed) {
				log.warning("Failed: " + cc.course.title + "\t" + cc.course.courseNumber + "\t" + 
						cc.section.stopPoint);
			}
			System.out.println("Success: " + schedule.size());
			System.out.println("Failed: " + failed.size());
			
		} catch (IOException e) {
			log.severe(e.getMessage());
		}
	}

	/**
	 * Reads a file.
	 * @param pathname File to read
	 * @return String of file contents
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
	 * Imports courses from a json file
	 * @param gson GSON object to use for importing
	 * @param filename File to import
	 * @return List of courses
	 * @throws IOException
	 */
	private static ArrayList<Course> importCourses(Gson gson, String filename) throws IOException {
		return gson.fromJson(readFile(filename), new TypeToken<ArrayList<Course>>(){}.getType());
	}

	/**
	 * Build map of classrooms
	 * @param classrooms List of classrooms
	 * @return TreeMap of capacity, room lists
	 */
	private static TreeMap<Integer, ArrayList<Classroom>> buildTree(Classroom[] classrooms) {
		TreeMap<Integer, ArrayList<Classroom>> map = new TreeMap<Integer,ArrayList<Classroom>>();

		for (Classroom room: classrooms) {
			if (!map.containsKey(room.capacity))
				map.put(room.capacity, new ArrayList<Classroom>());
			map.get(room.capacity).add(room);
		}
		return map;
	}

	/**
	 * Assign classes to rooms
	 * @param sortedClassrooms List of classrooms in sorted order
	 * @param deptClassrooms List of department specific classrooms
	 * @param courseList List of courses to schedule
	 * @param schedule Schedule to return
	 * @param failed List of failed classes to return
	 * @param sortMode In what order should courses be assigned
	 */
	private static void assignRoom(TreeMap<Integer, ArrayList<Classroom>> sortedClassrooms, 
			HashMap<String, ArrayList<Classroom>> deptClassrooms, 
			HashMap<String, CourseCondensed> courseList,
			HashMap<CourseCondensed, Classroom> schedule,
			List<CourseCondensed> failed, String sortMode) throws IllegalStateException{
		
		// Set sorting order for room assignment
		CourseCondensed.setSortMode(sortMode);
		
		List<CourseCondensed> classes = new ArrayList<CourseCondensed>(courseList.values());
		Collections.sort(classes);
		for (CourseCondensed cc: classes) {
			int targetCapacity = cc.section.stopPoint;
			MeetingTime mt = cc.section.meetingTimes[0];
			// Check for things we don't need to schedule: Online/off campus
			if (mt.meetingDay == null || mt.campusName == null || mt.campusName.equals("OFF CAMPUS"))
				continue;			
			int startPeriod = campus.get(mt.campusName).getPeriod(mt.startTime, mt.pmCode), 
					endPeriod = campus.get(mt.campusName).getPeriod(mt.endTime, mt.pmCode);
			log.fine(mt.campusName + " " + mt.startTime + " " + mt.endTime + " " + startPeriod +" "+ endPeriod);
			boolean scheduled = false;
			
			if ((startPeriod == -1) || (endPeriod == -1)) {
				log.warning(cc + " | " + mt.startTime + " " + mt.endTime + " " + startPeriod + " " + endPeriod);
				continue;
			}
			while ((sortedClassrooms.ceilingKey(targetCapacity) != null) && (startPeriod != -1) && (endPeriod != -1)) {
				//scheduled = false;
				// Try to schedule in dept rooms first
				if (deptClassrooms.containsKey(cc.course.subject)) {
					for (Classroom room: deptClassrooms.get(cc.course.subject)) {
						if (room.bookRoom(mt.meetingDay, startPeriod, endPeriod)) {
							schedule.put(cc, room);
							campusAbbrev.get(room.campus).count++;
							scheduled = true;
							break;
						}
					}
				}
				
				if (!scheduled && DISTRIBUTE_INTRO && cc.course.courseNumber.startsWith("1")) {
					// Find min campus
					Campus[] c = campusAbbrev.values().toArray(new Campus[5]);
					int min = 0;
					// Find campus with least amount of classes
					for (int i = 1; i < c.length-1; i++) {
						// Let campus affinity take precedence
						if (c[i].count == c[min].count && c[min].getName().equals(deptClassrooms.get(cc.course.subject).get(0).campus)) {
							min = i;
						}
						if (c[i].count < c[min].count) {
							min = i;
						}
					}
					// Find best room with least used campus as affinity
					for (Classroom room: sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity))) {
						if (room.campus.equals(c[min].getName()) && room.bookRoom(mt.meetingDay, startPeriod, endPeriod)) {
							schedule.put(cc, room);
							campusAbbrev.get(room.campus).count++;
							scheduled = true;
							break;
						}
					}
				}
				
				// Search for rooms on the affinity campus
				if (!scheduled && AFFINITY_BOUND) {
					for (Classroom room: sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity))) {
						if (deptClassrooms.get(cc.course.subject) != null && 
								room.campus.equals(deptClassrooms.get(cc.course.subject).get(0).campus) && 
								room.bookRoom(mt.meetingDay, startPeriod, endPeriod)) {
							schedule.put(cc, room);
							campusAbbrev.get(room.campus).count++;
							scheduled = true;
							break;
						}
					}
				}
				
				// Else search elsewhere
				if (!scheduled){
					for (Classroom room: sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity))) {
						if (room.bookRoom(mt.meetingDay, startPeriod, endPeriod)) {
							schedule.put(cc, room);
							campusAbbrev.get(room.campus).count++;
							scheduled = true;
							break;
						}
					}
				}
				// Check to see if we need to check another room with higher capacity
				if (scheduled) {
					break;
				} else {
					targetCapacity++;
				}
			}
			// If rooms are exhausted, add to failed
			if (!scheduled) {
				failed.add(cc);
			}
		}
	}

	/**
	 * Parse out courses to lectures and recitations
	 * @param courses Array of course objects
	 * @param lectures HashMap storing unique lectures
	 * @param recitations HashMap storing unique recitations
	 */
	private static void separateCourses(ArrayList<Course> courses, HashMap<String, CourseCondensed> lectures, 
			HashMap<String, CourseCondensed> recitations) {

		String prof = "", key = "", recKey = "";

		// sort each MeetingTime based on LECTURE or RECITATIONS
		//log.fine("\n==========SEPARATION==========");
		
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
		//log.fine("\n==========AGGREGATION==========");
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
