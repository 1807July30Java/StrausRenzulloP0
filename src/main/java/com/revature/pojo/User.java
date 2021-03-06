package com.revature.pojo;

public class User {
    private int userID;
    private String username;
    private int password;
    private boolean isAdmin;

    /**
     * @param userID
     * @param username
     * @param password
     * @param isAdmin
     */
    public User(int userID, String username, String password, boolean isAdmin) {
        super();
        this.userID = userID;
        this.username = username;
        this.password = password.hashCode();
        this.isAdmin = isAdmin;
    }

    public User(int userID, String username, int passwordHash, boolean isAdmin) {
        super();
        this.userID = userID;
        this.username = username;
        this.password = passwordHash;
        this.isAdmin = isAdmin;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public int getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public void setPassword(int passwordhash) {
        this.password = passwordhash;
    }

    /**
     * @return the isAdmin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
