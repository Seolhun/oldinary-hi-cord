package com.brain.home.entity.file;

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

import com.brain.home.entity.board.Board;

import lombok.Data;

@Entity
@Table(name = "FILE_DATA")
@Data
public class FileData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Long id;

	@NotEmpty
	@Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
	private String originName;

	@NotEmpty
	@Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
	private String savedName;

	@Column(name = "FILE_SAVED_DIR", nullable = false, length = 200)
	private String savedDir;

	@Column(name = " FILE_TYPE", nullable = false, length = 20)
	private String Type;

	@Column(name = "FILE_CREATED_BY", nullable = false, length = 30)
	private String createdBy;

	@Column(name = "FILE_MODIFIED_BY", length = 30)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_CREATION_DATE", nullable = false)
	private Date creationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_MODIFICATION_DATE")
	private Date modificationDate;

	@Column(name = "FILE_DELCHECK", nullable = false)
	private int delCheck = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_BOARD_FK"), name = "FILE_BOARD_ID", referencedColumnName = "BOARD_ID", nullable = false)
	private Board boardInFile;
}
