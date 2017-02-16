package com.hi.cord.first.repository.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.board.Board;
import com.hi.cord.first.repository.AbstractDao;

@Repository("boardDao")
public class BoardDAOImpl extends AbstractDao<Long, Board> implements BoardDAO {
	static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAll(Board board) {
		log.info("TEST : findAll"+board.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("boardId")).add(Restrictions.eq("boardDelCheck", 0)).add(Restrictions.eq("boardType", board.getBoardType()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Board> boards =criteria.list();
		log.info("Parameter : "+boards.toString());
		return boards;
	}
	
	@Override
	public Board findById(Long id) {
		Board board = getByKey(id);
		return board;
	}

	@Override
	public void save(Board board) {
		persist(board);
	}

	@Override
	public void delete(Long id) {
		delete(id);
	}

	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "BOARD WHERE BOARD_TYPE='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
//	@Override
//	public int getCount2(Paging paging) {
//		// 검색 로직
//		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		int count=((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		return count;
//	}
}
