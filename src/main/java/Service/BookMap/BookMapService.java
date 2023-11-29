package Service.BookMap;

import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookMapService implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}
	
	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		
		return "bookMap";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {		
		return "bookMap";
	}
}
