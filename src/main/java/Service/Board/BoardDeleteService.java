package Service.Board;

import DAO.BoardDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDeleteService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	// 게시글 삭제
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
		
		int result = boardDAO.delete(bbsNo);
		if(result == -1) {
			return "redirect:boardDetail.do?msg=DatabaseError";
		}
		return "redirect:board.do";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		return "board";
	}
}
