package com.project.Util;

import java.sql.Connection;

import com.project.Model.BooleanAndMessage;

public interface Util {
	Connection get();
	BooleanAndMessage mailSender(final String from, final String to, final String subject, final String msg);
	String hashPassword(final String password);
	String generateUUID();
	String generateOTP();
}
