package com.project.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.project.Dao.Dao;
import com.project.Dao.DaoImpl;
import com.project.Model.PostModel;

@WebServlet("/MainPage")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DashboardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int action = request.getParameter("action") != null? Integer.parseInt(request.getParameter("action")): 0;
		HttpSession ses = request.getSession();

		switch(action) {
		case 1:
			int lastPost = 0;
			Dao dao = new DaoImpl();
			ArrayList<PostModel> postModel = dao.getPosts(lastPost);
			ses.setAttribute("lastPost", lastPost + 3);
			lastPost = lastPost + 3;
			request.setAttribute("postModel", postModel);
			for(PostModel pm : postModel) {
				System.out.println(pm.getPostName());
			}
			String dest = "/jsp/MasterPage.jsp?action=1";
			RequestDispatcher rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);
			break;
		case 0:
			lastPost = ((Number)ses.getAttribute("lastPost")).intValue();
			dao = new DaoImpl();
			postModel = dao.getPosts(lastPost);
			ses.setAttribute("lastPost", lastPost + 3);
			lastPost = lastPost + 3;
			request.setAttribute("postModel", postModel);
			for(PostModel pm : postModel) {
				System.out.println(pm.getPostName());
			}
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			response.setContentType("application/json;charset=utf-8");
			
			json.put("postModel", postModel);
			out.write(json.toString());
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
