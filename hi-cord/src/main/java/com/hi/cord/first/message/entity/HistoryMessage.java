package com.hi.cord.first.message.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name="TB_HISTORY_MESSAGE")
@Data
public class HistoryMessage implements Serializable {
	private static final long serialVersionUID = 5199613846851497179L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HISTORY_MESSAGE_ID")
	private Long id;
	
	@Column(name = "HISTORY_MESSAGE_CONTENT", nullable = false, length = 100)
	private String content;

	@Column(name = "HISTORY_MESSAGE_TO_USER", nullable = false, length = 60)
	private String toUser;

	@Column(name = "HISTORY_MESSAGE_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "HISTORY_MESSAGE_MODIFIED_BY", length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HISTORY_MESSAGE_CREATED_DATE")
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HISTORY_MESSAGE_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "HISTORY_MESSAGE_DELCHECK", length=5, nullable=false)
	private String delCheck;
}