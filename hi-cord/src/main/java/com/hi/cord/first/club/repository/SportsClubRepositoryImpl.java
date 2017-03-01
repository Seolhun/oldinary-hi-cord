package com.hi.cord.first.club.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.repository.AbstractRepository;
import com.hi.cord.first.club.entity.SportsClub;

@Repository("sportsClubRepository")
public class SportsClubRepositoryImpl extends AbstractRepository<Long, SportsClub> implements SportsClubRepository {
	static final Logger log = LoggerFactory.getLogger(SportsClubRepositoryImpl.class);

	@Override
	public void insert(SportsClub sportsClub) {
		persist(sportsClub);
	}
	
	@Override
	public SportsClub findById(Long id) {
		SportsClub sportsClub = getByKeyByLong(id);
		if (sportsClub != null) {
			Hibernate.initialize(sportsClub.getSportsClubWithUser());
		}
		return sportsClub;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SportsClub> findAll(SportsClub sportsClub) {
		log.info("TEST : findAll"+sportsClub.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("stadiumId")).add(Restrictions.eq("stadiumDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SportsClub> SportsClubs =criteria.list();
		log.info("Parameter : "+SportsClubs.toString());
		return SportsClubs;
	}
	
	@Override
	public boolean delete(Long id) {
		return delete(id);
	}
}
