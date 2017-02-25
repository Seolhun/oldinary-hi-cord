package com.hi.cord.first.file.entity;

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
@Table(name = "TB_FILE_DATA")
@Data
public class FileData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Long fileDataId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_BOARD_FK"), name = "FILE_BOARD_ID", referencedColumnName = "BOARD_ID", nullable = false)
//	private Board boardInFile;

	@NotEmpty
	@Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
	private String fileDataOriginName;

	@NotEmpty
	@Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
	private String fileDataSavedName;

	@Column(name = "FILE_SAVED_PATH", nullable = false, length = 200)
	private String fileDataSavedPath;

	@Column(name = " FILE_TYPE", nullable = false, length = 20)
	private String fileDataType;

	@Column(name = "FILE_CREATED_BY", nullable = false, length = 60)
	private String fileDataCreatedBy;

	@Column(name = "FILE_MODIFIED_BY", length = 60)
	private String fileDataModifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_CREATED_DATE", nullable = false)
	private Date fileDataCreatedDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_MODIFIED_DATE")
	private Date fileDataModifiedDate;

	@Column(name = "FILE_DELCHECK", length=5, nullable=false)
	private int fileDataDelCheck;
}
