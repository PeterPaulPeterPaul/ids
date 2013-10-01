package com.ids.user;

public class User  implements UserInterface, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userStatus;
	private String password;
	private String access;
    private int world;
    private int china;
    private int india;
    private String currentLocation;
	
	
	
	public User(String userName,    String password, String access, int world, int china, int india, String currentLocation ) {
		this.userName = userName;
		this.password = password;
		this.access = access;
		this.world = world;
		this.china = china;
		this.india = india;
		this.currentLocation = currentLocation;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getCurrentLocation() {
		return currentLocation;
	}
	public int getWorld() {
		return world;
	}
	public int getChina() {
		return china;
	}
	public int getIndia() {
		return india;
	}
	
	public String getUserStatus() {
		if (userStatus == null){
			userStatus="D";
		}
		return userStatus;
	}

	public String getAccess() {
		return access;
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
	
	public void setAccess(String access) {
		this.access = access;
	}
	
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
}
