package com.brain.home.repository.board;

import java.util.List;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;

public interface BoardDAO {
	
	Board findById(Long id);
	
//	User findByEmail(String email);
	
	void save(Board board);

	void delete(Long id);
	
	int getCount(Paging paging);
	
	List<Board> findAll(Paging paging);

}
