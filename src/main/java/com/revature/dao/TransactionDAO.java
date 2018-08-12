package com.revature.dao;

import com.revature.pojo.Transaction;

import java.util.List;

public interface TransactionDAO {
    boolean addTransaction(int accountID, int userID, double amnt);
    Transaction getTransactionByID(int transactionID);
    List<Transaction> getTransactionsForAccount(int accountID);
    List<Transaction> getTransactionsForUser(int userID);
}
