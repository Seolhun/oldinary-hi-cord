package com.hi.cord.first.repository.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.user.User;
import com.hi.cord.first.repository.AbstractDao;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(Long id) {
		User user = getByKeyByLong(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByEmail(String email) {
		logger.info("email : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByPhone(String phone) {
		logger.info("phone : {}", phone);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("phone", phone));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(Paging paging) {
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String entityName = paging.getTableName();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} else if (paging.getSType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
		} 

		return users;
	}

	public void save(User user) {
		persist(user);
	}
	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "WHERE state='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM USER " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	// Criteria는 무엇인가?
	public void deleteByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

}
