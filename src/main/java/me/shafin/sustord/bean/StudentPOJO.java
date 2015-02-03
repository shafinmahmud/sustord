/*
 */
package me.shafin.sustord.bean;

import java.util.Comparator;

/**
 *
 * @author SHAFIN
 */
public class StudentPOJO{

    private String registrationNo;
    private String Name;
    private double completedCredits;
    private double cgpa;
    private int rank;

    /**
     * @return the registrationNo
     */
    public String getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param registrationNo the registrationNo to set
     */
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the completedCredits
     */
    public double getCompletedCredits() {
        return completedCredits;
    }

    /**
     * @param completedCredits the completedCredits to set
     */
    public void setCompletedCredits(double completedCredits) {
        this.completedCredits = completedCredits;
    }

    /**
     * @return the cgpa
     */
    public double getCgpa() {
        return cgpa;
    }

    /**
     * @param cgpa the cgpa to set
     */
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

 

    public static Comparator<StudentPOJO> CgpaMultiplyCreditComparator = new Comparator<StudentPOJO>() {

                @Override
                public int compare(StudentPOJO std1, StudentPOJO std2) {

                    double valStd1 = std1.getCgpa()*std1.getCompletedCredits();
                    double valStd2 = std2.getCgpa()*std2.getCompletedCredits();
                    
                    int comp = 0;
                    //ascending order
                    if(valStd1 > valStd2){
                        comp = 1;
                    }else if(valStd1 < valStd2){
                        comp = -1;
                    }
                    
                    return comp;

                }

            };

}
