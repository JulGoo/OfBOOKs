package Service.User;

import java.io.PrintWriter;

import DAO.UserDAO;
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
		}else {
			System.out.println("null");
			return null;
		}
	}

	// 로그인 페이지
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get");
		return "login";
	}

	// 로그인 확인 버튼
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		UserDAO userDAO = new UserDAO();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		System.out.println("post");

		String result = userDAO.login(userID, userPW);
		try {
			switch (result) {
			// 비밀번호 오류
			case "a":
				PrintWriter a = response.getWriter();
				a.println("<script>");
				a.println("alert('비밀번호가 틀렸습니다.')");
				a.println("</script>");
				a.close();
				// 회원정보 없음
			case "b":
				PrintWriter b = response.getWriter();
				b.println("<script>");
				b.println("alert('존재하지 않는 아이디입니다.')");
				b.println("</script>");
				b.close();
				// 데이터베이스 오류
			case "c":
				PrintWriter c = response.getWriter();
				c.println("<script>");
				c.println("alert('Database error')");
				c.println("</script>");
				c.close();
			default:
				PrintWriter success = response.getWriter();
				session.setAttribute("userID", userID);
				session.setAttribute("userName", result);
				success.println("<script>");
				success.println("alert('login success!')");
				success.println("</script>");
				success.close();
				System.out.println("BBbbbbbb");
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "login";
	}
}
