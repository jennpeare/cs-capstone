package schedule;

public class Classroom {
	
	//building?
	String room;
	int capacity;
	boolean wheelchair;
	boolean restricted;
	String type;
	
	public Classroom() {
		
	}
	
	public Classroom(String room, int capacity, boolean wheelchair, boolean restricted,
			String type) {
		this.room = room;
		this.capacity = capacity;
		this.wheelchair = wheelchair;
		this.restricted = restricted;
		this.type = type;
	}
}
