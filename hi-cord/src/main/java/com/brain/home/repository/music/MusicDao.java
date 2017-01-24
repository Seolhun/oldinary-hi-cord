package com.brain.home.repository.music;

import java.util.List;

import com.brain.home.entity.music.Music;

public interface MusicDao {

	Music findById(Long id);

	List<Music> findAllMusics();

	void saveMusic(Music music);

	void deleteMusicById(Long id);
}
