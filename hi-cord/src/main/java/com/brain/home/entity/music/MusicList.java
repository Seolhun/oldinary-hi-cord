package com.brain.home.entity.music;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "MUSIC_LIST")
@Data
public class MusicList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MUSIC_LIST_ID")
	private Long id;
	
	@Column(name = "MUSIC_LIST_JSON_DATA")
	@Lob
	private String JsonData;

	@Column(name = "MUSIC_LIST_CREATED_DATE", nullable = false)
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "MUSIC_LIST_MODIFIED_DATE", nullable = false)
	@UpdateTimestamp
	private Date ModifiedDate;

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof Music))
//			return false;
//		Music other = (Music) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
}
