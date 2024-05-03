package com.project.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel implements Serializable{
	private int uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String dob;
	private int age;
	private String gender;
	private String bio;
	private String profilePic;
	private String privileges;
	private String otp;
	

	
	public UserModel(int uid, String profilePic, String otp) {
		super();
		this.uid = uid;
		this.profilePic = profilePic;
		this.otp = otp;
	}

	public UserModel(String username, String password, String name, String email, String dob, int age, String gender, String profilePic,
			String privileges) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.profilePic = profilePic;
		this.privileges = privileges;
	}
	public UserModel(String username, String password, String name, String email, String dob, int age, String gender,
			String privileges) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.privileges = privileges;
	}

	public UserModel(int uid, String username, String password, String name, String email, String dob, int age,
			String gender, String bio, String profilePic, String privileges) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.bio = bio;
		this.profilePic = profilePic;
		this.privileges = privileges;
	}
	
	public UserModel() {
		super();
	}
	
	public UserModel(ResultSet rs) {
		super();
		try {
		this.uid = rs.getInt("uid");
		this.username = rs.getString("username");
		this.password = rs.getString("password");
		this.name = rs.getString("name");
		this.email = rs.getString("email");
		this.dob = rs.getString("dob");
		this.age = rs.getInt("age");
		this.gender = rs.getString("gender");
		this.bio = rs.getString("bio");
		this.profilePic = rs.getString("profile_pic");
		this.privileges = rs.getString("privileges");
		}
		catch(SQLException e) {
			System.out.println("Issue in Retrieval!");
		}
		
	}

	public UserModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public UserModel(int uid, String password) {
		super();
		this.uid = uid;
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDob() {
		return dob;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getBio() {
		return bio;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public String getPrivileges() {
		return privileges;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	

}