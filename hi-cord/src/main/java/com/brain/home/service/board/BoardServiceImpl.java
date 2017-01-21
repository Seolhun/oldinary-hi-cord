package com.brain.home.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.board.BoardDAO;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	protected BoardDAO dao;

	@Override
	public void save(Board board) {
		dao.save(board);
	}

	@Override
	public Boolean delete(Long id) {
		Board temp = dao.findById(id);
		
		if (temp != null) {
			dao.delete(temp.getId());
			return true;
		}
		return false;
	}

	@Override
	public Board update(Board board) {
		Board entity = dao.findById(board.getId());
		if (entity != null) {
			entity.setSubject(board.getSubject());
			entity.setContent(board.getContent());
		}
		return entity;
	}

	@Override
	public Board findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Board> findAll(Paging paging) {
		return dao.findAll(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return dao.getCount(paging);
	}

}
