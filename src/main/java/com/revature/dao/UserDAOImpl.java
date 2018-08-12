package com.revature.dao;

import com.revature.util.ConnectionUtil;
import com.revature.pojo.User;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAOImpl implements UserDAO{
    private String filename = "connections.properties";
    User u = null;
    public User getUser(String username, String pass){
    		try(Connection con = ConnectionUtil.getConnectionFromFile(filename)){
    			//using a Statement--beware injection
    			String sql = "SELECT * FROM USER WHERE USERNAME = ? PASSWORD = ?";
    			PreparedStatement stmt = con.prepareStatement(sql);
          stmt.setString(1,username);
          stmt.setInt(2,pass.hashCode());
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				int id = rs.getInt("USER_ID");
    				String name = rs.getString("USERNAME");
    				int password = rs.getInt("PASSWORD");
            boolean isAdmin = rs.getBoolean("ISADMIN");
    				u = new User(id,name,password,false);
    			}else{
            System.out.println("No users with that username/password combo");
          }
    			con.close();
    			return u;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		return null;
    }

    public void newUser(String username,String password,boolean isAdmin){
      try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
          String sql = "INSERT INTO USER(USER_NAME,PASSWORD,ISADMIN) VALUES (?,?,?)";
          PreparedStatement pstmt = con.prepareStatement(sql);
          pstmt.setString(1,username);
          pstmt.setInt(2,password.hashCode());
          pstmt.setBoolean(3,isAdmin);
          pstmt.execute();
      } catch (SQLException | IOException e) {
          e.printStackTrace();
      }
    }
}
