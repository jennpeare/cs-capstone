package schedule;

import com.google.gson.annotations.SerializedName;

/**
 * @author Billy Lynch
 * @author Jenny Shi
 * 
 */
public class Course {
	@SerializedName("campusCode")
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
	@SerializedName("sections")
	Section[] sections;
	String subject;
	String subjectNotes;
	String subjectGroupNotes;
	String supplementCode;
	String synopsisUrl;
	String title;
	String unitNotes;
	
	/**
	 *
	 */
	public static class CoreCodes {
		String code;
		String course;
		String description;
		String effective;
		String subject;
		String supplement;
		String unit;
		
		public CoreCodes() {
			
		}
	}
	
	public Course() {
		
	}

}
