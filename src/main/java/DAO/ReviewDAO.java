package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Config.DatabaseUtil;
import DTO.ReviewDTO;

public class ReviewDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 댓글 작성
	public int write(String isbn, String userID, String comment, String fileName) {
		String SQL = "INSERT INTO review_table (isbn, userID, comment) VALUES (?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, isbn);
			pstmt.setString(2, userID);
			pstmt.setString(3, comment);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	// 댓글 논리 삭제 (유효번호 1 -> 0 으로 변경)
	public int delete(int reviewNo) {
		String SQL = "UPDATE review_table SET available = 0 where reviewNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reviewNo);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 댓글 목록 불러오기
	public ArrayList<ReviewDTO> getReviewList(String isbn) {
		String SQL = "select review.*, user.fileName from review_table as review join user_table as user on review.userID = user.userID	where review.isbn = ? and available = 1";
		ResultSet rs;
		ArrayList<ReviewDTO> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setReviewNo(rs.getInt(1));
				reviewDTO.setIsbn(rs.getString(2));
				reviewDTO.setUserID(rs.getString(3));
				reviewDTO.setComment(rs.getString(4));
				reviewDTO.setDate(rs.getDate(5));
				reviewDTO.setFileName(rs.getString(7));

				list.add(reviewDTO);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
