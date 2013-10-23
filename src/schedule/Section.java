package schedule;

public class Section {

	String campusCode;
	Comments[] comments;
	String[] crossListedSections;
	String examCode;
	String[] honorsPrograms;
	String index;
	Instructor[] instructors;
	String legendKey;
	String[] majors;
	MeetingTime[] meetingTimes;
	String[] minors;
	String number;
	String openStatus;
	String printed;
	String sectionEligibility;
	String sectionNotes;
	String sessionDates;
	String sessionDatePrintIndicator;
	String specialPermissionAddCode;
	String specialPermissionAddCodeDescritpion;
	String specialPermissionDropCode;
	String specialPermissionDropCodeDescritpion;
	String stopPoint;
	String subtitle;
	String subtopic;
	String[] unitMajors;
	
	public class Comments {
		String code;
		String description;
		
	}
	
	public Section() {
		
	}
	
}
