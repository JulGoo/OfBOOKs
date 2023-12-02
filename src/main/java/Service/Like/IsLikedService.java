package Service.Like;

import DAO.LikeDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IsLikedService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String isbn = request.getParameter("isbn");
		String userID = (String) session.getAttribute("userID");

		LikeDAO likeDAO = new LikeDAO();
		// 추천 상태 확인
		boolean result = likeDAO.isLiked(isbn, userID);
		String isLiked = result?"1":"0";

		return isLiked;
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:bookInfo.do";
	}
}
