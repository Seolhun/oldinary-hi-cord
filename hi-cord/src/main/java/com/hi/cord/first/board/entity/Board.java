package com.hi.cord.first.board.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.hi.cord.first.file.entity.FileData;

import lombok.Data;

@Entity
@Table(name = "TB_BOARD")
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOARD_ID")
	private Long boardId;

	@NotEmpty
	@Column(name = "BOARD_SUBJECT", nullable = false)
	private String boardSubject;

	@NotEmpty
	@Column(name = "BOARD_CONTENT", nullable = false)
	@Lob
	private String boardContent;

	@Column(name = "BOARD_HITS", length = 200)
	private int boardHits;

	@Column(name = "BOARD_LIKES", length = 100)
	private int boardLikes;
	
	@Column(name = " BOARD_TYPE", nullable = false, length = 10)
	private String boardType;
	
	@Column(name = "BOARD_REPLY_DEPTH", length = 200)
	private int boardReplyDepth;
	
	@Column(name = "BOARD_COMMENT_DEPTH", length = 200)
	private int boardCommentDepth;
	
	@Column(name = "BOARD_CREATED_BY", nullable = false, length = 60)
	private String boardCreatedBy;
	
	@Column(name = "BOARD_MODIFIED_BY", length = 60)
	private String boardModifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_CREATED_DATE")
	private Date boardCreatedDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_MODIFIED_DATE")
	private Date boardModifiedDate;

	@Column(name = "BOARD_DELCHECK", length=5, nullable=false)
	private int boardDelCheck;
	
	@OneToMany(mappedBy="boardInFile", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FileData> boardWithFileList;
//	
//	@OneToMany(mappedBy="boardInReply", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Reply> replyListInBoard;
//
//	@OneToMany(mappedBy="boardInComment", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Comment> commentListInBoard;
}
