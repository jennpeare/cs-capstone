package schedule;

/**
 * @author Billy Lynch
 * @author Jenny Shi
 * 
 */
public class Section {

	String campusCode;
	Comments[] comments;
	String[] crossListedSections;
	String examCode;
	String[] honorsPrograms;
	String index;
	Instructor[] instructors;
	String legendKey;
	Major[] majors;
	MeetingTime[] meetingTimes;
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
	
	/**
	 *
	 */
	public static class Comments {
		String code;
		String description;
		
		public Comments() {
			
		}
	}
	
	public Section() {
		
	}
	
	public Section(Section s) {
		this.campusCode = s.campusCode;
		this.comments = s.comments;
		this.crossListedSections = s.crossListedSections;
		this.examCode = s.examCode;
		this.honorsPrograms = s.honorsPrograms;
		this.index = s.index;
		this.instructors = s.instructors;
		this.legendKey = s.legendKey;
		this.majors = s.majors;
		this.meetingTimes = s.meetingTimes;
		this.minors = s.minors;
		this.number = s.number;
		this.openStatus = s.openStatus;
		this.printed = s.printed;
		this.sectionEligibility = s.sectionEligibility;
		this.sectionNotes = s.sectionNotes;
		this.sessionDates = s.sessionDates;
		this.sessionDatePrintIndicator = s.sessionDatePrintIndicator;
		this.specialPermissionAddCode = s.specialPermissionAddCode;
		this.specialPermissionAddCodeDescritpion = s.specialPermissionAddCodeDescritpion;
		this.specialPermissionDropCode = s.specialPermissionDropCode;
		this.specialPermissionDropCodeDescritpion = s.specialPermissionDropCodeDescritpion;
		this.stopPoint = s.stopPoint;
		this.subtitle = s.subtitle;
		this.subtopic = s.subtopic;
		this.unitMajors = s.unitMajors;
	}
	
}
