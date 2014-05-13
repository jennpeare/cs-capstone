package schedule;

import com.google.gson.annotations.SerializedName;

/**
 * Affinity contains the affinity information for each department.
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class Affinity {
	@SerializedName("dept")
	String department;
	String description;
	String affinity;

	public Affinity() {
		
	}
	
	/**
	 * @param name
	 * @return
	 */
	public Classroom newClassroom(String name) {
		return newClassroom(name, 40);
	}
	
	/**
	 * @param name
	 * @param cap
	 * @return New classroom assigned for this department/affinity
	 */
	public Classroom newClassroom(String name, int cap) {
		Classroom c = new Classroom();
		c.building = name;
		c.room = name;
		c.capacity = cap;
		c.campus = this.affinity;
		c.restricted = true;
		c.type = "Classroom";
		c.wheelchair = true;
		return c;
	}
}
