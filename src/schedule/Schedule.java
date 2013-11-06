package schedule;

import java.io.IOException;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.TreeMap;

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
			
			
			
			// Look for best rooms
			for (Course c: courses) {
				for (Section s: c.sections) {
					int targetCapacity = s.stopPoint;
					MeetingTime mt = s.meetingTimes[0];
					if (mt.meetingDay == null)
						continue;
					String key = mt.meetingDay + mt.startTime + mt.endTime + mt.pmCode;
					Classroom cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
					while (cr.booked.containsKey(key)) {
						targetCapacity++;
						cr = sortedClassrooms.get(sortedClassrooms.ceilingKey(targetCapacity));
					}
					cr.booked.put(key, true);
					
					System.out.println(c.title + " " + c.courseNumber + " " + s.stopPoint + 
							" " + cr.building + " " + cr.room + " " + cr.capacity);
				}
			}
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
	
	private static TreeMap<Integer,Classroom> buildTree(Classroom[] classrooms) {
		TreeMap<Integer, Classroom> map = new TreeMap<Integer,Classroom>();
		for (Classroom room: classrooms) {
			map.put(room.capacity, room);
		}
		return map;
	}
}
