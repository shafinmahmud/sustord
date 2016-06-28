/*
 */
package shafin.sustord.service;

import shafin.sustord.entity.StudentInfo;
import shafin.sustord.util.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class StudentServicea {
	
	protected final String registrationNo;
    protected final StudentInfo studentInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public StudentServicea(String registrationNo){
    	this.registrationNo = registrationNo;
        this.studentInfo = ServiceDispatcher.getSingletonStudentInfo(registrationNo);
    }
    
}
