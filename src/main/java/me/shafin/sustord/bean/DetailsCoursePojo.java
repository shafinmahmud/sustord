/*
 */
package me.shafin.sustord.bean;

/**
 *
 * @author SHAFIN
 */
public class DetailsCoursePojo extends CoursePojo{
    
    private String courseDescription;
    private String courseReferences;

    /**
     * @return the courseDescription
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * @param courseDescription the courseDescription to set
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    /**
     * @return the courseReferences
     */
    public String getCourseReferences() {
        return courseReferences;
    }

    /**
     * @param courseReferences the courseReferences to set
     */
    public void setCourseReferences(String courseReferences) {
        this.courseReferences = courseReferences;
    }

    @Override
    public String toString() {
        return "DetailsCoursePojo{" + "courseDescription=" + courseDescription + ", courseReferences=" + courseReferences + '}';
    }
    
    
}
