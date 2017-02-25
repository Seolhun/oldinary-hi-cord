package com.hi.cord.first.user.repository;

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
import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.user.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractRepository<Integer, User> implements UserDao {
	static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(Long id) {
		User user = getByKeyByLong(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByEmail(String email) {
		log.info("Parameter : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userEmail", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByPhone(String phone) {
		log.info("Parameter : {}", phone);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userPhone", phone));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(Paging paging) {
		log.info("Parameter : {}", paging);
		int cPage = paging.getCPage();
		int sType = paging.getSType();
		String sText = paging.getSText();
		int limit = paging.getLimit();
		String entityName = paging.getTableName();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("userId")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("userEmail", "%" + sText + "%"));
		} else if (paging.getSType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("userNickname", "%" + sText + "%"));
		} 

		return users;
	}

	public void save(User user) {
		log.info("Parameter : {}", user.toString());
		persist(user);
	}
	
	@Override
	public int getCount(Paging paging) {
		log.info("Parameter : {}", paging.toString());
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "WHERE state='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM USER " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	// Criteria는 무엇인가?
	public void deleteByEmail(String email) {
		log.info("Parameter : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userEmail", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

}
