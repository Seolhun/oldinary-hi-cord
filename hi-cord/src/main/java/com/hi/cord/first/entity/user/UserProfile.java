package com.hi.cord.first.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TB_USER_PROFILE")
@Data
public class UserProfile implements Serializable{
	private static final long serialVersionUID = 3788477180129427170L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_PROFILE_ID")
	private Integer userProfileId;	

	@Column(name="USER_PROFILE_TYPE", length=15, unique=true, nullable=false)
	private String userProfileType = UserProfileType.GUEST.getUserProfileType();
	
}
