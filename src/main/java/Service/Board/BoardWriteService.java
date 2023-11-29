package Service.Board;

import DAO.BoardDAO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardWriteService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}
	
	//게시글 작성 폼
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		return "boardWrite";
	}

	//게시글 저장
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		BoardDAO boardDAO = new BoardDAO();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userID = (String) session.getAttribute("userID");
		String fileName = request.getParameter("fileName");
		
		int result = boardDAO.write(title, content, userID, fileName);
		if(result == -1) {
			return "redirect:boardWrite.do?msg=DatabaseError";
		}else {
			return "boardDetail";
		}
	}
}
