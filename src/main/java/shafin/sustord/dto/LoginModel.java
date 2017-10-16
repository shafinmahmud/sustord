package shafin.sustord.dto;

public class LoginModel {

	private String userRole;
	private String leftPanelImage;
	private String formHeaderText;
	private String userNameText;
	private String userNamePlaceHolder;
	private String validationMessage;
	private String alterUserText;
	private String alterUserURL;
	private String formSubmitURL;
	private String loginHelpURL;

	public LoginModel() {
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLeftPanelImage() {
		return leftPanelImage;
	}

	public void setLeftPanelImage(String leftPanelImage) {
		this.leftPanelImage = leftPanelImage;
	}

	public String getFormHeaderText() {
		return formHeaderText;
	}

	public void setFormHeaderText(String formHeaderText) {
		this.formHeaderText = formHeaderText;
	}

	public String getUserNameText() {
		return userNameText;
	}

	public void setUserNameText(String userNameText) {
		this.userNameText = userNameText;
	}

	public String getUserNamePlaceHolder() {
		return userNamePlaceHolder;
	}

	public void setUserNamePlaceHolder(String userNamePlaceHolder) {
		this.userNamePlaceHolder = userNamePlaceHolder;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public String getAlterUserText() {
		return alterUserText;
	}

	public void setAlterUserText(String alterUserText) {
		this.alterUserText = alterUserText;
	}

	public String getAlterUserURL() {
		return alterUserURL;
	}

	public void setAlterUserURL(String alterUserURL) {
		this.alterUserURL = alterUserURL;
	}

	public String getFormSubmitURL() {
		return formSubmitURL;
	}

	public void setFormSubmitURL(String formSubmitURL) {
		this.formSubmitURL = formSubmitURL;
	}

	public String getLoginHelpURL() {
		return loginHelpURL;
	}

	public void setLoginHelpURL(String loginHelpURL) {
		this.loginHelpURL = loginHelpURL;
	}

}
