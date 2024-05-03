package com.project.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.Model.BooleanAndMessage;
import com.project.Model.PostModel;
import com.project.Model.UserModel;
import com.project.Util.Util;
import com.project.Util.UtilImpl;

public class DaoImpl implements Dao {
	public BooleanAndMessage booleanAndMessage(UserModel userModel) {
		UserModel model = getUserByUsername(userModel.getUsername());
		
		if(model == null)
			return new BooleanAndMessage("Username doesn't exist!", false);
		
		boolean isValidPassword = model.getPassword().equals(userModel.getPassword());
		if(!isValidPassword)
			return new BooleanAndMessage("Incorrect Password!", isValidPassword);
		
		return new BooleanAndMessage("Logged in!", true);
	}
	
	public UserModel getUserByUsername(String username) {
		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		UserModel userModel = null;
		
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM user_info WHERE username='"+username+"'");
			if(rs.next())
				userModel = new UserModel(rs);
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return null;
		}
		
		return userModel;
	}
	
	public BooleanAndMessage createUserAccount(UserModel userModel) {
		int isCreated = 0;
		
		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT username AS username FROM user_info WHERE username='"+userModel.getUsername()+"'");
			if(!rs.next()) {
				isCreated = stm.executeUpdate("INSERT INTO user_info (username, password, name, email, dob, age, profile_pic, privileges) VALUES ('"+userModel.getUsername()+"', '"+userModel.getPassword()+"', '"+userModel.getName()+"', '"+userModel.getEmail()+"', '"+userModel.getDob()+"', '"+userModel.getAge()+"', '"+userModel.getProfilePic()+"', '"+userModel.getPrivileges()+"')");
			}
			else
				return new BooleanAndMessage("Username Already Exists!", false);
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return new BooleanAndMessage("Unable to create account!", false);
		}
		
		return new BooleanAndMessage("Account Created Successfully!", true);
	}
	
	public UserModel getUserByEmail(String email) {
		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		UserModel userModel = null;
		
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM user_info WHERE email='"+email+"'");
			if(rs.next())
				userModel = new UserModel(rs);
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return null;
		}
		
		return userModel;
	}
	
	public BooleanAndMessage changeUserPassword(UserModel userModel) {
		int isCreated = 0;

		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			isCreated = stm.executeUpdate("UPDATE user_info SET password='"+userModel.getPassword()+"' WHERE uid='"+userModel.getUid()+"'");
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return new BooleanAndMessage("Unable to update password!", false);
		}
		
		return new BooleanAndMessage("Password Successfully Changed!", true);
	}
	
	public ArrayList<PostModel> getPosts(int limit) {
		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		ArrayList<PostModel> listOfPosts = new ArrayList<PostModel>();
		
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM post ORDER BY post_id DESC LIMIT "+limit+", 3");
			while(rs.next()) {
				ResultSet result = stm.executeQuery("SELECT * FROM user_info WHERE uid='"+rs.getInt("uid")+"'");
				if(result.next())
					listOfPosts.add(new PostModel(rs, result.getString("username"), result.getString("profile_pic")));
			}
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			e.printStackTrace();
			return null;
		}
		
		return listOfPosts;
	}
	
	public BooleanAndMessage insertPostByUid(PostModel postModel) {
		int isCreated = 0;

		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			isCreated = stm.executeUpdate("INSERT INTO post(post_name, uid) VALUES ('"+postModel.getPostName()+"', '"+postModel.getUid()+"')");
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return new BooleanAndMessage("Unable to upload post!", false);
		}
		
		return new BooleanAndMessage("Post Uploaded Successfully", true);
	}
	
	public BooleanAndMessage insertOtpByUid(UserModel userModel) {
		int isCreated = 0;

		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			isCreated = stm.executeUpdate("UPDATE user_info SET OTP='"+userModel.getOtp()+"' WHERE uid='"+userModel.getUid()+"'");
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return new BooleanAndMessage("Unable to generate OTP! Please try again later!", false);
		}
		
		return new BooleanAndMessage("OTP generated Successfully!", true);
		
	}
	
	public BooleanAndMessage validateOtpByUid(UserModel userModel) {
		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT OTP FROM user_info as OTP WHERE uid='"+userModel.getUid()+"' and OTP='"+userModel.getOtp()+"'");
			if(rs.next())
				return new BooleanAndMessage("Valid OTP!", true);
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			e.printStackTrace();
			return null;
		}
		
		return new BooleanAndMessage("Invalid OTP!", false);
	}
	
	public BooleanAndMessage insertProfilePicByUid(UserModel userModel) {
		int isCreated = 0;

		Connection con = null;
		Statement stm = null;
		
		Util util = new UtilImpl();
		con = util.get();
		
		try {
			stm = con.createStatement();
			isCreated = stm.executeUpdate("UPDATE user_info SET profile_pic='"+userModel.getProfilePic()+"' WHERE uid='"+userModel.getUid()+"'");
		}
		catch(SQLException e) {
			System.out.println("Query Issue!");
			return new BooleanAndMessage("Unable to upload Profile Picture!", false);
		}
		
		return new BooleanAndMessage("Profile Picture Uploaded Successfully", true);
		
	}
}