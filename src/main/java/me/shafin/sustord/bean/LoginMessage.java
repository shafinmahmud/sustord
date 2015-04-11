/*
 */
package me.shafin.sustord.bean;

/**
 *
 * @author SHAFIN
 */
public class LoginMessage {
    
    private boolean REGISTRATION_NO_VALID;
    private boolean PASSWORD_VALID;    
    private String messageHeader;
    private String messageBody;
    private String requestedRegistrationNo;

    /**
     * @return the REGISTRATION_NO_VALID
     */
    public boolean isREGISTRATION_NO_VALID() {
        return REGISTRATION_NO_VALID;
    }

    /**
     * @param REGISTRATION_NO_VALID the REGISTRATION_NO_VALID to set
     */
    public void setREGISTRATION_NO_VALID(boolean REGISTRATION_NO_VALID) {
        this.REGISTRATION_NO_VALID = REGISTRATION_NO_VALID;
    }

    /**
     * @return the PASSWORD_VALID
     */
    public boolean isPASSWORD_VALID() {
        return PASSWORD_VALID;
    }

    /**
     * @param PASSWORD_VALID the PASSWORD_VALID to set
     */
    public void setPASSWORD_VALID(boolean PASSWORD_VALID) {
        this.PASSWORD_VALID = PASSWORD_VALID;
    }

    /**
     * @return the messageHeader
     */
    public String getMessageHeader() {
        return messageHeader;
    }

    /**
     * @param messageHeader the messageHeader to set
     */
    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    /**
     * @return the messageBody
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * @param messageBody the messageBody to set
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    /**
     * @return the requestedRegistrationNo
     */
    public String getRequestedRegistrationNo() {
        return requestedRegistrationNo;
    }

    /**
     * @param requestedRegistrationNo the requestedRegistrationNo to set
     */
    public void setRequestedRegistrationNo(String requestedRegistrationNo) {
        this.requestedRegistrationNo = requestedRegistrationNo;
    }

    @Override
    public String toString() {
        return "LoginMessage{" + "REGISTRATION_NO_VALID=" + REGISTRATION_NO_VALID + ", PASSWORD_VALID=" + PASSWORD_VALID + ", messageHeader=" + messageHeader + ", messageBody=" + messageBody + ", requestedRegistrationNo=" + requestedRegistrationNo + '}';
    }
    
}
