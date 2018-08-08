package com.revature.pojo;

public class Account {
	private int accountID;
	private double accountBalance;
	
	/**
	 * @param accountID
	 * @param accountBalance
	 */
	public Account(int accountID, double accountBalance) {
		super();
		this.accountID = accountID;
		this.accountBalance = accountBalance;
	}
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the accountBalance
	 */
	public double getAccountBalance() {
		return accountBalance;
	}
	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
}
