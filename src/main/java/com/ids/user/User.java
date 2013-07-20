package com.ids.user;

public class User  implements UserInterface, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userStatus;
	private String password;

	
	
	
	public User(String userName,    String password ) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUserStatus() {
		if (userStatus == null){
			userStatus="D";
		}
		return userStatus;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
}
