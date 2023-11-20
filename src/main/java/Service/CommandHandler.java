package Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandHandler {
	public String process(HttpServletRequest request, HttpServletResponse response);
}
