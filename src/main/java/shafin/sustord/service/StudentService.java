/*
 */
package shafin.sustord.service;

import shafin.sustord.entity.StudentInfo;
import shafin.sustord.util.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class StudentService {
	
	protected final String registrationNo;
    protected final StudentInfo studentInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public StudentService(String registrationNo){
    	this.registrationNo = registrationNo;
        this.studentInfo = ServiceDispatcher.getSingletonStudentInfo(registrationNo);
    }
    
}
