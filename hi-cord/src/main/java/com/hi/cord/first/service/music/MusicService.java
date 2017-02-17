package com.hi.cord.first.service.music;

import java.util.List;

import com.hi.cord.first.entity.music.Music;

public interface MusicService {
	
	Music findById(Long id);
	
	void saveMusic(Music music);
	
	void updateMusic(Music music);
	
	void deleteUserById(Long id);

	List<Music> findAllMusics(); 
}