package com.hi.cord.first.repository.board;

import java.util.List;

import com.hi.cord.first.entity.board.Board;
import com.hi.cord.common.model.Paging;

public interface BoardDAO {
	
	List<Board> findAll(Board board);
	
	Board findById(Long id);
	
	void save(Board board);

	void delete(Long id);
	
	int getCount(Paging paging);
}
