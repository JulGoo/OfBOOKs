package Service.User;

import java.io.File;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.UserDAO;
import DTO.UserDTO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
		//C:\Users\yrrud\OneDrive\바탕 화면\2-2\JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\OfBOOKs에 uploadProfile 실제 폴더에 존재
		String folder = "/uploadProfile";
		String realPath = request.getServletContext().getRealPath(folder);
		int max = 10*1024*1024;
		
		try {
			MultipartRequest mr = new MultipartRequest(request, realPath, max, "utf-8", new DefaultFileRenamePolicy());
			String userID = mr.getParameter("userID");
			String userPW = mr.getParameter("userPW");
			String userName = mr.getParameter("userName");
			String userEmail = mr.getParameter("userEmail");
			String fileName = mr.getFilesystemName("fileName");
		
			//System.out.println("post");

			UserDAO userDAO = new UserDAO();
			
			int result = userDAO.profile(userPW, userName, userEmail, fileName, userID);
			if (result == -1) {
				return "redirect:profile.do?msg=DatabaseError";
			}else {
				HttpSession session = request.getSession();
				
				session.setAttribute("userID", userID);
				session.setAttribute("userName", userName);
				session.setAttribute("fileName", fileName);
				
				return "redirect:index.do?msg=Success";
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "redirect:profile.do?msg=Error";
	}

}
