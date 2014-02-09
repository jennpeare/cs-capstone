package schedule;

import com.google.common.collect.Range;

public abstract class Campus {
	private int[][] periods;
	
	public Campus() {}
	
	private int parseTime(String timeStr, String pmCode) {
		int time = Integer.parseInt(timeStr);
		if (pmCode.equalsIgnoreCase("p")) {
			time += 1200;
		}
		return time;
	}
	
	public int getPeriod(String timeStr, String pmCode) {
		// Convert time string into 24-hour time represented by an int
		int time = parseTime(timeStr, pmCode);
		
		// Find corresponding period
		for (int i = 0; i < periods.length; i++) {
			if (time >= periods[i][0] && time <= periods[i][1]) {
				return i;
			}
		}
		
		return -1;
	}
	
	public class CollegeAve extends Campus {
		public CollegeAve() {
			periods = new int[][]{{810, 930}, {950, 1110}, {1130,1250}, {1310, 1430}, {1450, 1610}, 
					{1630, 1750}, {1810, 1930}, {1940, 2100}, {2110, 2230}};
		}
	}
	public class Busch extends Campus {
		public Busch() {
			periods = new int[][]{{840, 1000}, {1020, 1140}, {1200,1320}, {1340, 1500}, {1520, 1640}, 
					{1700, 1820}, {1840, 2000}, {2010, 2130}, {2140, 2300}};
		}
	}
	public class Livingston extends Campus {
		public Livingston() {
			periods = new int[][]{{840, 1000}, {1020, 1140}, {1200,1320}, {1340, 1500}, {1520, 1640}, 
					{1700, 1820}, {1840, 2000}, {2010, 2130}, {2140, 2300}};
		}
	}
	public class CookDouglass extends Campus {
		public CookDouglass() {
			periods = new int[][]{{915, 1035}, {1055, 1215}, {1235, 1355}, {1415, 1535}, {1555, 1715}, 
					{1735, 1855}, {1915, 2035}, {2045, 2205}};
		}
	}
}
