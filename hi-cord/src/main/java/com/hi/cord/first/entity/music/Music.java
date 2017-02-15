package com.hi.cord.first.entity.music;

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
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "MUSIC")
@Data
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MUSIC_ID")
	private Long id;
	
	@NotEmpty
	@Column(name = "MUSIC_IMAGE", nullable = false, length=255)
	@JsonProperty
	private String image;

	@NotEmpty
	@Column(name = "MUSIC_SINGER", nullable = false, length=50)
	@JsonProperty
	private String singer;

	@NotEmpty
	@Column(name = "MUSIC_TITLE", nullable = false, unique=true, length=100)
	@JsonProperty
	private String title;
	
	@Column(name = "MUSIC_LYRICS")
	@Lob
	private String lyrics;
	
	@Column(name = "MUSIC_URL", length=255)
	private String url;

	@Column(name = "MUSIC_CREATED_DATE", nullable = false)
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "MUSIC_MODIFIED_DATE", nullable = false)
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
