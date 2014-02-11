package schedule;

public class CourseCondensed implements Comparable<CourseCondensed> {
	
	Course course;
	Section section;
	MeetingTime meetingTime;
	static String sortMode = "stopPoint";
	
	public CourseCondensed(Course course, Section section, MeetingTime meetingTime) {
		this.course = course;
		this.section = section;
		this.meetingTime = meetingTime;
	}
	
	public CourseCondensed(Course course, Section section, MeetingTime meetingTime, String sortMode) {
		this(course, section, meetingTime);
	}
	
	public static void setSortMode(String mode) {
		 sortMode = mode;
	}
	
	@Override
	public int compareTo(CourseCondensed o) {
		// TODO Auto-generated method stub
		if (sortMode.equals("courseInc")) {
			return Integer.parseInt(this.course.courseNumber) - Integer.parseInt(o.course.courseNumber);
		} else if (sortMode.equals("courseDec")) {
			return Integer.parseInt(o.course.courseNumber) - Integer.parseInt(this.course.courseNumber);
		} else {
			return o.section.stopPoint - this.section.stopPoint;
		}
	}
	
	@Override
	public String toString(){
		return this.course.title + " " + this.course.courseNumber + " " 
				+ this.section.number + " " + this.meetingTime.meetingModeDesc + " " + 
				this.meetingTime.meetingDay + " " + this.meetingTime.startTime + " " +
				this.section.stopPoint;
	}
}
