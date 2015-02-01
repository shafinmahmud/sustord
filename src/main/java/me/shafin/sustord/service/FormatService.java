/*
 */
package me.shafin.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class FormatService {
    
    /**
     *
     * @param semesterNo
     * @return
     */
    public static String formatSemesterName(int semesterNo) {
        String name = "";

        switch (semesterNo) {
            case 1:
                name = "First year First semester";
                break;
            case 2:
                name = "First year Second semester";
                break;
            case 3:
                name = "Second year First semester";
                break;
            case 4:
                name = "Second year Second semester";
                break;
            case 5:
                name = "Third year First semester";
                break;
            case 6:
                name = "Third year Second semester";
                break;
            case 7:
                name = "Fourth year First semester";
                break;
            case 8:
                name = "Fourth year Second semester";
                break;
            case 9:
                name = "Fifth year First semester";
                break;
            case 10:
                name = "Fifth year Second semester";
                break;
            case 0:
                name = "Optional Courses";
                break;
        }

        return name;
    }
    
    public static String formatSemesterNameShort(int semesterNo) {
        String name = "";

        switch (semesterNo) {
            case 1:
                name = "1st yr. 1st";
                break;
            case 2:
                name = "1st yr. 2nd";
                break;
            case 3:
                name = "2nd yr. 1st";
                break;
            case 4:
                name = "2nd yr. 2nd";
                break;
            case 5:
                name = "3rd yr. 1st";
                break;
            case 6:
                name = "3rd yr. 2nd";
                break;
            case 7:
                name = "4th yr. 1st";
                break;
            case 8:
                name = "4th yr. 2nd";
                break;
            case 9:
                name = "5th yr. 1st";
                break;
            case 10:
                name = "5th yr. 2nd";
                break;
            case 0:
                name = "Optional Courses";
                break;
        }

        return name;
    }
    /**
     *
     * @param semester
     * @return
     */
    public static String formatSemesterNumber(int semester) {
        double year = (double) semester / 2;
        year = Math.ceil(year);

        int part = semester % 2;
        if (part == 0) {
            part = 2;
        }
        
        return (int)year + "/" + part;
    }
    
}
