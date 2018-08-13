package com.revature.dao;
import com.revature.pojo.User;
public interface UserDAO{
    public User getUser(String username, String pass);
    public void newUser(String username,String password,boolean isAdmin);
    public boolean elevateUser(String username);
    public boolean deleteUser(String username);
}
