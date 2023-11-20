package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Config.DatabaseUtil;

public class BoardDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 게시글 작성
	public int write(String title, String content, String userID, String fileName) {
		String SQL = "INSERT INTO board_table (title, content, userID, fileName) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, userID);
			pstmt.setString(4, fileName);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류

	}

	// 게시글 수정
	public int update(int bbsNo, String title, String content, String fileName) {
		String SQL = "UPDATE board_table SET title =?, content = ?,fileName = ? Where bbsNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, fileName);
			pstmt.setInt(4, bbsNo);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//게시글 논리 삭제 (유효번호 1 -> 0 으로 변경)
	public int delete(int bbsNo) {
		String SQL = "UPDATE board_table SET available = 0 Where bbsNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsNo);
			
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
