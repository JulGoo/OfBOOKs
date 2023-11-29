package Service.Board;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardUpdateService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	// 게시글 수정 화면
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo"));

		BoardDTO boardDTO = boardDAO.getBoardDetail(bbsNo);
		request.setAttribute("boardDTO", boardDTO);

		return "boardUpdate";
	}

	// 게시글 수정하기
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String fileName = request.getParameter("fileName");

		int result = boardDAO.update(title, content, fileName, bbsNo);
		if (result == -1) {
			return "redirect:boardUpdate.do?msg=DatabaseError";
		} else {
			return "redirect:boardDetail.do?bbsNo=" + bbsNo;
		}
	}
}
