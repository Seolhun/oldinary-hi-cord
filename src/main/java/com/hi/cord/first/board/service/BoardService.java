package com.hi.cord.first.board.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.board.entity.Board;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {
	public void insert(Board board);

	public List<Board> selectList(Board board);
	
	public Board selectById(Long id);

	public Board update(Board board);

	public Boolean delete(Long id);

	int getCount(Paging paging);
}
