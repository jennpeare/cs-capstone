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
 *
 */
public class Schedule {
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Gson gson = new Gson();
		try {
			String json = readFile(args[0]);
			Type collectionType = new TypeToken<Building[]>(){}.getType();
			Building[] buildings = gson.fromJson(json, collectionType);
			
			TreeMap<Integer, Classroom> classrooms = new TreeMap<Integer, Classroom>();
			buildTree(classrooms);
			
			json = readFile(args[1]);
			collectionType = new TypeToken<Course[]>(){}.getType();
			Course[] courses = gson.fromJson(json, collectionType);
			for (Course c: courses) {
				for (Section s: c.sections) {
					System.out.println(c.title + " " + c.courseNumber + " " 
							+ classrooms.get(classrooms.ceilingKey(s.stopPoint)).room);
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
	
	private static void buildTree(TreeMap<Integer, Classroom> tree) {
		
		// lecture hall
		tree.put(500, new Classroom("103", 500, true, false, "Lecture Hall"));
		tree.put(141, new Classroom("102", 141, true, true, "Lecture Hall"));
		tree.put(165, new Classroom("B120", 165, true, false, "Lecture Hall"));
		tree.put(314, new Classroom("114", 314, true, false, "Lecture Hall"));
		tree.put(105, new Classroom("116", 105, true, false, "Lecture Hall"));
		tree.put(144, new Classroom("111", 144, true, false, "Lecture Hall"));
		tree.put(144, new Classroom("115", 144, true, false, "Lecture Hall"));
		tree.put(300, new Classroom("LH", 300, true, false, "Lecture Hall"));
		tree.put(269, new Classroom("111", 269, true, false, "Lecture Hall"));
		tree.put(126, new Classroom("117", 126, true, false, "Lecture Hall"));
		tree.put(140, new Classroom("118", 140, true, false, "Lecture Hall"));
		tree.put(121, new Classroom("AUD", 121, true, false, "Lecture Hall"));
		tree.put(198, new Classroom("AUD", 198, true, true, "Lecture Hall"));
		
		// classroom
		tree.put(66, new Classroom("105", 66, true, false, "Classroom"));
		tree.put(70, new Classroom("107", 70, true, false, "Classroom"));
		tree.put(30, new Classroom("108", 30, true, false, "Classroom"));
		tree.put(30, new Classroom("110", 30, true, false, "Classroom"));
		tree.put(18, new Classroom("203 - Seminar Room", 18, true, false, "Classroom"));
		tree.put(42, new Classroom("204", 42, true, false, "Classroom"));
		tree.put(30, new Classroom("205", 30, true, false, "Classroom"));
		tree.put(40, new Classroom("206", 40, true, false, "Classroom"));
		tree.put(30, new Classroom("207", 30, true, false, "Classroom"));
		tree.put(12, new Classroom("212 - Seminar Room", 12, true, false, "Classroom"));
		tree.put(22, new Classroom("324", 22, true, true, "Classroom"));
		tree.put(22, new Classroom("326", 22, true, true, "Classroom"));
		tree.put(30, new Classroom("333", 30, true, false, "Classroom"));
		tree.put(65, new Classroom("116", 65, true, true, "Classroom"));
		tree.put(30, new Classroom("122", 30, true, true, "Classroom"));
		tree.put(32, new Classroom("126", 32, true, true, "Classroom"));
		tree.put(30, new Classroom("128", 30, true, true, "Classroom"));
		tree.put(49, new Classroom("009", 49, true, false, "Classroom"));
		tree.put(40, new Classroom("124", 40, true, true, "Classroom"));
		tree.put(30, new Classroom("254", 30, true, true, "Classroom"));
		tree.put(30, new Classroom("423", 30, true, true, "Classroom"));
		tree.put(24, new Classroom("425", 24, true, true, "Classroom"));
		tree.put(30, new Classroom("525", 30, true, true, "Classroom"));
		tree.put(45, new Classroom("552", 45, true, true, "Classroom"));
		tree.put(49, new Classroom("007", 49, true, true, "Classroom"));
		tree.put(20, new Classroom("307", 20, true, true, "Classroom"));
		tree.put(30, new Classroom("A317", 30, true, true, "Classroom"));
		tree.put(25, new Classroom("A340", 25, true, true, "Classroom"));
		tree.put(40, new Classroom("166", 40, true, true, "Classroom"));
		tree.put(60, new Classroom("202", 60, true, false, "Classroom"));
		tree.put(60, new Classroom("203", 60, true, false, "Classroom"));
		tree.put(38, new Classroom("204", 38, true, false, "Classroom"));
		tree.put(60, new Classroom("205", 60, true, false, "Classroom"));
		tree.put(38, new Classroom("206", 38, true, false, "Classroom"));
		tree.put(60, new Classroom("207", 60, true, false, "Classroom"));
		tree.put(70, new Classroom("208", 70, true, false, "Classroom"));
		tree.put(90, new Classroom("209", 90, true, false, "Classroom"));
		tree.put(90, new Classroom("210", 90, true, false, "Classroom"));
		tree.put(35, new Classroom("211", 35, true, false, "Classroom"));
		tree.put(45, new Classroom("212", 45, true, false, "Classroom"));
		tree.put(45, new Classroom("216", 45, true, false, "Classroom"));
		tree.put(45, new Classroom("217", 45, true, false, "Classroom"));
		tree.put(28, new Classroom("218", 28, true, false, "Classroom"));
		tree.put(36, new Classroom("220", 36, true, false, "Classroom"));
		tree.put(20, new Classroom("106", 20, true, true, "Classroom"));
		tree.put(30, new Classroom("232", 30, true, true, "Classroom"));
		tree.put(50, new Classroom("260 - Seminar Room", 50, true, true, "Classroom"));		
	}
}
