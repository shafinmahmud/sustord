/*
 */
package sm.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class LoginMessage extends Message{

    private boolean requestedIdValid;
    private boolean requestedPasswordValid;
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
        return "LoginMessage{" + "requestedIdValid=" + requestedIdValid + ", requestedPasswordValid=" + requestedPasswordValid + ", requestedId=" + requestedId + '}';
    }

}
