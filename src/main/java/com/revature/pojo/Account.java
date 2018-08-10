package com.revature.pojo;

import java.util.Objects;

public class Account {
	private int accountID;
	private double accountBalance;
	private int ownerID;

	public Account(double accountBalance, int ownerID) {
		this.accountBalance = accountBalance;
		this.ownerID = ownerID;
	}

	public Account(int accountID, double accountBalance, int ownerID) {
		this.accountID = accountID;
		this.accountBalance = accountBalance;
		this.ownerID = ownerID;
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

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return getAccountID() == account.getAccountID() &&
				Double.compare(account.getAccountBalance(), getAccountBalance()) == 0 &&
				getOwnerID() == account.getOwnerID();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAccountID(), getAccountBalance(), getOwnerID());
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountID=" + accountID +
				", accountBalance=" + accountBalance +
				", ownerID=" + ownerID +
				'}';
	}
}
