package schedule.campus;

/**
 * Period information for Livingston Campus.
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Livingston extends Campus {
	public Livingston() {
		periods = new int[][]{{840, 1000}, {1020, 1140}, {1200, 1320}, {1340, 1500}, {1520, 1640}, 
				{1700, 1820}, {1840, 2000}, {2010, 2130}, {2140, 2300}};
		name = "LIV";
	}
}