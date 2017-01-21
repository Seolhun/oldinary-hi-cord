package com.brain.home.repository.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.brain.home.entity.board.Board;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.AbstractDao;

@Repository("boardRepository")
public class BoardDAOImpl extends AbstractDao<Integer, Board> implements BoardDAO {
	
	static final String TABLENAME= "BOARD";
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

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getEntityName() != null) {
			condition = "WHERE BOARD_TYPE='" + paging.getEntityName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + TABLENAME + " " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Board> findAll(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String entityName = paging.getEntityName();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		//where절
		criteria.add(Restrictions.eq("boardType", entityName));
		criteria.add(Restrictions.eq("delCheck", 1));
		
		List<Board> boards = (List<Board>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} else if (paging.getSType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
		}
		return boards;
	}

}
