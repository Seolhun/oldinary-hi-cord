package com.hi.cord.first.comment.entity;

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

import com.hi.cord.first.board.entity.Board;

import lombok.Data;

@Entity
@Table(name = "TB_COMMENT")
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMMENT_ID")
	private Long commentId;

	@NotEmpty
	@Column(name = "COMMENT_CONTENT", nullable = false, length = 300)
	private String commentContent;

	@Column(name = "COMMENT_LIKES")
	private int commentLkes;
	
	@Column(name = "COMMENT_COMMENT_DEPTH", nullable = false)
	private int commentDepth;
	
	@Column(name = " COMMENT_TYPE", nullable = false, length=10)
	private String commentType;
	
	@Column(name = "COMMENT_CREATED_BY", nullable = false, length = 60)
	private String commentCreatedBy;
	
	@Column(name = "COMMENT_MODIFIED_BY", length = 60)
	private String commentModifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_CREATED_DATE", nullable = false)
	private Date commentCreatedDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_MODIFIED_DATE")
	private Date commentModifiedDate;

	@Column(name = "COMMENT_DELCHECK", length=5, nullable=false)
	private String commentDelCheck;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="COMMENT_BOARD_FK"), name="COMMENT_BOARD_ID", referencedColumnName="BOARD_ID")
	private Board boardInComment;
}
