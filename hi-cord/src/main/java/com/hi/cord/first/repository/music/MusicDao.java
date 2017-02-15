package com.hi.cord.first.repository.music;

import java.util.List;

import com.hi.cord.first.entity.music.Music;

public interface MusicDao {

	Music findById(Long id);

	List<Music> findAllMusics();

	void saveMusic(Music music);

	void deleteMusicById(Long id);
}
