package Service.Board;

import DAO.BoardDAO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteService implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	// 게시글 작성 폼
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		return "boardWrite";
	}

	// 게시글 저장
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		// C:\Users\yrrud\OneDrive\바탕
		// 화면\2-2\JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\OfBOOKs에
		// uploadProfile 실제 폴더에 존재
		String folder = "/uploadProfile";
		String realPath = request.getServletContext().getRealPath(folder);
		int max = 10 * 1024 * 1024;

		try {
			MultipartRequest mr = new MultipartRequest(request, realPath, max, "utf-8", new DefaultFileRenamePolicy());

			HttpSession session = request.getSession();

			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String userID = (String) session.getAttribute("userID");
			String fileName = mr.getFilesystemName("fileName");

			BoardDAO boardDAO = new BoardDAO();

			int result = boardDAO.write(title, content, userID, fileName);
			if (result == -1) {
				return "redirect:boardWrite.do?msg=DatabaseError";
			} else {
				//bbsNo 마지막 값 가져오기
				int bbsNo = boardDAO.getLastBbsNo();
				System.out.println();
				return "redirect:boardDetail.do?bbsNo=" + bbsNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:boardWrite.do?msg=Error";
	}
}
