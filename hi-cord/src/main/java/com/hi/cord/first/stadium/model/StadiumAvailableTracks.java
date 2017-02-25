package com.hi.cord.first.stadium.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_STADIUM_AVAILABLE_TRACKS")
public class StadiumAvailableTracks implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	// stadium PK Value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STADIUM_TRACKS_ID")
	private Long stadiumTracksId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_STADIUM_TRACKS_FK"), name = "STADIUM_TRACKS_WITH_STADIUM_ID", referencedColumnName = "STADIUM_ID", nullable = false)
	private Stadium stadiumTracksWithStardium;
	
	@Column(name = "STADIUM_TRACKS_TYPE", nullable = false, length = 10)
	private String stadiumTracksType;
	
	// stadium Name
	@Column(name = "STADIUM_TRACKS_NAME", nullable = false, length = 30)
	private String stadiumTracksName;
	
	@NotEmpty
	@Column(name = "STADIUM_TRACKS_DESCRIPTION", nullable = false)
	@Lob
	private String stadiumTracksDescription;
	
	@Column(name = "STADIUM_TRACKS_CAPACITY", nullable = false, length = 10)
	private Integer stadiumTracksCapacity;

	@Column(name = "STADIUM_TRACKS_PRICE", nullable = false, length = 20)
	private Integer stadiumTracksPrice;
}