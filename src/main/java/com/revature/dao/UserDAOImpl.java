package com.revature.dao;

import com.revature.util.ConnectionUtil;
import com.revature.pojo.User;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private String filename = "src/main/resources/connection.properties";
    private User u = null;

    public User getUser(String username, String pass) {
        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            //using a Statement--beware injection
            String sql = "SELECT * FROM BANK_USER WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username.toLowerCase());
            stmt.setInt(2, pass.hashCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("USER_ID");
                String name = rs.getString("USERNAME");
                int password = rs.getInt("PASSWORD");
                boolean isAdmin = rs.getBoolean("IS_ADMIN");
                u = new User(id, name, password, isAdmin);
            } else {
                System.out.println("No users with that username/password combo");
            }
            con.close();
            return u;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newUser(String username, String password, boolean isAdmin) {
        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO BANK_USER(USERNAME, PASSWORD, IS_ADMIN) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username.toLowerCase());
            pstmt.setInt(2, password.hashCode());
            pstmt.setBoolean(3, isAdmin);
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean elevateUser(String username) {
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE BANK_USER SET IS_ADMIN = 1 WHERE USERNAME = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);

            if(pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(String username) {
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "DELETE FROM BANK_USER WHERE USERNAME = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);

            if(pstmt.executeUpdate() > 0 ) {
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
