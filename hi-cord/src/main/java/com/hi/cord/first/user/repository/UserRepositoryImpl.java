package com.hi.cord.first.user.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.user.entity.User;

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractRepository<Integer, User> implements UserRepository {
	static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

	public User selectById(Long id) {
		log.info("param : selectById : {}", id);
		User user = getByKeyByLong(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getUserPaidForList());
		}
		return user;
	}

	public User selectByEmail(String email) {
		log.info("param : selectByEmail : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userEmail", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getUserPaidForList());
		}
		return user;
	}

	public User selectByPhone(String phone) {
		log.info("param : selectByPhone : {}", phone);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userPhone", phone));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getUserPaidForList());
		}
		return user;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(User user) {
		log.info("param : findAllUsers : {}", user);
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("userId"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	public void save(User user) {
		log.info("param : save : {}", user.toString());
		persist(user);
	}
	
	// Criteria는 무엇인가?
	public void deleteByEmail(String email) {
		log.info("param : deleteByEmail : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userEmail", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

}
