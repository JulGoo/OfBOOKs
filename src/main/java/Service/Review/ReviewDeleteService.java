package Service.Review;

import DAO.ReviewDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewDeleteService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	// 댓글 삭제
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		ReviewDAO reviewDAO = new ReviewDAO();
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String isbn = request.getParameter("isbn");
		
		int result = reviewDAO.delete(reviewNo);
		if (result == -1) {
			return "redirect:bookInfo.do?msg=DatabaseError";
		}
		return "redirect:bookInfo.do?isbn=" + isbn;
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:bookInfo.do";
	}
}
