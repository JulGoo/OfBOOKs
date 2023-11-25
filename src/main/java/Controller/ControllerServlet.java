package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Service.CommandHandler;

@WebServlet(urlPatterns = "*.do", initParams = {
		@WebInitParam(name = "config", value = "/WEB-INF/commandHandler.properties") })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// <커멘드, 핸들러인스턴스> 매핑 정보 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	// 서블릿 생성하고 초기화할 때 가장 먼저 호출되는 init() 메소드 이용
	public void init(ServletConfig config) throws ServletException {
		String configFile = config.getInitParameter("config");
		Properties prop = new Properties();
		String configFilePath = config.getServletContext().getRealPath(configFile);

		try (FileInputStream fis = new FileInputStream(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}

		// 속성 파일을 하나씩 읽어 키값과 실행명령 핸들러명을 맵에 저장
		Iterator<Object> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getRequestURI();
		command = command.substring(request.getContextPath().length() + 1);

		CommandHandler handler = commandHandlerMap.get(command);

		String viewPage = handler.process(request, response);

		System.out.println(viewPage);

		// viewpage -> data
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(viewPage);
			return;
		}

		// viewpage -> page
		if (viewPage.startsWith("redirect:")) {
			String newURL = viewPage.substring(9);
			System.out.println(newURL);
			response.sendRedirect(newURL);
			System.out.println("redirect");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + viewPage + ".jsp");
			rd.forward(request, response);
			System.out.println("forward");
		}
	}
}
