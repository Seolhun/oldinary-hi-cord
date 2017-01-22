package com.brain.home.repository.board;

import java.util.List;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;

public interface BoardDAO {
	
	Board findById(Long id);
	
	void save(Board board);

	void delete(Long id);
	
	List<Board> findAll(Paging paging);
	
	int getCount(Paging paging);
	
	int getCount2(Paging paging);

}