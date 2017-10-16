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
	
	protected final String adminID;
    protected final AdminInfo adminInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public AdminService(String adminId){
    	this.adminID = adminId;
        this.adminInfo = ServiceDispatcher.getSingletonAdminInfo(adminId);
    }
}
