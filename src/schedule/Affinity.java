package schedule;

import com.google.gson.annotations.SerializedName;

public class Affinity {
	@SerializedName("dept")
	String department;
	String description;
	String affinity;
	
	public Affinity() {
		
	}
	
	public Classroom newClassroom(String name) {
		Classroom c = new Classroom();
		c.building = name;
		c.room = name;
		c.capacity = 40;
		c.campus = this.affinity;
		c.restricted = true;
		c.type = "Classroom";
		c.wheelchair = true;
		return c;
	}
}
