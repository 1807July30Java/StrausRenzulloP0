package com.revature.dao;
import com.revature.pojo.User;
public interface UserDAO{
    public User getUser(String username, String pass);
    public void newUser(String username,String password,boolean isAdmin);
}
