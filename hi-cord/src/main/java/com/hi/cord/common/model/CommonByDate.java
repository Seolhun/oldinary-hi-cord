package com.hi.cord.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class CommonByDate {
	@Column(name = "COMMON_CREATED_BY", nullable = false, length = 60)
	private String commonCreatedBy;
	
	@Column(name = "COMMON_MODIFIED_BY", length = 60)
	private String commonModifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMON_CREATED_DATE")
	private Date commonCreatedDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMON_MODIFIED_DATE")
	private Date commonModifiedDate;

	@Column(name = "COMMON_DELCHECK", length=5, nullable=false)
	private int commonDelCheck;
}
