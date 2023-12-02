package Service.Board;

import DAO.BoardDAO;
import DTO.BoardDTO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		// C:\Users\yrrud\OneDrive\바탕
		// 화면\2-2\JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\OfBOOKs에
		// uploadProfile 실제 폴더에 존재
		String folder = "/uploadProfile";
		String realPath = request.getServletContext().getRealPath(folder);
		int max = 10 * 1024 * 1024;
		try {
			MultipartRequest mr = new MultipartRequest(request, realPath, max, "utf-8", new DefaultFileRenamePolicy());

			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String fileName = mr.getFilesystemName("fileName");
			int bbsNo = Integer.parseInt(mr.getParameter("bbsNo"));

			BoardDAO boardDAO = new BoardDAO();

			int result = boardDAO.update(title, content, fileName, bbsNo);
			if (result == -1) {
				return "redirect:boardUpdate.do?msg=DatabaseError";
			} else {
				return "redirect:boardDetail.do?bbsNo=" + bbsNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:boardUpdate.do?msg=Error";
	}
}
