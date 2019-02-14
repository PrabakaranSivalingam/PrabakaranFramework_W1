package com.training.bean;

/* UserBean class - Used getter and Setter method to store the values available in the userinfo table in realestate 
 * datbase of MySQL server  */

public class UserBean {
	
	private String userName;
	private String eMail;
	private String firstName;
	private String lastName;
	private String webSite;
	private String passwd;
	private String role;
	
	public UserBean() {
	}
	
	public UserBean(String userName, String eMail, String firstName, String lastName, String webSite, String passwd, String role) {
		
		super();
		this.userName = userName;
		this.eMail = eMail;
		this.firstName = firstName;
		this.lastName= lastName;
		this.webSite = webSite;
		this.passwd = passwd;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
