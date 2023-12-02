package Service.User;

import DAO.UserDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterService implements CommandHandler{

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

	// 회원가입 페이지
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get");
		return "register";
	}

	// 회원가입 확인 버튼
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		UserDAO userDAO = new UserDAO();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");

		System.out.println("post");

		int result = userDAO.register(userID, userPW, userName, userEmail);
		if (result == -1) {
			return "redirect:register.do?msg=EmptyInput";
		}else {
			return "login";
		}
	}

}
