package com.hi.cord.first.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.board.entity.Board;
import com.hi.cord.first.board.repository.BoardDAO;


@Transactional
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	protected BoardDAO boardDao;
	
	@Override
	public void insert(Board board) {
		log.info("param : "+board.toString());
		boardDao.insert(board);
	}

	@Override
	public List<Board> selectList(Board board) {
		log.info("param : "+board.toString());
		
		List<Board> boardList = boardDao.selectList(board);
		log.info("return : "+boardList.toString());
		return boardList;
	}
	
	@Override
	public Board selectById(Long id) {
		log.info("param : "+id.toString());
		
		Board board=boardDao.selectById(id);
		log.info("return : "+board.toString());
		return board;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		
		Board dbBoard = boardDao.selectById(id);
		log.info("return : "+dbBoard);
		if (dbBoard != null) {
			boardDao.delete(dbBoard.getBoardId());
			return true;
		}
		return false;
	}

	@Override
	public Board update(Board board) {
		log.info("param : "+board.toString());
		Board dbBoard = boardDao.selectById(board.getBoardId());
		log.info("return : "+dbBoard);
		if (dbBoard != null) {
			dbBoard.setBoardSubject(board.getBoardSubject());
			dbBoard.setBoardContent(board.getBoardContent());
		}
		return dbBoard;
	}

	@Override
	public int getCount(Paging paging) {
		log.info("param : "+paging.toString());
		
		int count=boardDao.getCount(paging);
		log.info("return : "+count);
		return count;
	}

}
