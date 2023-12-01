package Service.SearchBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Config.BookAPI;
import DTO.BookDTO;
import Service.CommandHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookListService implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		// 1. 검색한 도서명 가져오기
		String paramTitle = request.getParameter("title");

		// 2. api로 사이트 접속해서 json 결과값 가져오기
		BookAPI bookAPI = new BookAPI();
		String jsonStr = bookAPI.getBookInfo(paramTitle);

		// JSON 파서 생성
		JSONParser parser = new JSONParser();

		try {
			// JSON 문자열을 파싱하여 JSONObject 생성
			JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);

			// 'documents' 키로부터 JSONArray 추출
			JSONArray documents = (JSONArray) jsonObject.get("documents");

			// BookDTO 리스트 생성
			ArrayList<BookDTO> bookDTOList = new ArrayList<>();

			// 각 문서에 대한 정보를 출력
			for (Object documentObj : documents) {
				JSONObject document = (JSONObject) documentObj;

				String title = (String) document.get("title");
				JSONArray authorsArray = (JSONArray) document.get("authors");
				// authorsArray를 String 배열로 변환
				String[] authors = Arrays.copyOf(authorsArray.toArray(), authorsArray.size(), String[].class);
				//앞 뒤 [] 제거
				String author = String.join(", ", authors);
				
				String publisher = (String) document.get("publisher");

				String thumbnail = (String) document.get("thumbnail");
				// 표지가 없으면 기본 이미지 세팅
				if (thumbnail.equals("")) {
					thumbnail = "img/default.png";
				}

				String isbn = (String) document.get("isbn");
				//System.out.println(isbn);
				//ISBN10 또는 ISBN13 중 하나 이상 포함될 경우 ISBN13을 가져온다.
				if (isbn.length() > 15) {
					isbn = isbn.substring(11);
				}
				
				System.out.println(isbn);

				// DTO에 담기
				BookDTO bookDTO = new BookDTO();
				bookDTO.setTitle(title);
				bookDTO.setAuthor(author);
				bookDTO.setPublisher(publisher);
				bookDTO.setThumbnail(thumbnail);
				bookDTO.setIsbn(isbn);

				bookDTOList.add(bookDTO);

//				for (BookDTO bookDTO1 : bookDTOList) {
//					System.out.println(bookDTO1);
//				}
				
				request.setAttribute("bookDTOList", bookDTOList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "bookList";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {

		return "bookList";
	}
}
