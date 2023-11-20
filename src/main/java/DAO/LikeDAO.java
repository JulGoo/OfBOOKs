package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Config.DatabaseUtil;

public class LikeDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 좋아요 눌렀을 때
	public int likeUp(int ISBN, String userID) {
		String SQL = "INSERT INTO like_table (ISBN, userID) VALUES (?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, ISBN);
			pstmt.setString(2, userID);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
