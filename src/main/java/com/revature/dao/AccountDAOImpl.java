package com.revature.dao;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private String filename = "src/main/resources/connection.properties";
    TransactionDAO td = new TransactionDAOImpl();

    public double deposit(Account a, int userID, double amnt) {
        PreparedStatement pstmt;

        if (a == null || amnt < 0) {
            return 0;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, a.getAccountBalance()+amnt);
            pstmt.setInt(2, a.getAccountID());
            if (pstmt.executeUpdate() > 0) {
                a.setAccountBalance(a.getAccountBalance()+amnt);
                td.addTransaction(a.getAccountID(), userID, amnt);
                return a.getAccountBalance();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return a.getAccountBalance();
    }

    public double withdraw(Account a, int userID, double amnt) {
        PreparedStatement pstmt;

        if (a == null || amnt < 0 || amnt > a.getAccountBalance()) {
            return 0;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, a.getAccountBalance()-amnt);
            pstmt.setInt(2, a.getAccountID());
            if (pstmt.executeUpdate() > 0) {
                a.setAccountBalance(a.getAccountBalance()-amnt);
                td.addTransaction(a.getAccountID(), userID, -amnt);
                return a.getAccountBalance();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addAccount(Account a) {
        PreparedStatement pstmt;
        if (a == null) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)){
            String sql = "INSERT INTO BANK_ACCOUNT (BALANCE, OWNER_ID) VALUES (?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, a.getAccountBalance());
            pstmt.setInt(2, a.getOwnerID());

            if(pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAccount(int ownerID) {
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)){
            String sql = "SELECT * FROM BANK_USER WHERE USER_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ownerID);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                sql = "INSERT INTO BANK_ACCOUNT (OWNER_ID) VALUES (?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, ownerID);

                if (pstmt.executeUpdate() > 0) {
                    return true;
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(Account a) {
        PreparedStatement pstmt;
        if (a == null || a.getAccountBalance() > 0) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "DELETE FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, a.getAccountID());

            if (pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Account getAccountById(int id) {
        Account a = null;
        PreparedStatement pstmt;

        if (id <= 0) {
            return a;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int accountID = rs.getInt("ACCOUNT_ID");
                double balance = rs.getDouble("BALANCE");
                int ownerID = rs.getInt("OWNER_ID");
                a = new Account(accountID, balance, ownerID);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return a;
    }

    @Override
    public List<Account> getAllAccounts(User u) {
        PreparedStatement pstmt;
        List<Account> a = new ArrayList<Account>();

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM BANK_ACCOUNT WHERE OWNER_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, u.getUserID());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int acctID = rs.getInt("ACCOUNT_ID");
                double balance = rs.getDouble("BALANCE");
                int owner = rs.getInt("OWNER_ID");
                a.add(new Account(acctID, balance, owner));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return a;
    }
}
