package test;
import static org.junit.Assert.*;

import org.junit.Test;

import schedule.campus.*;


public class CampusTest {

	private Campus[] campus = {new CollegeAve(), new Busch(), new Livingston(), new CookDouglass()};
	
	@Test
	public void testParseTime() {
		assertEquals(0, Campus.parseTime("1200", "A"));
		assertEquals(0, Campus.parseTime("1200", "a"));
		assertEquals(800, Campus.parseTime("0800", "A"));
		assertEquals(1020, Campus.parseTime("1020", "A"));
		assertEquals(1020, Campus.parseTime("1020", "a"));
		assertEquals(1200, Campus.parseTime("1200", "P"));
		assertEquals(1400, Campus.parseTime("200", "P"));
		assertEquals(1400, Campus.parseTime("0200", "P"));
		assertEquals(1400, Campus.parseTime("0200", "p"));
	}
	
	@Test
	public void testGetPeriod() {
		for (Campus c : campus) {
			assertEquals(-1, c.getPeriod("200", "a"));
			assertEquals(-1, c.getPeriod("1150", "p"));
			assertEquals(3, c.getPeriod("215", "p"));
		}
		assertEquals(-1, campus[3].getPeriod("200", "p"));
		assertEquals(3, campus[0].getPeriod("125", "p"));
		assertEquals(-1, campus[1].getPeriod("125", "p"));
		assertEquals(-1, campus[2].getPeriod("125", "p"));
		assertEquals(2, campus[3].getPeriod("125", "p"));
		assertEquals(2, campus[3].getPeriod("1235", "p"));
	}

}
