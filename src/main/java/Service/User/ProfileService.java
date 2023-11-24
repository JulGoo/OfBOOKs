package Service.User;

import DAO.UserDAO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfileService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else if ("POST".equals(request.getMethod())) {
			return processPost(request, response);
		} else {
			return null;
		}
	}

	// 프로필 페이지
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get");
		return "profile";
	}

	// 프로필 수정
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		UserDAO userDAO = new UserDAO();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");

		System.out.println("post");

		int result = userDAO.profile(userPW, userName, userEmail, userID);
		if (result == -1) {
			return "redirect:profile.do?msg==DatabaseError";
		}else {
			return "redirect:profile.do";
		}
	}

}
