/*.
 */
package me.shafin.sustord.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import me.shafin.sustord.bean.SyllabusPOJO;

/**
 *
 * @author SHAFIN
 */
public class CgpaCalculation {

    public static String getGradePointFromGradeLetter(String grade) {
        String gradePoint = "";
        
           if(grade.equals("A+")){
                gradePoint = "4.00";
           }else if(grade.equals("A")){
                gradePoint = "3.75";
           }else if(grade.equals("A-")){
                gradePoint = "3.50";
           }else if(grade.equals("B+")){
                gradePoint = "3.25";
           }else if(grade.equals("B")){
                gradePoint = "3.00";
           }else if(grade.equals("B-")){
                gradePoint = "2.75";
           }else if(grade.equals("C+")){
                gradePoint = "2.50";
           }else if(grade.equals("C")){
                gradePoint = "2.25";
           }else if(grade.equals("C-")){
                gradePoint = "2.00";
           }else if(grade.equals("F")){
                gradePoint = "0.00";
           }else{
               gradePoint = "-";
           }
            
        return gradePoint;
    }

    public static String getGradeLetterFromGradePoint(double point) {
        String gradeLetter = "";

        if (point == 4) {
            gradeLetter = "A+";
        } else if (point >= 3.75 && point < 4) {
            gradeLetter = "A";
        } else if (point >= 3.5 && point < 3.75) {
            gradeLetter = "A-";
        } else if (point >= 3.25 && point < 3.5) {
            gradeLetter = "B+";
        } else if (point >= 3 && point < 3.25) {
            gradeLetter = "B";
        } else if (point >= 2.75 && point < 3) {
            gradeLetter = "B-";
        } else if (point >= 2.5 && point < 2.75) {
            gradeLetter = "C+";
        } else if (point >= 2.25 && point < 2.5) {
            gradeLetter = "C";
        } else if (point >= 2 && point < 2.25) {
            gradeLetter = "C-";
        } else if (point >= 0 && point < 2) {
            gradeLetter = "F";
        } else {
            gradeLetter = "-";
        }

        return gradeLetter;
    }

    public static double getTotalCreditOfSemester(List<SyllabusPOJO> list) {
        double totalCredit = 0;

        for (SyllabusPOJO spojo : list) {
            totalCredit += spojo.getCredit();
        }
        return totalCredit;
    }
    
    public static double getTotalAttendedCredit(List<SyllabusPOJO> allCourseList) {
        double totalCredit = 0;
        
        List<SyllabusPOJO> uniqueCourseList = new ArrayList<SyllabusPOJO>();
        for (SyllabusPOJO course : allCourseList) {
            boolean duplicateFlag = false;
            for(SyllabusPOJO uniqueCourse: uniqueCourseList){
                if(course.getSyllabusId() == uniqueCourse.getSyllabusId()){
                    duplicateFlag = true;
                }         
            }
            
            if(!duplicateFlag){
                uniqueCourseList.add(course);
                totalCredit += course.getCredit();
            }      
        }
        return totalCredit;
    }

    public static double getPassedCreditOfSemester(List<SyllabusPOJO> list) {
        double passedCredit = 0;

        for (SyllabusPOJO spojo : list) {
            if (!spojo.getGrade().equals("N/A") && !spojo.getGrade().equals("F")) {
                passedCredit += spojo.getCredit();
            }
        }

        return passedCredit;
    }

    public static double getGradePointOfSemester(List<SyllabusPOJO> list) {
        double gradePoint = 0;
        double multipliedValue = 0;

        if (!list.isEmpty()) {
            for (SyllabusPOJO spojo : list) {

                if (!spojo.getPoint().equals("-") && !spojo.getPoint().equals("0.00")) {
                    double point = Double.parseDouble(spojo.getPoint());
                    multipliedValue += spojo.getCredit() * point;
                }
            }
        }

        if (multipliedValue != 0) {
            gradePoint = multipliedValue / getPassedCreditOfSemester(list);
        }
        //setting the gradepoint precision to 2 digit after decimal
        BigDecimal bd = new BigDecimal(gradePoint).setScale(2, RoundingMode.HALF_EVEN);
        gradePoint = bd.doubleValue();

        return gradePoint;
    }
    
}
