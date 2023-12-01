package DTO;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class BookDTO {
	private String title;
	private String author;
	private String contents; // 도서 소개
	private String publisher;
	private Date datetime;// 출판 날짜
	private int sale_price; // 도서 판매가
	private String thumbnail; // 도서 표지 URL
	private String isbn;// ISBN
}
