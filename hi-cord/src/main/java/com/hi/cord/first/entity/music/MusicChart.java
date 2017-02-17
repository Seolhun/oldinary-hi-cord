package com.hi.cord.first.entity.music;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "TB_MUSIC_CHART")
@Data
public class MusicChart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MUSIC_CHART_ID")
	private Long id;
	
	@Column(name = "MUSIC_CHART_JSON_DATA")
	@Lob
	private String jsonData;

	@Column(name = "MUSIC_CHART_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "MUSIC_CHART_MODIFIED_BY", length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MUSIC_CHART_CREATED_DATE")
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MUSIC_CHART_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "MUSIC_CHART_DELCHECK", length=5, nullable=false)
	private String delCheck;

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
