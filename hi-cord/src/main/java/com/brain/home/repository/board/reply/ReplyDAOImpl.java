package com.brain.home.repository.board.reply;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.brain.home.entity.board.Reply;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.AbstractDao;

@Repository("replyRepository")
public class ReplyDAOImpl extends AbstractDao<Integer, Reply> implements ReplyDAO {
	static final String TABLENAME= "REPLY";
	static final Logger logger = LoggerFactory.getLogger(ReplyDAOImpl.class);

	@Override
	public Reply findById(Long id) {
		Reply reply = getByKey(id);
		return reply;
	}

	@Override
	public void save(Reply reply) {
		persist(reply);		
	}

	@Override
	public void delete(Long id) {
		delete(id);
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getEntityName() != null) {
			condition = "WHERE REPLY_TYPE='" + paging.getEntityName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + TABLENAME + " " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findAll(Paging paging) {
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String entityName = paging.getEntityName();
		String pfName = paging.getPfName();
		
//		// 검색 로직
//		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
//				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		//where절
		Criteria criteria = createEntityCriteria().add(Restrictions.eq("type", entityName));
		criteria.add(Restrictions.eq("delCheck", 1));
		
		List<Reply> replys = (List<Reply>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (pfName != null) {
			criteria.add(Restrictions.eq("pfName", pfName));
		}

//		if (paging.getSType() != 0 && sType == 1) {
//			criteria.add(Restrictions.like("email", "%" + sText + "%"));
//		} else if (paging.getSType() != 0 && sType == 2) {
//			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
//		}
		return replys;
	}


}
