package com.hi.cord.first.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.board.Board;
import com.hi.cord.common.model.Paging;
import com.hi.cord.first.repository.board.BoardDAO;


@Transactional
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	protected BoardDAO boardDao;

	@Override
	public List<Board> findAll(Board board) {
		return boardDao.findAll(board);
	}
	
	@Override
	public Board findById(Long id) {
		return boardDao.findById(id);
	}
	
	@Override
	public void save(Board board) {
		boardDao.save(board);
	}

	@Override
	public Boolean delete(Long id) {
		Board temp = boardDao.findById(id);
		
		if (temp != null) {
			boardDao.delete(temp.getBoardId());
			return true;
		}
		return false;
	}

	@Override
	public Board update(Board board) {
		Board entity = boardDao.findById(board.getBoardId());
		if (entity != null) {
			entity.setBoardSubject(board.getBoardSubject());
			entity.setBoardContent(board.getBoardContent());
		}
		return entity;
	}

	@Override
	public int getCount(Paging paging) {
		return boardDao.getCount(paging);
	}

}
