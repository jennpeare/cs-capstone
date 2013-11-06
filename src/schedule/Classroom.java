package schedule;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

public class Classroom {
	
	@SerializedName("Building")
	String building;
	@SerializedName("Room")
	String room;
	@SerializedName("Room Capacity")
	int capacity;
	@SerializedName("Wheelchair Accessible")
	boolean wheelchair;
	@SerializedName("Restricted")
	boolean restricted;
	@SerializedName("Type")
	String type;
	@SerializedName("Campus")
	String campus;
	
	HashMap<String, Boolean> booked = new HashMap<String, Boolean>();
	
	public Classroom() {
		
	}
	
	public Classroom(String building, String room, int capacity, boolean wheelchair,
			boolean restricted, String type) {
		this.building = building;
		this.room = room;
		this.capacity = capacity;
		this.wheelchair = wheelchair;
		this.restricted = restricted;
		this.type = type;
	}
}
