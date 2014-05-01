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
		return newClassroom(name, 40);
	}
	
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
