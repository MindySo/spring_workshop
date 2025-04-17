package com.ssafy.exam.model.service;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.exam.model.dao.BoardDao;
import com.ssafy.exam.model.dto.Board;
import com.ssafy.exam.model.dto.BoardFile;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Value("${file.upload.directory}")
	private String uploadDir;
	
	private final BoardDao boardDao;
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public void write(Board board) throws Exception {
		int no = boardDao.insertBoard(board);
		
		// 파일을 업로드 요청했다면 파일 정보를 
		List<MultipartFile> attachList = board.getAttach();
//		if (attach == null) // 화면에서 attach 파라미터 자체가 없는 경우
		// 사용자가 파일을 선택 했다면..
		for (MultipartFile attach : attachList) {
			if (!attach.isEmpty()) {
				String originalName = attach.getOriginalFilename();
				long fileSize = attach.getSize();	// byte 크기
				String uploadName = generateUniqueName(originalName);
				File dirFile = new File(uploadDir);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				File file = new File(dirFile, uploadName);
				// 1. 서버에 저장
				attach.transferTo(file);
				
				// 2. 디비에 저장
				BoardFile boardFile = new BoardFile();
				// no, original_name, upload_name, file_size
				boardFile.setNo(board.getNo());
				boardFile.setOriginalName(originalName);
				boardFile.setUploadName(uploadName);
				boardFile.setFileSize(fileSize);
				boardDao.insertBoardFile(boardFile);
			}
		}
		
		
		
	}

	private String generateUniqueName(String originalName) {
		String timeStr = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String uniqueStr = UUID.randomUUID().toString().substring(0, 8);
		int index = originalName.lastIndexOf(".");
		String extName = "";
		if (index != -1) {
			extName = originalName.substring(index);
		}
		return timeStr + "_" + uniqueStr + extName;
	}
	
	
	@Override
	public List<Board> list() throws Exception {
		return boardDao.selectAll();
	}

	@Override
	public Board detail(int no) throws SQLException {
		Board board = boardDao.selectBoard(no);
		List<BoardFile> boardFiles = boardDao.selectBoardFileList(no);
		board.setBoardFiles(boardFiles);
		return board;
	}

	@Override
	public void remove(int no) throws SQLException {
		boardDao.deleteBoard(no);
		
	}

	@Override
	public void update(Board board) throws Exception {
		boardDao.updateBoard(board);
	}

}













