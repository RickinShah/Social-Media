package com.project.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.project.Dao.Dao;
import com.project.Dao.DaoImpl;
import com.project.Model.UserModel;

@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("action"));
		
		switch(action) {
		case 1:
			String fileName = request.getParameter("path");
			String imagePath = getServletContext().getInitParameter("upload.location");
			System.out.println(imagePath + fileName);
			InputStream inputStream = new FileInputStream(imagePath + fileName);
			response.setContentType("image/*");
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			break;
		case 2:
			String page = request.getParameter("page");
			int uid = Integer.parseInt(request.getParameter("uid"));
			Dao dao = new DaoImpl();
			String dest = "";
			
			switch(page) {
			case "changePassword":
				request.setAttribute("uid", uid);
				dest = "/jsp/ChangePassword.jsp";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);
			break;
		case 3:
			dest = "/jsp/Login.jsp";
			rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);
			break;
		case 4:
			fileName = request.getParameter("path");
			imagePath = getServletContext().getInitParameter("profilepic.location");
			System.out.println(imagePath + fileName);
			inputStream = new FileInputStream(imagePath + fileName);
			response.setContentType("image/*");
			outputStream = response.getOutputStream();
			buffer = new byte[1024];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			break;
		}

	}
}
