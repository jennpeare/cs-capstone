package analysis;

import java.util.HashMap;

import schedule.Classroom;
import schedule.CourseCondensed;

public class Analysis {
	
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
					total++;
					return campusFreq[0];
				case "LIV":
					campusFreq[1]++;
					total++;
					return campusFreq[1];
				case "CAC":
					campusFreq[2]++;
					total++;
					return campusFreq[2];
				case "D/C":
					campusFreq[3]++;
					total++;
					return campusFreq[3];
				case "DNB":
					campusFreq[4]++;
					total++;
					return campusFreq[4];
				default:
					campusFreq[5]++;
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
	
	public static void analyze(HashMap<CourseCondensed, Classroom> schedule) {
		HashMap<String, Stats> stats = generateStats(schedule);
		
		System.out.println("dept\tBUS\tLIV\tCAC\tD/C\tDNB");
		
		for (Stats s: stats.values()) {
			System.out.println(s);
		}
	}
}
