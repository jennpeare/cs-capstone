package schedule;

public class Section {

	String campusCode;
	//Comments[] comments;
	String[] crossListedSections;
	String examCode;
	String[] honorsPrograms;
	String index;
	//Instructor[] instructors;
	String legendKey;
	Object[] majors;
	//MeetingTime[] meetingTimes;
	String[] minors;
	String number;
	boolean openStatus;
	String printed;
	String sectionEligibility;
	String sectionNotes;
	String sessionDates;
	String sessionDatePrintIndicator;
	String specialPermissionAddCode;
	String specialPermissionAddCodeDescritpion;
	String specialPermissionDropCode;
	String specialPermissionDropCodeDescritpion;
	int stopPoint;
	String subtitle;
	String subtopic;
	String[] unitMajors;
	
	public static class Comments {
		String code;
		String description;
		
		public Comments() {
			
		}
	}
	
	public Section() {
		
	}
	
}
