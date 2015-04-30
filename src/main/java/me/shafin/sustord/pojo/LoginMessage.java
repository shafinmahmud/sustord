/*
 */
package me.shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class LoginMessage {
    
    private boolean requestedIdValid;
    private boolean requestedPasswordValid;    
    private String messageTitle;
    private String messageBody;
    private String requestedId;

    /**
     * @return the requestedIdValid
     */
    public boolean isRequestedIdValid() {
        return requestedIdValid;
    }

    /**
     * @param requestedIdValid the requestedIdValid to set
     */
    public void setRequestedIdValid(boolean requestedIdValid) {
        this.requestedIdValid = requestedIdValid;
    }

    /**
     * @return the requestedPasswordValid
     */
    public boolean isRequestedPasswordValid() {
        return requestedPasswordValid;
    }

    /**
     * @param requestedPasswordValid the requestedPasswordValid to set
     */
    public void setRequestedPasswordValid(boolean requestedPasswordValid) {
        this.requestedPasswordValid = requestedPasswordValid;
    }

    /**
     * @return the messageTitle
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * @param messageTitle the messageTitle to set
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
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
     * @return the requestedId
     */
    public String getRequestedId() {
        return requestedId;
    }

    /**
     * @param requestedId the requestedId to set
     */
    public void setRequestedId(String requestedId) {
        this.requestedId = requestedId;
    }

    @Override
    public String toString() {
        return "LoginMessage{" + "requestedIdValid=" + requestedIdValid + ", requestedPasswordValid=" + requestedPasswordValid + ", messageTitle=" + messageTitle + ", messageBody=" + messageBody + ", requestedId=" + requestedId + '}';
    }
    
}
