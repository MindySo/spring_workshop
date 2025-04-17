package com.ssafy.exam.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardFile;
import com.ssafy.exam.util.DBUtil;

@Repository
public class BoardDaoImpl implements BoardDao {
	private final DBUtil db;
	public BoardDaoImpl(DBUtil db) {
		this.db = db;
	}
	
	@Override
	public int insertBoard(Board board) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement("""
						insert into board(title, content)
						values (?, ?)		
				""", Statement.RETURN_GENERATED_KEYS);
		) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}
	
	@Override
	public int insertBoardFile(BoardFile boardFile) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement("""
						insert into board_file(no, original_name, upload_name, file_size)
						values (?, ?, ?, ?)		
				""");
				) {
			int idx = 1;
			pstmt.setInt(idx++, boardFile.getNo());
			pstmt.setString(idx++, boardFile.getOriginalName());
			pstmt.setString(idx++, boardFile.getUploadName());
			pstmt.setLong(idx++, boardFile.getFileSize());
			return pstmt.executeUpdate();
		}
	}

	@Override
	public List<Board> selectBoard() throws SQLException {
		try (
			Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("""
				select no, title, content, view_cnt, 
				       date_format(reg_date, '%y.%m.%d') reg_date
				  from board
				 order by no desc
			""");
		) {
			List<Board> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRegDate(rs.getString("reg_date"));
				list.add(board);
			}
			return list;
		}
	}

	@Override
	public Board selectDetail(int no) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement("""
					select no, title, content, view_cnt, 
					       date_format(reg_date, '%y.%m.%d') reg_date
					  from board
					 where no = ?
				""");
			) {
				pstmt.setInt(1, no);
				ResultSet rs = pstmt.executeQuery();
				Board board = null;
				if (rs.next()) {
					board = new Board();
					board.setNo(rs.getInt("no"));
					board.setTitle(rs.getString("title"));
					board.setContent(rs.getString("content"));
					board.setViewCnt(rs.getInt("view_cnt"));
					board.setRegDate(rs.getString("reg_date"));
				}
				return board;
			}
	}

	@Override
	public List<BoardFile> selectBoardFileList(int no) throws SQLException {
		try (
				Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement("""
					select no, board_no, original_name, upload_name, file_size 
					from board_file 
					where no = ?
				""");
			) {
				pstmt.setInt(1, no);
				List<BoardFile> list = new ArrayList<>();
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardFile boardFile = new BoardFile();
					boardFile.setNo(rs.getInt("no"));
					boardFile.setFileNo(rs.getInt("board_no"));
					boardFile.setOriginalName(rs.getString("original_name"));
					boardFile.setUploadName(rs.getString("upload_name"));
					boardFile.setFileSize(rs.getLong("file_size"));
					list.add(boardFile);
				}
				return list;
			}
	}

}












