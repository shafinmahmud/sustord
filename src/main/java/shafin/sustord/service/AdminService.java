/*
 */
package shafin.sustord.service;

import shafin.sustord.entity.AdminInfo;
import shafin.sustord.util.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class AdminService {
	
	protected final String adminNo;
    protected final AdminInfo adminInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public AdminService(String adminNo) throws Exception {
    	this.adminNo = adminNo;
        this.adminInfo = ServiceDispatcher.getSingletonAdminInfo(adminNo);
    }
}
