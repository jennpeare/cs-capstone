package schedule;

/**
 * MeetingTime contains information about Course meeting times.
 * @author Billy Lynch
 * @author Jenny Shi
 *
 */
public class MeetingTime {

	String baClassHours;
	String buildingCode;
	String campusAbbrev;
	public String getBaClassHours() {
		return baClassHours;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public String getCampusAbbrev() {
		return campusAbbrev;
	}

	public String getCampusLocation() {
		return campusLocation;
	}

	public String getCampusName() {
		return campusName;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getMeetingDay() {
		return meetingDay;
	}

	public String getMeetingModeCode() {
		return meetingModeCode;
	}

	public String getMeetingModeDesc() {
		return meetingModeDesc;
	}

	public String getPmCode() {
		return pmCode;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public static String getLecturecode() {
		return lectureCode;
	}

	public static String getRecitationcode() {
		return recitationCode;
	}

	String campusLocation;
	String campusName;
	String endTime;
	String meetingDay;
	String meetingModeCode;
	String meetingModeDesc;
	String pmCode;
	String roomNumber;
	String startTime;
	
	public static final String lectureCode = "02";
	public static final String recitationCode = "03";
	
	public MeetingTime() {
		
	}
}
