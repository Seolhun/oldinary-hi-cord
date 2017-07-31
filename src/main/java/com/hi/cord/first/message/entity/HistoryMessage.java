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
	private Long historyMessageId;
	
	//Sending Message Content
	@Column(name = "HISTORY_MESSAGE_CONTENT", nullable = false, length = 100)
	private String historyMessageContent;
	
	//Moving where we want Page
	@Column(name = "HISTORY_MESSAGE_TO_MOVE_URI", nullable = false, length = 100)
	private String historyMessageToMoveUri;
	
	//Moving where we want core Entity Name
	@Column(name = "HISTORY_MESSAGE_PARAM_TYPE", nullable = false, length = 20)
	private String historyMessageParamType;
		
	//Moving where we want PK data
	@Column(name = "HISTORY_MESSAGE_TO_MOVE_PK", nullable = false, length = 20)
	private Long historyMessageToMovePK;

	//Sending message to Who?
	@Column(name = "HISTORY_MESSAGE_TO_USER", nullable = false, length = 60)
	private String historyMessageToUser;

	@Column(name = "HISTORY_MESSAGE_CREATED_BY", nullable = false, length = 60)
	private String historyMessageCreatedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HISTORY_MESSAGE_CREATED_DATE")
	private Date historyMessageCreatedDate;
	
	@Column(name = "HISTORY_MESSAGE_MODIFIED_BY", length = 60)
	private String historyMessageModifiedBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HISTORY_MESSAGE_MODIFIED_DATE")
	private Date historyMessageModifiedDate;

	@Column(name = "HISTORY_MESSAGE_DELCHECK", length=5, nullable=false)
	private String historyMessageDelCheck;
}