package com.hi.cord.first.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hi.cord.first.entity.music.Music;
import com.hi.cord.first.repository.music.MusicDao;

@Service("musicService")
@Transactional
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicDao dao;

	public Music findById(Long id) {
		return dao.findById(id);
	}

	public void saveMusic(Music music) {
		dao.saveMusic(music);
	}

	public void updateMusic(Music music) {
		Music entity = dao.findById(music.getId());
		if (entity != null) {
			entity.setSinger(music.getSinger());
			entity.setTitle(music.getTitle());
			entity.setCreatedDate(music.getCreatedDate());
		}
	}

	@Override
	public void deleteUserById(Long id) {
		dao.deleteMusicById(id);
	}

	@Override
	public List<Music> findAllMusics() {
		return dao.findAllMusics();
	}
}
