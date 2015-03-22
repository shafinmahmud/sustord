/*
 */
package me.shafin.sustord.bean;

/**
 *
 * @author SHAFIN
 */
public class SyllabusCoursePojo extends CoursePojo{
    

    private Double courseHoursPerWeek;
    private String prerequisite;

  
    /**
     * @return the courseHoursPerWeek
     */
    public Double getCourseHoursPerWeek() {
        return courseHoursPerWeek;
    }

    /**
     * @param courseHoursPerWeek the courseHoursPerWeek to set
     */
    public void setCourseHoursPerWeek(Double courseHoursPerWeek) {
        this.courseHoursPerWeek = courseHoursPerWeek;
    }

    /**
     * @return the prerequisite
     */
    public String getPrerequisite() {
        return prerequisite;
    }

    /**
     * @param prerequisite the prerequisite to set
     */
    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    @Override
    public String toString() {
        return "SyllabusCoursePojo{" + "courseHoursPerWeek=" + courseHoursPerWeek + ", prerequisite=" + prerequisite + '}';
    }
    
    
}
