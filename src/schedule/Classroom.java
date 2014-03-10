package schedule;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
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
	
	//HashMap<String, Boolean> booked = new HashMap<String, Boolean>();
	private boolean[][] booked = new boolean[7][9];
	private static final Map<String, Integer> weekday = ImmutableMap.<String,Integer>builder()
			.put("M", 0)
			.put("T", 1)
			.put("W", 2)
			.put("TH", 3)
			.put("F", 4)
			.put("S", 5)
			.put("SU", 6)
			.build();
	
	
	public Classroom() {}
	
	public Classroom(String building, String room, int capacity, boolean wheelchair,
			boolean restricted, String type) {
		this.building = building;
		this.room = room;
		this.capacity = capacity;
		this.wheelchair = wheelchair;
		this.restricted = restricted;
		this.type = type;
	}
	
	/**
	 * Checks if the classroom is available for a given period
	 * @param day
	 * @param period
	 * @return
	 */
	public boolean isBooked(String day, int period) {
		return booked[weekday.get(day)][period];
	}
	
	public boolean isBooked(String day, int startPeriod, int endPeriod) {
		for (int i = startPeriod; i <= endPeriod; i++) {
			if (isBooked(day, i)) {
				return true;
			}
		}
		return false;
	}
	
	public String getBuilding() {
		return building;
	}

	public String getRoom() {
		return room;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isWheelchair() {
		return wheelchair;
	}

	public boolean isRestricted() {
		return restricted;
	}

	public String getType() {
		return type;
	}

	public String getCampus() {
		return campus;
	}

	public boolean[][] getBooked() {
		return booked;
	}

	public static Map<String, Integer> getWeekday() {
		return weekday;
	}

	/**
	 * Books a classroom.
	 * @param day
	 * @param startPeriod
	 * @param endPeriod
	 * @return true is classroom booked successfully, else false
	 */
	public boolean bookRoom(String day, int startPeriod, int endPeriod) {
		if (isBooked(day, startPeriod, endPeriod)) {
			return false;
		}
		for (int i = startPeriod; i <= endPeriod; i++) {
			booked[weekday.get(day)][i] = true;
		}
		return true;
	}
}
