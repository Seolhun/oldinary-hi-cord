package com.brain.home.repository.user;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.user.PersistentLogin;
import com.brain.home.repository.AbstractDao;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {
	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setEmail(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLatestdate(token.getDate());
		persist(persistentLogin);
	}

	// persistent Login 부
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getEmail(), persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLatestdate());
		} catch (Exception e) {
			logger.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String email) {
		logger.info("Removing Token if any for user : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
		if (persistentLogin != null) {
			logger.info("rememberMe was selected");
			delete(persistentLogin);
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLatestdate(lastUsed);
		update(persistentLogin);
	}

}