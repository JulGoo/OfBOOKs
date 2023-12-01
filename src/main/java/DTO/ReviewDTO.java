package DTO;

import java.sql.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewDTO {
	int reviewNo;
	String isbn;
	String userID;
	String comment;
	Date date;
	int available;
	String profileImg;
}
