package com.revature.dao;

import com.revature.pojo.Transaction;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    private String filename = "src/main/resources/connection.properties";

    @Override
    public boolean addTransaction(int accountID, int userID, double amnt) {
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO BANK_TRANSACTION (TIMESTAMP, ACCOUNT_ID, BANKUSER_ID, AMOUNT) VALUES (?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, Timestamp.from(Instant.now()));
            pstmt.setInt(2, accountID);
            pstmt.setInt(3, userID);
            pstmt.setDouble(4, amnt);

            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Transaction getTransactionByID(int transactionID) {
        PreparedStatement pstmt;
        Transaction t = null;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM BANK_TRANSACTION WHERE TRANSACTION_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, transactionID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int transID = rs.getInt("TRANSACTION_ID");
                LocalDateTime date = rs.getTimestamp("TIMESTAMP").toLocalDateTime();
                int accountID = rs.getInt("ACCOUNT_ID");
                int bankuserID = rs.getInt("BANKUSER_ID");
                double amnt = rs.getDouble("AMOUNT");
                t = new Transaction(transID, date, accountID, bankuserID, amnt);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Transaction> getTransactionsForAccount(int accountID) {
        PreparedStatement pstmt;
        List<Transaction> t = new ArrayList<Transaction>();

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM BANK_TRANSACTION WHERE ACCOUNT_ID = ? ORDER BY BANK_TRANSACTION.TIMESTAMP DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int transID = rs.getInt("TRANSACTION_ID");
                LocalDateTime date = rs.getTimestamp("TIMESTAMP").toLocalDateTime();
                int acctID = rs.getInt("ACCOUNT_ID");
                int bankuserID = rs.getInt("BANKUSER_ID");
                double amnt = rs.getDouble("AMOUNT");
                t.add(new Transaction(transID, date, acctID, bankuserID, amnt));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Transaction> getTransactionsForUser(int userID) {
        PreparedStatement pstmt;
        List<Transaction> t = new ArrayList<Transaction>();

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM BANK_TRANSACTION WHERE BANKUSER_ID = ? ORDER BY BANK_TRANSACTION.TIMESTAMP DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int transID = rs.getInt("TRANSACTION_ID");
                LocalDateTime date = rs.getTimestamp("TIMESTAMP").toLocalDateTime();
                int account_id = rs.getInt("ACCOUNT_ID");
                int bankuserID = rs.getInt("BANKUSER_ID");
                double amnt = rs.getDouble("AMOUNT");
                t.add(new Transaction(transID, date, account_id, bankuserID, amnt));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
