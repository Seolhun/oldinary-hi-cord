package com.hi.cord.first.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_USER_PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable{
	private static final long serialVersionUID = -1123562957733732177L;

	@Id
	@Column(name="PERSISTENT_LOGINS_SERIES", length=50)
	private String series;

	@Column(name="PERSISTENT_LOGINS_EMAIL", unique=true, nullable=false, length=60)
	private String email;
	
	@Column(name="PERSISTENT_LOGINS_TOKEN", unique=true, nullable=false)
	private String token;
	
	@Column(name="PERSISTENT_LOGINS_LATESTDATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date latestdate;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLatestdate() {
		return latestdate;
	}

	public void setLatestdate(Date latestdate) {
		this.latestdate = latestdate;
	}
}
