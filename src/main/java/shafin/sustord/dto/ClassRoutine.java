package shafin.sustord.dto;

import java.util.List;

public class ClassRoutine {
	private String date;
	private String day;
	private List<ClassSchedule> schedules;
	
	class ClassSchedule {
		private Course course;
		private String starTimet;
		private String endTime;

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public String getStarTimet() {
			return starTimet;
		}

		public void setStarTimet(String starTimet) {
			this.starTimet = starTimet;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<ClassSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<ClassSchedule> schedules) {
		this.schedules = schedules;
	}

}
