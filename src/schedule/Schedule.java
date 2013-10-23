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
					System.out.println(c.title + " " + classrooms.floorKey(s.stopPoint));
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

	
}
