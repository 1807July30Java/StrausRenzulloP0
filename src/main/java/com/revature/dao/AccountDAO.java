package com.revature.dao;

import com.revature.pojo.Account;

public interface AccountDAO {
    double deposit(Account a, int userID, double amnt);
    double withdraw(Account a, int userID, double amnt);
    boolean addAccount(Account a);
    boolean addAccount(int ownerID);
    boolean deleteAccount(Account a);
    Account getAccountById(int id);

}
