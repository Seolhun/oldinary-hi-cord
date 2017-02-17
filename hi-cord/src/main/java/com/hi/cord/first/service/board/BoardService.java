package com.hi.cord.first.service.board;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.board.Board;
import com.hi.cord.common.model.Paging;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {

	public List<Board> findAll(Board board);
	
	public Board findById(Long id);

	public void save(Board board);

	public Board update(Board board);

	public Boolean delete(Long id);

	int getCount(Paging paging);
}
