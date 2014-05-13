package analysis;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import schedule.Classroom;
import schedule.CourseCondensed;

/**
 * Analysis handles the analysis of the scheduling results.
 * It prints out various stats about each course and where it was scheduled.
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Analysis {
	private static int[] campusTotalFreq = new int[6];
	private static class Stats {
		int[] campusFreq;
		int total;
		String dept;
		public Stats(String dept) {
			this.dept = dept;
			this.total = 0;
			this.campusFreq = new int[6];
		}
		public int addCampus(String campus) {
			switch (campus) {
			case "BUS":
				campusFreq[0]++;
				campusTotalFreq[0]++;
				total++;
				return campusFreq[0];
			case "LIV":
				campusFreq[1]++;
				campusTotalFreq[1]++;
				total++;
				return campusFreq[1];
			case "CAC":
				campusFreq[2]++;
				campusTotalFreq[2]++;
				total++;
				return campusFreq[2];
			case "D/C":
				campusFreq[3]++;
				campusTotalFreq[3]++;
				total++;
				return campusFreq[3];
			case "DNB":
				campusFreq[4]++;
				campusTotalFreq[4]++;
				total++;
				return campusFreq[4];
			default:
				campusFreq[5]++;
				campusTotalFreq[5]++;
				total++;
				return campusFreq[5];
			}

		}
		public String toString() {
			String retval = this.dept + ":\t"; 
			for (int i = 0; i < campusFreq.length; i++) {
				retval += this.campusFreq[i] + "\t";
			}
			retval += this.total;
			return retval;
		}
	}

	/**
	 * Gets the distribution of classes across campuses for each dept.
	 * @param schedule List of classrooms
	 * @return Map of stats for each dept
	 */
	private static HashMap<String, Stats> generateStats(HashMap<CourseCondensed, Classroom> schedule) {
		HashMap<String, Stats> freq =  new HashMap<String, Stats>();

		for (CourseCondensed cc: schedule.keySet()) {
			Stats s = freq.get(cc.getCourse().getSubject());
			if (s == null) {
				s =  new Stats(cc.getCourse().getSubject());
				freq.put(cc.getCourse().getSubject(), s);
			}
			s.addCampus(schedule.get(cc).getCampus());
		}
		return freq;
	}

	/**
	 * Prints stats about scheduling
	 * @param schedule Schedule
	 */
	public static void analyze(HashMap<CourseCondensed, Classroom> schedule) {
		HashMap<String, Stats> stats = generateStats(schedule);

		System.out.println("dept\tBUS\tLIV\tCAC\tD/C\tDNB\tOther\tTotal");

		SortedSet<String> keys = new TreeSet<String>(stats.keySet());
		for (String key : keys) { 
			Stats s = stats.get(key);
			System.out.println(s);
		}

		for (int x: campusTotalFreq) {
			System.out.print("\t" + x);
		}
		System.out.println();
	}
}
