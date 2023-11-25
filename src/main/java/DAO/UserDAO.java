package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.DatabaseUtil;
import DTO.UserDTO;

public class UserDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 회원가입
	public int register(String userID, String userPW, String userName, String userEmail) {
		String SQL = "INSERT INTO user_table (userID, userPW, userName, userEmail) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			pstmt.setString(3, userName);
			pstmt.setString(4, userEmail);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 로그인
	public int login(String userID, String userPW) {
		String SQL = "SELECT userPW, userName FROM user_table where userID = ?";
		ResultSet rs = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPW)) {
					return 1;
				} else {
					return 0; // 비밀번호 오류
				}
			} else {
				return -1; // 아이디 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터배이스 오류
	}

	// 로그인 정보 세션 저장위해 생성
	public UserDTO logintoSession(String userID) {
		String SQL = "SELECT * FROM user_table where userID = ?";
		ResultSet rs = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserID(rs.getString(1));
				userDTO.setUserName(rs.getString(3));
				userDTO.setUserEmail(rs.getString(4));
				userDTO.setFileName(rs.getString(5));

				return userDTO;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 프로필 수정
	public int profile(String userPW, String userName, String userEmail, String fileName, String userID) {
		String SQL = "UPDATE user_table SET userPW = ?, userName = ?, userEmail = ?, fileName = ? where userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userPW);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			pstmt.setString(4, fileName);
			pstmt.setString(5, userID);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}
