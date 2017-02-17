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
		Music entity = dao.findById(music.getMusicId());
		if (entity != null) {
			entity.setMusicSinger(music.getMusicSinger());
			entity.setMusicTitle(music.getMusicTitle());
			entity.setMusicCreatedDate(music.getMusicCreatedDate());
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
