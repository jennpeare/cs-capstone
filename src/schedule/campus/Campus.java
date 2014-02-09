package schedule.campus;

public abstract class Campus {
	protected int[][] periods;
	
	public static int parseTime(String timeStr, String pmCode) {
		if (timeStr.equals("1200") && pmCode.equalsIgnoreCase("a")) {
			return 0;
		}
		int time = Integer.parseInt(timeStr);
		if (!timeStr.equals("1200") && pmCode.equalsIgnoreCase("p")) {
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
}
