package schedule.campus;

/**
 * Period information for Downtown New Brunswick Campus.
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Downtown extends Campus {
	public Downtown() {
		periods = new int[][]{{810, 930}, {950, 1110}, {1130, 1250}, {1310, 1430}, {1450, 1610}, 
				{1630, 1750}, {1810, 1930}, {1940, 2100}, {2110, 2230}};
		name = "DNB";
	}
}