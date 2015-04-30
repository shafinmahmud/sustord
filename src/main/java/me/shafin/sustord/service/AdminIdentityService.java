/*
 */
package me.shafin.sustord.service;

import me.shafin.sustord.dao.AdminInfoDao;
import me.shafin.sustord.model.AdminInfo;
import me.shafin.sustord.utility.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class AdminIdentityService {
    public final AdminInfo adminInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public AdminIdentityService(AdminInfo adminInfo) throws Exception {
        this.adminInfo = adminInfo;
    }

    /* Constructor helper for singleton AcademicInfoService  */
    public static AdminInfo forSingletonIdentityService(String registrationNo) throws Exception {
        return ServiceDispatcher.getSingletonAdminInfo(registrationNo);
    }

    /* Constructor helper for prototype AcademicInfoService  */
    public static AdminInfo forProtypeIdentityService(String adminNo) throws Exception {
        return AdminInfoDao.getAdminInfoObject(adminNo);
    }
}
