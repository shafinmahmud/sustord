/*
 */
package shafin.sustord.dto;

/**
 *
 * @author SHAFIN
 */
public class OptionalCoursePojo extends CoursePojo{
        private String prerequisite;

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
        return "OptionalCoursePojo{" + "prerequisite=" + prerequisite + '}';
    }
    
    
}
