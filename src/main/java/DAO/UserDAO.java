package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseUtil;

public class UserDAO {
	Connection conn = DatabaseUtil.getConnection();
	
	//회원가입
	public int register(String userID, String userPW, String userName, String userEmail, String userPhone, String userGender) {
		String SQL = "INSERT INTO user_table VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			pstmt.setString(3, userName);
			pstmt.setString(4, userEmail);
			pstmt.setString(5, userPhone);
			pstmt.setString(6, userGender);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//로그인 - 사용자의 이름과 프로필사진을 반환하기위해 String으로 선언
	public String login(String userID, String userPW) {
		String SQL = "SELECT userPW, userName FROM user_table where userID = ?";
		ResultSet rs = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPW)) {
					return rs.getString(2);
				} else
					return "a"; //비밀번호 오류
			} else
				return "b"; // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "c"; // 데이터배이스 오류
	}

}
