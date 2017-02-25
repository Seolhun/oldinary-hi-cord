package com.hi.cord.first.stadium.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.hi.cord.common.model.State;
import com.hi.cord.first.user.entity.User;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_STADIUM")
public class Stadium implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	// stadium PK Value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STADIUM_ID")
	private Long stadiumId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "STADIUM_USER_FK"), name = "STADIUM_WITH_USER_ID", referencedColumnName = "USER_ID", nullable = false)
	private User stadiumWithUser;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="stadiumTracksWithStardium")
	private List<StadiumAvailableTracks> stadiumWithStardiumTracks;
	
	// stadium Name
	@Column(name = "STADIUM_NAME", nullable = false, length = 30)
	private String stadiumName;
	
	@NotEmpty
	@Column(name = "STADIUM_DESCRIPTION", nullable = false)
	@Lob
	private String stadiumDescription;

	// stadium UK Email
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message = "Invalid Email")
	@Column(name = "STADIUM_EMAIL", unique = true, nullable = false, length = 60)
	private String stadiumEmail;

//	// stadium UK Value
	@Column(name = "STADIUM_TEL", unique = true, nullable = false, length = 30)
	private String stadiumTel;

//	// stadium Photo to show others
	@Column(name = "STADIUM_IMAGE_LIST", nullable = false, length = 100)
	private String imageList;

	@Column(name = "STADIUM_ZIP_CODE", nullable = false, length = 20)
	private String stadiumZipCode;

	@Column(name = "STADIUM_ADDRESS", nullable = false, length = 100)
	private String stadiumAddress;

	@Column(name = "STADIUM_NATION_CODE", nullable = false, length = 10)
	private String stadiumNationCode;

	//Point written by User
	@Column(name = "STADIUM_AVERAGE_POINT", nullable = false, length = 150)
	private Integer stadiumAveragePoint;

	@Column(name = "STADIUM_REPLY_DETPH", nullable = false, length = 100)
	private Integer stadiumReplyDepth;
	
	@Column(name = "STADIUM_TRACKS_DETPH", nullable = false, length = 10)
	private Integer stadiumTracksDepth;
	
	@Column(name = "STADIUM_LIKES", nullable = false, length = 150)
	private Integer stadiumLikes;
	
	// stadium boolean if receive mail or not through the Email
	@Column(name = "STADIUM_RECEIVE_MAIL", length = 1)
	private Integer stadiumReceiveEmail;

	// stadium, Who did their's data modified?
	@Column(name = "STADIUM_CREATED_BY", length = 60)
	private String stadiumCreatedBy;

	// stadium, Who did their's data modified?
	@Column(name = "STADIUM_MODIFIED_BY", length = 60)
	private String stadiumModifiedBy;
	
	@CreationTimestamp
	@Column(name = "STADIUM_CONTRACT_STARTED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stadiumContractStartedDate;

	@Column(name = "STADIUM_CONTRACT_EXPIRED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stadiumContractExpiredDate;
	
	// stadium, When did their's data is registerd?
	@CreationTimestamp
	@Column(name = "STADIUM_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stadiumCreatedDate;

	// stadium, When did their's data is modified?
	@UpdateTimestamp
	@Column(name = "STADIUM_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stadiumModifiedDate;

	// stadium, Boolean account is deleted or not
	@Column(name = "STADIUM_DELCHECK", length = 1)
	private Integer stadiumDelCheck;

	// stadium, about Account state
	@Column(name = "STADIUM_STATE", nullable = false, length = 20)
	private String stadiumState = State.ACTIVE.getState();
}