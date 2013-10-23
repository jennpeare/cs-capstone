package schedule;

public class Course {
	String campusCode;
	CoreCodes[] coreCodes;
	String courseDescription;
	String courseNotes;
	String courseNumber;
	String credits;
	String expandedTitle;
	String offeringUnitCode;
	String offeringUnitTime;
	String openSections;
	String preReqNotes;
	Section[] section;
	String subject;
	String subjectNotes;
	String subjectGroupNotes;
	String supplementCode;
	String synopsisUrl;
	String title;
	String unitNotes;
	
	public static class CoreCodes {
		String code;
		String course;
		String description;
		String effective;
		String subject;
		String supplement;
		String unit;
	}
	
	public Course() {
		
	}

}
