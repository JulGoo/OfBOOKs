package Service.SearchBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Config.BookAPI;
import Config.LibraryAPI;
import DAO.LikeDAO;
import DAO.ReviewDAO;
import DTO.BookDTO;
import DTO.LibraryDTO;
import DTO.ReviewDTO;
import Service.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookInfoService implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if ("GET".equals(request.getMethod())) {
			return processGet(request, response);
		} else {
			return processPost(request, response);
		}
	}

	private String processGet(HttpServletRequest request, HttpServletResponse response) {
		// 1. isbn으로 검색
		String paramIsbn = request.getParameter("isbn");
		
		System.out.println(paramIsbn);
		
		/*** BookAPI : ISBN으로 책 정보 가져오기 ***/

		// 2. api로 사이트 접속해서 json 결과값 가져오기
		BookAPI bookAPI = new BookAPI();
		String jsonStr = bookAPI.getBookInfo(paramIsbn);

		// JSON 파서 생성
		JSONParser parser = new JSONParser();

		try {
			// JSON 문자열을 파싱하여 JSONObject 생성
			JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);

			// 'documents' 키로부터 JSONArray 추출
			JSONArray documents = (JSONArray) jsonObject.get("documents");

			for (Object documentObj : documents) {
				JSONObject document = (JSONObject) documentObj;

				String title = (String) document.get("title");
				JSONArray authorsArray = (JSONArray) document.get("authors");
				// authorsArray를 String 배열로 변환
				String[] authors = Arrays.copyOf(authorsArray.toArray(), authorsArray.size(), String[].class);
				String author = String.join(", ", authors);

				String contents = (String) document.get("contents");
				String publisher = (String) document.get("publisher");

				// 날짜 변환
				String datetimeString = (String) document.get("datetime");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date datetime = dateFormat.parse(datetimeString);

				// sale_price가 null이면 기본값인 0으로 처리, 값이 없을 경우 -1로 받아옴 -> 0으로 처리
				int sale_price = document.get("sale_price") != null ? ((Long) document.get("sale_price")).intValue()
						: 0;
				if (sale_price < 0) {
					sale_price = 0;
				}

				String thumbnail = (String) document.get("thumbnail");
				// 표지가 없으면 기본 이미지 세팅
				if (thumbnail == null) {
					thumbnail = "img/default.png";
				}

				String isbn = (String) document.get("isbn");
				//ISBN10 또는 ISBN13 중 하나 이상 포함될 경우 ISBN13을 가져온다.
				if (isbn.length() > 15) {
					isbn = isbn.substring(11);
				}

				// DTO에 담기
				BookDTO bookDTO = new BookDTO();
				bookDTO.setTitle(title);
				bookDTO.setAuthor(author);
				bookDTO.setContents(contents);
				bookDTO.setPublisher(publisher);
				bookDTO.setDatetime(datetime);
				bookDTO.setSale_price(sale_price);
				bookDTO.setThumbnail(thumbnail);
				bookDTO.setIsbn(isbn);

				// System.out.println(title);

				request.setAttribute("bookDTO", bookDTO);

				System.out.println(bookDTO.toString() + "******");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*** LibraryAPI : ISBN으로 소장 도서관 정보 가져오기 ***/
		LibraryAPI libraryAPI = new LibraryAPI();
		String jsonString = libraryAPI.getLibrary(paramIsbn);

		/*************
		 * 소장 도서관 검색 API가 출판 2019년작까지 결과 출력 -> 2019년 이후 출판작일 경우 예외처리
		 **************/
		if (jsonString.contains("error") || jsonString.contains("정보나루")) {
			// System.out.println(jsonString);
			return "bookInfo";
		}
		// System.out.println(jsonString);

		// JSON 파서 생성
		JSONParser parser1 = new JSONParser();

		try {
			// JSON 문자열을 파싱하여 JSONObject 생성
			JSONObject jsonObject = (JSONObject) parser1.parse(jsonString);

			// System.out.println(jsonObject.toString()+"------------");

			// "response" 객체에서 "libs" 배열 추출
			JSONObject responseL = (JSONObject) jsonObject.get("response");
			JSONArray libs = (JSONArray) responseL.get("libs");

			// System.out.println("_)(*&%^&*()_)(*&^"+libs);

			// LibraryDTO 리스트 생성
			ArrayList<LibraryDTO> libraryDTOList = new ArrayList<>();

			// 각 문서에 대한 정보를 출력
			for (Object libObj : libs) {
				JSONObject lib = (JSONObject) libObj;

				JSONObject libInfo = (JSONObject) lib.get("lib");

				String libName = (String) libInfo.get("libName");
				String address = (String) libInfo.get("address");
				String homepage = (String) libInfo.get("homepage");
				String latitudeString = (String) libInfo.get("latitude");
				String longitudeString = (String) libInfo.get("longitude");

				// String -> Double (카카오맴 api 사용)
				Double latitude = Double.valueOf(latitudeString);
				Double longitude = Double.valueOf(longitudeString);

				// DTO에 정보 담기
				LibraryDTO libraryDTO = new LibraryDTO();
				libraryDTO.setLibName(libName);
				libraryDTO.setAddress(address);
				libraryDTO.setHomepage(homepage);
				libraryDTO.setLatitude(latitude);
				libraryDTO.setLongitude(longitude);

				libraryDTOList.add(libraryDTO);

//				for (LibraryDTO libraryDTO1 : libraryDTOList) {
//					System.out.println(libraryDTO1);
//				}
			}

			request.setAttribute("libraryDTOList", libraryDTOList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*** 댓글 목록 가져오기 ***/
		ReviewDAO reviewDAO = new ReviewDAO();
		ArrayList<ReviewDTO> reviewDTOList = reviewDAO.getReviewList(paramIsbn);	
		
		request.setAttribute("reviewDTOList", reviewDTOList);
		
		/*** 추천 총 개수 가져오기 ***/
		LikeDAO likeDAO = new LikeDAO();
		int likeCnt = likeDAO.likeCount(paramIsbn);
		
		request.setAttribute("likeCnt", likeCnt);

		return "bookInfo";
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		return "bookInfo";
	}
}
