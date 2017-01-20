package com.brain.home.entity.board;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.brain.home.entity.file.FileData;

import lombok.Data;

@Entity
@Table(name="BOARD")
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOARD_ID")
	private Long id;

	@NotEmpty
	@Column(name = "BOARD_SUBJECT", nullable = false)
	private String subject;

	@NotEmpty
	@Column(name = "BOARD_CONTENT", nullable = false, length = 65535, columnDefinition="Text")
	private String content;

	@Column(name = "BOARD_HITS", length = 200)
	private int hits;

	@Column(name = "BOARD_LIKES", length = 100)
	private int likes;
	
	@Column(name = " BOARD_TYPE", nullable = false, length = 10)
	private String boardType;
	
	@Column(name = "BOARD_REPLY_DEPTH", length = 200)
	private int replyDepth;
	
	@Column(name = "BOARD_COMMENT_DEPTH", length = 200)
	private int commentDepth;
	
	@Column(name = "BOARD_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "BOARD_MODIFIED_BY", length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_CREATION_DATE")
	private Date creationDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_MODIFICATION_DATE")
	private Date modificationDate;

	@Column(name = "BOARD_DELCHECK")
	private int delCheck = 1;
	
	@OneToMany(mappedBy="boardInFile", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FileData> fileListInBoard;
	
	@OneToMany(mappedBy="boardInReply", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Reply> replyListInBoard;

	@OneToMany(mappedBy="boardInComment", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> commentListInBoard;
}
