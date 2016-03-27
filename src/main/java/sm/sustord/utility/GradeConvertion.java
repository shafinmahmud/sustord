/*

 */
package sm.sustord.utility;

/**
 *
 * @author SHAFIN
 */
public class GradeConvertion {

    public static double getGradePointFromGradeLetter(String grade) {
  
        if (grade.equals("A+")) {
            return 4.00;
        } else if (grade.equals("A")) {
            return 3.75;
        } else if (grade.equals("A-")) {
            return 3.50;
        } else if (grade.equals("B+")) {
            return 3.25;
        } else if (grade.equals("B")) {
            return 3.00;
        } else if (grade.equals("B-")) {
            return 2.75;
        } else if (grade.equals("C+")) {
            return 2.50;
        } else if (grade.equals("C")) {
            return 2.25;
        } else if (grade.equals("C-")) {
            return 2.00;
        } else if (grade.equals("F")) {
            return 0.00;
        } else {
            return -1;
        }
    }

    public static String getGradeLetterFromGradePoint(double point) {
        String gradeLetter;

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
}
