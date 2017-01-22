package com.brain.home.repository.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.AbstractDao;

@Repository("boardDao")
public class BoardDAOImpl extends AbstractDao<Integer, Board> implements BoardDAO {
	
	static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);

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

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAll(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String tableName = paging.getTableName();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		//where절
		criteria.add(Restrictions.eq("boardType", tableName));
		criteria.add(Restrictions.eq("delCheck", 0));
		
		List<Board> boards = (List<Board>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (tableName != null) {
			criteria.add(Restrictions.eq("boardType", tableName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} 
		return boards;
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

	@Override
	public int getCount2(Paging paging) {
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		int count=((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return count;
	}

}
