/*
 */
package sm.sustord.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import sm.sustord.pojo.CourseGradePojo;
import sm.sustord.pojo.StudentGradePojo;

/**
 *
 * @author SHAFIN
 */
public class GpaCalculator {

    public static StudentGradePojo getGradePoint(List<CourseGradePojo> list) {
        StudentGradePojo studentGrade = new StudentGradePojo();
        double gradePoint = 0;
        double completedCredit = 0;
        double multipliedValue = 0;

        if (!list.isEmpty()) {
            for (CourseGradePojo gradePojo : list) {
                if (gradePojo.getGradePoint() > 0) {
                    completedCredit += gradePojo.getCredit();
                    multipliedValue += gradePojo.getCredit() * gradePojo.getGradePoint();
                }
            }
        }
        if (multipliedValue != 0) {
            gradePoint = multipliedValue / completedCredit;
        }
        //setting the gradepoint precision to 2 digit after decimal
        BigDecimal bd = new BigDecimal(gradePoint).setScale(2, RoundingMode.HALF_EVEN);
        gradePoint = bd.doubleValue();
        
        studentGrade.setGradePoint(gradePoint);
        studentGrade.setCompletedCredits(completedCredit);

        return studentGrade;
    }
}
