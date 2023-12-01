package Service.User;

import DAO.UserDAO;
import DTO.UserDTO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("aaaa");
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else if ("POST".equals(request.getMethod())) {
			return processPost(request, response);
		} else {
			return null;
		}
	}

	// 프로필 페이지
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("get");
		//System.out.println("bbbb");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userID");
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.logintoSession(id);
		request.setAttribute("userDTO", userDTO);
		
		return "profile";
	}

	// 프로필 수정
	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("cccc");
		response.setContentType("text/html; charset=UTF-8");

		String folder = "/uploadProfile";
		String realPath = request.getRealPath(folder);
		int max = 10*1024*1024;
		
//		try {
//			MultipartRequest mr = new MultipartRequest((javax.servlet.http.HttpServletRequest) request, realPath, max, "utf-8", new DefaultFileRenamePolicy());
//			String userID = mr.getParameter("userID");
//			String userPW = mr.getParameter("userPW");
//			String userName = mr.getParameter("userName");
//			String userEmail = mr.getParameter("userEmail");
//			String fileName = mr.getParameter("fileName");
//			
//			File file = new File(realPath+"/"+fileName);
//			int size = (int)file.length();
//		
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
		UserDAO userDAO = new UserDAO();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String fileName = request.getParameter("fileName");

		System.out.println("post");

		int result = userDAO.profile(userPW, userName, userEmail, fileName, userID);
		if (result == -1) {
			return "redirect:profile.do?msg=DatabaseError";
		}else {
			HttpSession session = request.getSession();
			session.invalidate();
			
			session.setAttribute("userID", userID);
			session.setAttribute("userName", userName);
			session.setAttribute("fileName", fileName);
			
			return "redirect:index.do?msg=Success";
		}
	}

}
