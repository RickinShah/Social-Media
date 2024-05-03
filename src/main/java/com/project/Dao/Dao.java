package com.project.Dao;

import java.util.ArrayList;

import com.project.Model.BooleanAndMessage;
import com.project.Model.PostModel;
import com.project.Model.UserModel;

public interface Dao {
	BooleanAndMessage booleanAndMessage(UserModel userModel);
	UserModel getUserByUsername(String username);
	BooleanAndMessage createUserAccount(UserModel userModel);
	UserModel getUserByEmail(String email);
	BooleanAndMessage changeUserPassword(UserModel userModel);
	ArrayList<PostModel> getPosts(int limit);
	BooleanAndMessage insertPostByUid(PostModel postModel);
	BooleanAndMessage insertOtpByUid(UserModel userModel);
	BooleanAndMessage validateOtpByUid(UserModel userModel);
	BooleanAndMessage insertProfilePicByUid(UserModel userModel);
}
