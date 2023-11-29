package Service.Board;

import java.util.ArrayList;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}
	
	//게시글 목록 보기
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardDTO> boardDTO = boardDAO.getBoardList();		
		request.setAttribute("boardDTO", boardDTO);
		
		return "board";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {		
		return "board";
	}
}
