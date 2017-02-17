package com.hi.cord.first.repository.board.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.first.entity.board.Comment;
import com.hi.cord.common.model.Paging;
import com.hi.cord.first.repository.AbstractDao;

@Repository("commentRepository")
public class CommentDAOImpl extends AbstractDao<Integer, Comment> implements CommentDAO {
	
	static final String TABLENAME= "BOARD";
	static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);

	@Override
	public Comment findById(Long id) {
		Comment comment = getByKeyByLong(id);
		return comment;
	}

	@Override
	public void save(Comment comment) {
		persist(comment);
	}

	@Override
	public void delete(Long id) {
		delete(id);
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "WHERE BOARD_TYPE='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + TABLENAME + " " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findAll(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String entityName = paging.getTableName();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		//where절
		criteria.add(Restrictions.eq("commentType", entityName));
		criteria.add(Restrictions.eq("delCheck", 1));
		
		List<Comment> comments = (List<Comment>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} else if (paging.getSType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
		}
		return comments;
	}

}
