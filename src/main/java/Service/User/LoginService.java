package Service.User;

import DAO.UserDAO;
import DTO.UserDTO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else if ("POST".equals(request.getMethod())) {
			return processPost(request, response);
		} else {
			//System.out.println("null");
			return null;
		}
	}

	// 로그인 페이지
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("get");
		return "login";
	}

	// 로그인 확인 버튼
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		UserDAO userDAO = new UserDAO();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");

		//System.out.println("post");

		int result = userDAO.login(userID, userPW);
		switch (result) {
		// 로그인 성공 시
		case 1:
			UserDTO udto = userDAO.logintoSession(userID);
			
			session.setAttribute("userID", udto.getUserID());
			session.setAttribute("userName", udto.getUserName());
			session.setAttribute("fileName", udto.getFileName());
			
			return "index";
		// 비밀번호 오류
		case 0:
			return "redirect:login.do?msg=WrongPW";
		// 회원정보 없음
		case -1:
			return "redirect:login.do?msg=NoUserInfo";
		// 데이터베이스 오류
		case -2:
			return "redirect:login.do?msg=DatabaseError";
		}
		
		return "redirect:login.do?msg=Error";
	}

}
