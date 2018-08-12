package com.revature.dao;

import com.revature.pojo.Account;
import com.revature.pojo.User;

import java.util.List;

public interface AccountDAO {
    double deposit(Account a, int userID, double amnt);
    double withdraw(Account a, int userID, double amnt);
    boolean addAccount(Account a);
    boolean addAccount(int ownerID);
    boolean deleteAccount(Account a);
    Account getAccountById(int id);
    List<Account> getAllAccounts(User u);
}
