package com.hi.cord.first.user.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.club.repository.SportsClubRepositoryImpl;
import com.hi.cord.first.user.entity.UserAttempts;

@Repository("userAttemptsRepository")
public class UserAttemptsRepositoryImpl extends AbstractRepository<Integer, UserAttempts> implements UserAttemptsRepository {
	static final Logger log = LoggerFactory.getLogger(SportsClubRepositoryImpl.class);
	
	@Override
	public void insert(UserAttempts userAttempts) {
		persist(userAttempts);
	}
	
	@Override
	public UserAttempts selectById(Long id) {
		return getByKeyByLong(id);
	}
	
	@Override
	public UserAttempts selectByEmail(String email) {
		log.info("Parameter : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userAttemptsEmail", email))
			.addOrder(Order.desc("userAttemptsCreatedDate"))
			.setMaxResults(1);
		UserAttempts userAttempts = (UserAttempts)crit.uniqueResult();
		return userAttempts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAttempts> selectList(UserAttempts userAttempts) {
		log.info("param : "+userAttempts.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("userAttemptsId")).add(Restrictions.eq("userAttemptsDelCheck", 0)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<UserAttempts> attempts = criteria.list();
		
		log.info("return : "+attempts.toString());
		return attempts;
	}
	
	@Override
	public boolean delete(Long id) {
		return delete(id);
	}

}
