package com.hi.cord.first.board.repository;

import java.util.List;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.board.entity.Board;

public interface BoardDAO {
	
	List<Board> selectList(Board board);
	
	Board selectById(Long id);
	
	void insert(Board board);

	void delete(Long id);
	
	int getCount(Paging paging);
}
