package com.hi.cord.first.club.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hi.cord.common.model.CommonState;
import com.hi.cord.first.user.entity.User;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_SPORTS_CLUB")
public class SportsClub implements Serializable {
	private static final long serialVersionUID = 1023828411700763632L;

	// User PK Value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPORTS_CLUB_ID")
	private Long sportsClubId;

	// User Name
	@Column(name = "SPORTS_CLUB_NAME", unique = true, length = 30, nullable = false)
	@JsonProperty("sportClubName")
	private String sportsClubName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInSportsClub")
	private List<User> sportsClubWithUser;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "SPORTS_CLUB_OWNER_USER_FK"), name = "SPORTS_CLUB_OWNER_ID", referencedColumnName = "USER_ID", nullable = false)
	private User sportsClubOwner;

	// User, How many have Privileges.
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_SPORTS_CLUB_PROFILES_REFER", foreignKey = @ForeignKey(name = "SPORTS_CLUB_REFER_FK"), joinColumns = {
			@JoinColumn(name = "SPORTS_CLUB_ID", columnDefinition = "BIGINT(20)") }, inverseForeignKey = @ForeignKey(name = "SPORTS_CLUB_PROFILE_REFER_FK"), inverseJoinColumns = {
					@JoinColumn(name = "SPORTS_CLUB_PROFILE_ID") })
	private Set<SportsClubProfile> sportsClubProfiles = new HashSet<SportsClubProfile>();

	// // User Photo to show others
	@Column(name = "SPORTS_CLUB_LOGO", length = 100, nullable = true)
	private String sportsClubLogo;

	@Column(name = "SPORTS_CLUB_PASSWORD", length = 100, nullable = false)
	private String sportsClubPassword;

	// How many users can join here.
	@Column(name = "SPORTS_CLUB_CAPACITY", length = 20, nullable = false)
	private Integer sportsClubCapacity = 30;

	// How many users can join here.
	@Column(name = "SPORTS_CLUB_TEL", length = 20, nullable = true)
	private String sportsClubTel;

	// main stadium or main address where they play sports
	@Column(name = "SPORTS_CLUB_ZIP_CODE", length = 20, nullable = false)
	private String sportsClubZipCode;

	@Column(name = "SPORTS_CLUB_ADDRESS", length = 100, nullable = false)
	private String sportsClubAddress;

	@Column(name = "SPORTS_CLUB_ADDRESS2", length = 100, nullable = false)
	private String sportsClubAddress2;

	@Column(name = "SPORTS_CLUB_NATION_CODE", length = 10, nullable = false)
	private String sportsClubNationCode;

	// @Embedded
	// private CommonAddress commonAddress;

	@Column(name = "SPORTS_CLUB_ACTIVE_POINT", length = 10, nullable = true)
	private Integer sportsClubActivePoint = 0;

	@Column(name = "SPORTS_CLUB_PAID_POINT", length = 10, nullable = true)
	private Integer sportsClubPaidPoint = 0;

	// Owner of SportsClub boolean if receive mail or not through the Email
	@Column(name = "SPORTS_CLUB_RECEIVE_MAIL", length = 1, nullable = true)
	private Integer sportsClubReceiveEmail = 0;

	// Owner of SportsClub boolean if receive message or not through the Phone
	@Column(name = "SPORTS_CLUB_RECEIVE_PHONE", length = 1, nullable = true)
	private Integer sportsClubReceivePhone = 0;

	// SportsClub, Who did their's data modified?
	@Column(name = "SPORTS_CLUB_MODIFIED_BY", length = 60, nullable = true)
	private String sportsClubModifiedBy;

	// SportsClub, When did their's data is registerd?
	@CreationTimestamp
	@Column(name = "SPORTS_CLUB_CREATED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sportsClubCreatedDate;

	// SportsClub, When did their's data is modified?
	@UpdateTimestamp
	@Column(name = "SPORTS_CLUB_MODIFIED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sportsClubModifiedDate;

	// SportsClub, Boolean account is deleted or not
	@Column(name = "SPORTS_CLUB_DELCHECK", length = 1, nullable = true)
	private Integer sportsClubDelCheck = 0;
	// @Embedded
	// private CommonByDate commonByDate;

	// SportsClub, about Account state
	@Column(name = "SPORTS_CLUB_STATE", length = 20, nullable = false)
	private String sportsClubState = CommonState.ACTIVE.getState();

	// SportsClub, Boolean account is NON_LOCKED or not.
	@Column(name = "SPORTS_CLUB_PRIVATE_AGREE", length = 1, nullable = true)
	private Integer sportsClubPrivateAgree = 0;

	// SportsClub, Boolean account is NON_LOCKED or not.
	@Column(name = "SPORTS_CLUB_SERVICE_AGREE", length = 1, nullable = true)
	private Integer sportsClubServiceAgree = 0;
}