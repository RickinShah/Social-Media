package com.project.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.project.Dao.Dao;
import com.project.Dao.DaoImpl;
import com.project.Model.BooleanAndMessage;
import com.project.Model.PostModel;
import com.project.Model.UserModel;
import com.project.Util.Util;
import com.project.Util.UtilImpl;

@WebServlet("/InsertController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession ses = request.getSession();

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json;charset=utf-8");

		if(action.equals("logout")) {
			ses.removeAttribute("uid");
			ses.removeAttribute("username");
			ses.removeAttribute("name");
			ses.invalidate();
			BooleanAndMessage booleanAndMessage = new BooleanAndMessage("Logged Out Successfully", true);
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());
			out.write(json.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		Dao dao = new DaoImpl();
		Util util = new UtilImpl();

		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		response.setContentType("application/json;charset=utf-8");
		
		switch(action) {
		case "login":

			String username = request.getParameter("username");
			String password = util.hashPassword(request.getParameter("password"));
			UserModel userModel = new UserModel(username, password);
			BooleanAndMessage booleanAndMessage = dao.booleanAndMessage(userModel);
			
// Change the messages of all success event	
			if(!booleanAndMessage.isBool()) {
				json.put("success", booleanAndMessage.isBool());
				json.put("msg", booleanAndMessage.getMessage());
			}
			else {
				HttpSession ses = request.getSession();
				userModel = dao.getUserByUsername(username);
				ses.setAttribute("uid", userModel.getUid());
				ses.setAttribute("username", userModel.getUsername());
				ses.setAttribute("name", userModel.getName());
				//ses.setAttribute("lastPost", 0);
				ses.setAttribute("profilePic", userModel.getProfilePic());
				System.out.println(ses.getAttribute("uid") + " " + ses.getAttribute("username") + " " + ses.getAttribute("name"));
				json.put("msg", booleanAndMessage.getMessage());
				json.put("success", booleanAndMessage.isBool());
			}
			out.write(json.toString());
			break;

		case "createAccount":
			username = request.getParameter("username");
			password = util.hashPassword(request.getParameter("password"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String dob = request.getParameter("dob");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String profilePic = "default.jpg";
			userModel = new UserModel(username, password, name, email, dob, age, gender, profilePic, "User");
			booleanAndMessage = dao.createUserAccount(userModel);
			System.out.println(booleanAndMessage.getMessage());
			
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());

			out.write(json.toString());
			
			break;
		case "forgotPassword":
			username = request.getParameter("username");
			userModel = dao.getUserByUsername(username);
			if(userModel != null) {
				final String from = "rickinshah.21.cs@iite.indusuni.ac.in";
				final String to = userModel.getEmail();
				final String subject = "Forgot Password!";
				final String OTP = util.generateOTP();
				//final String msg = "Click on this link to change the password\n" + "http://localhost:8080/Social_Media/file?page=changePassword&uid=" + userModel.getUid() + "action=2";
				final String msg = "Your One Time Password for Social Media:<br /> <h1><strong>" + OTP + "</strong></h1>";
				UserModel userModel1 = new UserModel(userModel.getUid(), null, OTP);
				booleanAndMessage = dao.insertOtpByUid(userModel1);
				if(booleanAndMessage.isBool())
					booleanAndMessage =  util.mailSender(from, to, subject, msg);
				System.out.println(booleanAndMessage.getMessage());

				if(booleanAndMessage.isBool()) {
					HttpSession ses = request.getSession();
					ses.setAttribute("uid", userModel.getUid());
				}
				json.put("msg", booleanAndMessage.getMessage());
				json.put("success", booleanAndMessage.isBool());
				
			}
			else {
				json.put("msg", "Invalid Username!");
				json.put("success", false);
			}
			out.write(json.toString());
			break;
		
		case "changePassword":
			
			HttpSession ses = request.getSession();
			int uid = ((Number)ses.getAttribute("uid")).intValue();
			password = util.hashPassword(request.getParameter("password"));
			
			userModel = new UserModel(uid, password);
			booleanAndMessage = dao.changeUserPassword(userModel);
			
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());
			if(booleanAndMessage.isBool())
				ses.removeAttribute("uid");

			out.write(json.toString());
			break;
			
		case "Image":
			ses = request.getSession();
			uid = (int)ses.getAttribute("uid");

			Part filePart = request.getPart("postImage");
			String getImage = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			int getExtensionIndex = getImage.lastIndexOf('.');
			String fileExtension = getImage.substring(getExtensionIndex);

			String imagePath = getServletContext().getInitParameter("upload.location");
			String imageName = util.generateUUID() + fileExtension;
			
			File file = new File(imagePath, imageName);

			System.out.println(imagePath + imageName);
			
			try {
				InputStream input = filePart.getInputStream();
				Files.copy(input, file.toPath());
			} catch(IOException e) { e.printStackTrace(); }
			
			PostModel postModel = new PostModel(imageName, uid);
			booleanAndMessage = dao.insertPostByUid(postModel);
			boolean isFile = file.exists() && !file.isDirectory() && booleanAndMessage.isBool();
			
			String msg= imageName;
			System.out.println("File Exists: " + isFile);
			
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());
			out.write(json.toString());
			break;
		case "checkOtp":
			String OTP = request.getParameter("otp");
			ses = request.getSession();
			uid = ((Number)ses.getAttribute("uid")).intValue();
			userModel = new UserModel(uid, null, OTP);
			System.out.println(uid);
			System.out.println(OTP);
			
			booleanAndMessage = dao.validateOtpByUid(userModel);
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());
			out.write(json.toString());
			break;
		
		case "uploadProfilePic":
			ses = request.getSession();
			uid = (int)ses.getAttribute("uid");

			filePart = request.getPart("profile");
			getImage = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			getExtensionIndex = getImage.lastIndexOf('.');
			fileExtension = getImage.substring(getExtensionIndex);

			imagePath = getServletContext().getInitParameter("profilepic.location");
			imageName = util.generateUUID() + fileExtension;
			
			file = new File(imagePath, imageName);

			System.out.println(imagePath + imageName);
			
			try {
				InputStream input = filePart.getInputStream();
				Files.copy(input, file.toPath());
			} catch(IOException e) { e.printStackTrace(); }
			
			userModel = new UserModel(uid, imageName, null);
			booleanAndMessage = dao.insertProfilePicByUid(userModel);
			isFile = file.exists() && !file.isDirectory() && booleanAndMessage.isBool();
			ses = request.getSession();
			ses.setAttribute("profilePic", imageName);
			msg= imageName;
			System.out.println("File Exists: " + isFile);
			
			json.put("msg", booleanAndMessage.getMessage());
			json.put("success", booleanAndMessage.isBool());
			out.write(json.toString());
			break;
			
		}
			
	}
}
