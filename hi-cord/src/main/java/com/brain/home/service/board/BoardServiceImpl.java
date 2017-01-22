package com.brain.home.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.board.BoardDAO;


@Transactional
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	protected BoardDAO boardDao;

	@Override
	public void save(Board board) {
		boardDao.save(board);
	}

	@Override
	public Boolean delete(Long id) {
		Board temp = boardDao.findById(id);
		
		if (temp != null) {
			boardDao.delete(temp.getId());
			return true;
		}
		return false;
	}

	@Override
	public Board update(Board board) {
		Board entity = boardDao.findById(board.getId());
		if (entity != null) {
			entity.setSubject(board.getSubject());
			entity.setContent(board.getContent());
		}
		return entity;
	}

	@Override
	public Board findById(Long id) {
		return boardDao.findById(id);
	}

	@Override
	public List<Board> findAll(Paging paging) {
		return boardDao.findAll(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return boardDao.getCount(paging);
	}

}
