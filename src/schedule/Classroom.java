package schedule;

public class Classroom {
	
	String building;
	String room;
	int capacity;
	boolean wheelchair;
	boolean restricted;
	String type;
	
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
