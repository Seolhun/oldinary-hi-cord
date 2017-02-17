package com.hi.cord.first.repository.music;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.first.entity.music.Music;
import com.hi.cord.first.repository.AbstractDao;

@Repository("musicDao")
public class MusicDaoImpl extends AbstractDao<Integer, Music> implements MusicDao {

	static final Logger logger = LoggerFactory.getLogger(MusicDaoImpl.class);
	
	public Music findById(Long id) {
		Music music = getByKeyByLong(id);
		return music;
	}
	
	@SuppressWarnings("unchecked")
	public List<Music> findAllMusics() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Music> musics =(List<Music>) criteria.list();
		return musics;
	}

	public void saveMusic(Music music) {
		persist(music);
	}

	public void deleteMusicById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Music music = (Music)crit.uniqueResult();
		delete(music);
	}
}