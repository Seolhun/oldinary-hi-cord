package com.hi.cord.first.entity.board;

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
@Table(name = "TB_COMMENT")
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMMENT_ID")
	private Long id;

	@NotEmpty
	@Column(name = "COMMENT_CONTENT", nullable = false, length = 300)
	private String content;

	@Column(name = "COMMENT_LIKES")
	private int likes;
	
	@Column(name = "COMMENT_COMMENT_DEPTH", nullable = false)
	private int commentDepth;
	
	@Column(name = " COMMENT_TYPE", nullable = false, length=10)
	private String type;
	
	@Column(name = "COMMENT_CREATED_BY", nullable = false, length = 30)
	private String createdBy;
	
	@Column(name = "COMMENT_MODIFIED_BY", length = 30)
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_CREATED_DATE", nullable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_MODIFIED_DATE")
	private Date modificationDate;

	@Column(name = "COMMENT_DELCHECK", length=5, nullable=false)
	private String delCheck;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="COMMENT_BOARD_FK"), name="COMMENT_BOARD_ID", referencedColumnName="BOARD_ID")
	private Board boardInComment;
}
