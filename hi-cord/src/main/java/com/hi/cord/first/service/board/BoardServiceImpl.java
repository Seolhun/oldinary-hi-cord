package com.hi.cord.first.service.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.board.Board;
import com.hi.cord.first.repository.board.BoardDAO;


@Transactional
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	protected BoardDAO boardDao;
	
	@Override
	public void save(Board board) {
		log.info("param : "+board.toString());
		boardDao.save(board);
	}

	@Override
	public List<Board> findAll(Board board) {
		log.info("param : "+board.toString());
		
		List<Board> boardList = boardDao.findAll(board);
		log.info("return : "+boardList.toString());
		return boardList;
	}
	
	@Override
	public Board findById(Long id) {
		log.info("param : "+id.toString());
		
		Board board=boardDao.findById(id);
		log.info("return : "+board.toString());
		return board;
	}

	@Override
	public Boolean delete(Long id) {
		log.info("param : "+id.toString());
		
		Board dbBoard = boardDao.findById(id);
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
		Board dbBoard = boardDao.findById(board.getBoardId());
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
