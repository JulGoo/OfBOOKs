package Service.SearchBook;

import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}
	
	//메인 도석 검색 화면
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {		
		return "index";
	}
}
