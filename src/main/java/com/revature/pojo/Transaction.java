package com.revature.pojo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
	private int transID;
	private LocalDateTime timestamp;
	private int accountID;
	private int bankuserID;
	private double amount;

	public Transaction(LocalDateTime timestamp, int accountID, int bankuserID, double amount) {
		this.timestamp = timestamp;
		this.accountID = accountID;
		this.bankuserID = bankuserID;
		this.amount = amount;
	}

	public Transaction(int transID, LocalDateTime timestamp, int accountID, int bankuserID, double amount) {
		this.transID = transID;
		this.timestamp = timestamp;
		this.accountID = accountID;
		this.bankuserID = bankuserID;
		this.amount = amount;
	}

	/**
	 * @return the transID
	 */
	public int getTransID() {
		return transID;
	}
	/**
	 * @param transID the transID to set
	 */
	public void setTransID(int transID) {
		this.transID = transID;
	}
	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBankuserID() {
		return bankuserID;
	}

	public void setBankuserID(int bankuserID) {
		this.bankuserID = bankuserID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return getTransID() == that.getTransID() &&
				getAccountID() == that.getAccountID() &&
				Double.compare(that.getAmount(), getAmount()) == 0 &&
				Objects.equals(getTimestamp(), that.getTimestamp());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTransID(), getTimestamp(), getAccountID(), getAmount());
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transID=" + transID +
				", timestamp=" + timestamp +
				", accountID=" + accountID +
				", amount=" + amount +
				'}';
	}
}
