/*
 */
package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.pojo.Message;
import me.shafin.sustord.service.ChangePasswordService;

/**
 *
 * @author SHAFIN
 */
public class ChangePasswordController {

    public static Message saveNewPassword(String registrationNo, String oldPassword, String newPassword) {

        Message message = new Message();
        try {

            ChangePasswordService changePasswordService = new ChangePasswordService(registrationNo);

            if (changePasswordService.verifyCurrentPassword(oldPassword)) {
                if (changePasswordService.saveNewPassword(newPassword)) {
                    message.setMessageTitle("Password Chnaged");
                    message.setMessageBody("New password has been successfully changes.");
                    return message;
                } else {
                    message.setMessageTitle("ERROR in Opertation");
                    message.setMessageBody("");
                    return message;
                }

            } else {
                message.setMessageTitle("Current Password wrong!");
                message.setMessageBody("The requesting password is wrong.");
                return message;
            }
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}
