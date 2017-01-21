package com.brain.home.entity.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "REPLY")
@Data
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REPLY_ID")
	private Long id;

	@NotEmpty
	@Column(name = "REPLY_SUBJECT", nullable = false, length=200)
	private String subject;

	@NotEmpty
	@Column(name = "REPLY_CONTENT", nullable = false, length = 65535, columnDefinition="Text")
	private String content;

	@Column(name = "REPLY_HITS", length=300)
	private int hits;

	@Column(name = "REPLY_LIKES", length=300)
	private int likes;
	
	@Column(name = " REPLY_TYPE", nullable = false, length=10)
	private String type;
	
	@Column(name = "REPLY_COMMENT_DEPTH", length=100)
	private int commentDepth;
	
	@Column(name = "REPLY_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "REPLY_MODIFIED_BY", length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPLY_CREATION_DATE", nullable = false)
	private Date creationDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPLY_MODIFICATION_DATE")
	private Date modificationDate;

	@Column(name = "REPLY_DELCHECK")
	private int delCheck = 0;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="REPLY_BOARD_FK"), name="REPLY_BOARD_ID", referencedColumnName="BOARD_ID", nullable = false)
	private Board boardInReply;
}
