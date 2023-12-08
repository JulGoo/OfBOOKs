package Service.Board;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDetailService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	// 게시글 상세 보기
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo"));

		BoardDTO boardDTO = boardDAO.getBoardDetail(bbsNo);
		request.setAttribute("boardDTO", boardDTO);

		return "boardDetail";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		return "boardDetail";
	}
}
