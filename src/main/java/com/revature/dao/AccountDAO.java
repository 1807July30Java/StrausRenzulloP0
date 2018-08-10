package com.revature.dao;

import com.revature.pojo.Account;

public interface AccountDAO {
    double deposit(Account a, double amnt);
    double withdraw(Account a, double amnt);
    boolean addAccount(Account a);
    boolean deleteAccount(Account a);
    Account getAccountById(int id);

}
