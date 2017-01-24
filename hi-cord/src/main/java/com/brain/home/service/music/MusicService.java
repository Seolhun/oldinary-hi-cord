package com.brain.home.service.music;

import java.util.List;

import com.brain.home.entity.music.Music;

public interface MusicService {
	
	Music findById(Long id);
	
	void saveMusic(Music music);
	
	void updateMusic(Music music);
	
	void deleteUserById(Long id);

	List<Music> findAllMusics(); 
}