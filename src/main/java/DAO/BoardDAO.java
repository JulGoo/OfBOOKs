package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Config.DatabaseUtil;
import DTO.BoardDTO;

public class BoardDAO {
	Connection conn = DatabaseUtil.getConnection();

	// 게시글 작성
	public int write(String title, String content, String userID, String fileName) {
		String SQL = "INSERT INTO board_table (title, content, userID, fileName) VALUES (?,?,?,?)";
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
	public int update(String title, String content, String fileName, int bbsNo) {
		String SQL = "UPDATE board_table SET title = ?, content = ?,fileName = ? Where bbsNo = ?";
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

	// 게시글 논리 삭제 (유효번호 1 -> 0 으로 변경)
	public int delete(int bbsNo) {
		String SQL = "UPDATE board_table SET available = 0 Where bbsNo = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsNo);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 게시글 목록 불러오기
	public ArrayList<BoardDTO> getBoardList() {
		String SQL = "SELECT * from board_table where available = 1";
		ResultSet rs;
		ArrayList<BoardDTO> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBbsNo(rs.getInt(1));
				boardDTO.setTitle(rs.getString(2));
				boardDTO.setContent(rs.getString(3));
				boardDTO.setUserID(rs.getString(4));
				boardDTO.setDate(rs.getDate(5));
				boardDTO.setFileName(rs.getString(7));

				list.add(boardDTO);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 게시글 상세 보기
	public BoardDTO getBoardDetail(int bbsNo) {
		String SQL = "SELECT * from board_table where bbsNo = ?";
		ResultSet rs;
		BoardDTO boardDTO = new BoardDTO();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardDTO.setBbsNo(rs.getInt(1));
				boardDTO.setTitle(rs.getString(2));
				boardDTO.setContent(rs.getString(3));
				boardDTO.setUserID(rs.getString(4));
				boardDTO.setFileName(rs.getString(7));
			}
			return boardDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
