package com.hi.cord.first.user.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.user.entity.UserProfile;

@Repository("userProfileRepository")
public class UserProfileRepositoryImpl extends AbstractRepository<Integer, UserProfile> implements UserProfileRepository {

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userProfileType", type));
		return (UserProfile) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("userProfileId"));
		return (List<UserProfile>) crit.list();
	}
}
