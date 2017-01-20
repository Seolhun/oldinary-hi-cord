package com.brain.home.service.board;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {

	public Board findById(Long id);

	public void save(Board board);

	public Board update(Board board);

	public Boolean delete(Long id);

	int getCount(Paging paging);

	public List<Board> findAll(Paging paging);

}
