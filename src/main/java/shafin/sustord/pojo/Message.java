/*
 */
package shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class Message {
    
    private String messageTitle;
    private String messageBody;

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

    @Override
    public String toString() {
        return "Message{" + "messageTitle=" + messageTitle + ", messageBody=" + messageBody + '}';
    }
    
    
}
