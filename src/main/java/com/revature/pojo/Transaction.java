package com.revature.pojo;

import java.time.LocalDateTime;

public class Transaction {
	private int transID;
	private LocalDateTime timestamp;
	private int accountID;
	private double amount;
	
	/**
	 * @param transID
	 * @param timestamp
	 * @param accountID
	 * @param amount
	 */
	public Transaction(int transID, LocalDateTime timestamp, int accountID, double amount) {
		super();
		this.transID = transID;
		this.timestamp = timestamp;
		this.accountID = accountID;
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
}
