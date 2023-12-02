package Service.Like;

import DAO.LikeDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LikeService implements CommandHandler{

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

	//추천 총 개수 반환
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String isbn = request.getParameter("isbn");
		String userID = (String) session.getAttribute("userID");
		
		if(userID == null) {
			return "-1";
		}
		
		LikeDAO likeDAO = new LikeDAO();
		// 추천 상태 확인
		boolean result = likeDAO.isLiked(isbn, userID);
		if(result == true) {
			//눌렀으면 취소
			likeDAO.likeDown(isbn, userID);
		}else {
			//아니면 추천
			likeDAO.likeUp(isbn, userID);
		}
		//추천 총 개수
		int likeCnt = likeDAO.likeCount(isbn);
		
		return Integer.toString(likeCnt);
	}
}
