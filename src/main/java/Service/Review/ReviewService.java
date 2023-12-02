package Service.Review;

import DAO.ReviewDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}
	
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:bookInfo.do";
	}

	//댓글 작성
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		ReviewDAO reviewDAO = new ReviewDAO();
		String isbn = request.getParameter("isbn");
		String userID = (String) session.getAttribute("userID");	
		if (userID == null) {
			return "redirect:bookInfo.do?msg=NoLogin";
		}
		String fileName = (String) session.getAttribute("fileName");
		String comment = request.getParameter("comment");
		
		int result = reviewDAO.write(isbn, userID, comment, fileName);
		if(result == -1) {
			return "redirect:bookInfo.do?msg=DatabaseError";
		}else {
			System.out.println(isbn);
			return "redirect:bookInfo.do?isbn=" + isbn;
		}
	}
}
