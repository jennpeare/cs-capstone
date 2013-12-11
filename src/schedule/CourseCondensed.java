package schedule;

public class CourseCondensed implements Comparable<CourseCondensed> {
	
	Course course;
	Section section;
	MeetingTime meetingTime;
	
	public CourseCondensed(Course course, Section section, MeetingTime meetingTime) {
		this.course = course;
		this.section = section;
		this.meetingTime = meetingTime;
	}
	
	@Override
	public int compareTo(CourseCondensed o) {
		// TODO Auto-generated method stub
		return o.section.stopPoint - this.section.stopPoint;
	}
}
