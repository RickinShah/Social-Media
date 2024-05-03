package com.project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostModel {
	int postId;
	String postName;
	int likes;
	int uid;
	String username;
	String profilePic;
	
	

	public PostModel(int postId, String postName, int likes, int uid, String username, String profilePic) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.likes = likes;
		this.uid = uid;
		this.username = username;
		this.profilePic = profilePic;
	}

	public PostModel(int postId, String postName, int uid) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.uid = uid;
	}

	public PostModel(String postName, int uid) {
		super();
		this.postName = postName;
		this.uid = uid;
	}

	public PostModel(ResultSet rs, String username, String profilePic) {
		try {
			this.postId = rs.getInt("post_id");
			this.postName = rs.getString("post_name");
			this.uid = rs.getInt("uid");
			this.likes = rs.getInt("likes");
			this.username = username;
			this.profilePic = profilePic;
		} catch(SQLException e) { e.printStackTrace(); }
	}
	
	public String getUsername() {
		return username;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public int getPostId() {
		return postId;
	}

	public String getPostName() {
		return postName;
	}

	public int getLikes() {
		return likes;
	}

	public int getUid() {
		return uid;
	}
	
	
}
