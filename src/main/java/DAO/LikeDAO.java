package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseUtil;

public class LikeDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 추천
	public int likeUp(String isbn, String userID) {
		String SQL = "INSERT INTO like_table (ISBN, userID) VALUES (?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, isbn);
			pstmt.setString(2, userID);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 추천 취소
	public int likeDown(String isbn, String userID) {
		String SQL = "Delete from like_table where isbn = ? and userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, isbn);
			pstmt.setString(2, userID);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 추천 수 가져오기
	public int likeCount(String isbn) {
		String SQL = "Select COUNT(*) from like_table where isbn = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 추천 여부
	public boolean isLiked(String isbn, String userID) {
		String SQL = "Select * from like_table where isbn = ? and userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, isbn);
			pstmt.setString(2, userID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
