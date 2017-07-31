package com.hi.cord.first.club.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TB_SPORTS_CLUB_PROFILE")
@Data
public class SportsClubProfile implements Serializable{
	private static final long serialVersionUID = 3788477180129427170L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SPORTS_CLUB_PROFILE_ID")
	private Integer sportsClubProfileId;	

	@Column(name="SPORTS_CLUB_PROFILE_TYPE", length=15, unique=true, nullable=false)
	private String userProfileType = SportsType.Unchosen.getName();
	
}
