package schedule;

import com.google.gson.annotations.SerializedName;

/**
 * Course contains the various data for individual courses.
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
	
	public String getCampusCode() {
		return campusCode;
	}

	public CoreCodes[] getCoreCodes() {
		return coreCodes;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public String getCourseNotes() {
		return courseNotes;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public String getCredits() {
		return credits;
	}

	public String getExpandedTitle() {
		return expandedTitle;
	}

	public String getOfferingUnitCode() {
		return offeringUnitCode;
	}

	public String getOfferingUnitTime() {
		return offeringUnitTime;
	}

	public String getOpenSections() {
		return openSections;
	}

	public String getPreReqNotes() {
		return preReqNotes;
	}

	public Section[] getSections() {
		return sections;
	}

	public String getSubject() {
		return subject;
	}

	public String getSubjectNotes() {
		return subjectNotes;
	}

	public String getSubjectGroupNotes() {
		return subjectGroupNotes;
	}

	public String getSupplementCode() {
		return supplementCode;
	}

	public String getSynopsisUrl() {
		return synopsisUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getUnitNotes() {
		return unitNotes;
	}

	String subjectNotes;
	String subjectGroupNotes;
	String supplementCode;
	String synopsisUrl;
	String title;
	String unitNotes;
	
	/**
	 * CoreCodes are supplemental data corresponding to SAS requirements fulfilled by the course.
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
