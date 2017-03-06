package com.hi.cord.first.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hi.cord.common.model.CommonState;
import com.hi.cord.first.club.entity.SportsClub;
import com.hi.cord.first.price.entity.PriceRecord;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_USER")
public class User implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	// User PK Value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	// User, What did you paid money for service. or How many
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceRecorPaidByUser", cascade=CascadeType.ALL)
	private List<PriceRecord> userPaidForList;
	
	//Club I found	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "sportsClubOwner", cascade=CascadeType.ALL)
	private SportsClub ownerOfSportClub;
	
	//Club I joined
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="USER_SPORTS_CLUB_FK"), name="UESR_IN_SPORTS_CLUB_ID", referencedColumnName="SPORTS_CLUB_ID")
	private SportsClub userInSportsClub;
	
	// User, How many have Privileges.
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USER_PROFILE_REFER", foreignKey = @ForeignKey(name = "USER_REFER_FK"), joinColumns = {
			@JoinColumn(name = "USER_ID", columnDefinition = "BIGINT(20)") }, inverseForeignKey = @ForeignKey(name = "USER_PROFILE_REFER_FK"), inverseJoinColumns = {
					@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	// User UK Email
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message = "INVALID-EMAIL")
	@Column(name = "USER_EMAIL", length = 60, unique = true, nullable = false)
	@JsonProperty("userEmail")
	private String userEmail;

	// User UK Value
	@Column(name = "USER_PHONE", length = 30, unique = true, nullable = false)
	@JsonProperty("userPhone")
	private String userPhone;

	// User Name
	@Column(name = "USER_NAME", length = 30, nullable = false)
	@JsonProperty("userName")
	private String userName;

//	// User Photo to show others
	@Column(name = "USER_IMAGE_LIST", length = 100, nullable = true)
	private String imageList;

	// @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})",
	// message="Invalid Password")
	// User Bcrypt encod Password
	@Column(name = "USER_PASSWORD", length = 100, nullable = false)
	private String userPassword;

	@Column(name = "USER_ZIP_CODE", length = 20, nullable = false)
	private String userZipCode;

	@Column(name = "USER_ADDRESS", length = 100, nullable = false)
	private String userAddress;
	
	@Column(name = "USER_ADDRESS2", length = 100, nullable = false)
	private String userAddress2;

	@Column(name = "USER_NATION_CODE", length = 10, nullable = false)
	private String userNationCode;
	
//	@Embedded
//	private CommonAddress commonAddress;

	@Column(name = "USER_ACTIVE_POINT", length = 10, nullable = true)
	private Integer userActivePoint=0;

	@Column(name = "USER_PAID_POINT", length = 10, nullable = true)
	private Integer userPaidPoint=0;

	// User boolean if receive mail or not through the Email
	@Column(name = "USER_RECEIVE_MAIL", length = 1, nullable = true)
	private Integer userReceiveEmail=0;

	// User boolean if receive message or not through the Phone
	@Column(name = "USER_RECEIVE_PHONE", length = 1, nullable = true)
	private Integer userReceivePhone=0;

	// User, Who did their's data modified?
	@Column(name = "USER_MODIFIED_BY", length = 60, nullable = true)
	private String userModifiedBy;

	// User, When did their's data is registerd?
	@CreationTimestamp
	@Column(name = "USER_CREATED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreatedDate;

	// User, When did their's data is modified?
	@UpdateTimestamp
	@Column(name = "USER_MODIFIED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date userModifiedDate;

	// User, Boolean account is deleted or not
	@Column(name = "USER_DELCHECK", length = 1, nullable = true)
	private Integer userDelCheck=0;
	
//	@Embedded
//	private CommonByDate commonByDate;

	// User, about Account state
	@Column(name = "USER_STATE", length = 20, nullable = false)
	private String userState = CommonState.ACTIVE.getState();

	// User, Boolean account is NON_EXPIRED or not.
	@Column(name = "USER_ACCOUNT_NON_EXPIRED", length = 1, nullable = true)
	private Integer userAccountNonExpired=0;

	// User, Boolean account is CREDENTIALS_NON_EXPIRED or not.
	@Column(name = "USER_CREDENTIALS_NON_EXPIRED", length = 1, nullable = true)
	private Integer userCredentialsNonExpired=0;

	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "USER_ACCOUNT_NON_LOCKED", length = 1, nullable = true)
	private Integer userAccountNonLocked=0;
	
	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "USER_PRIVATE_AGREE", length = 1, nullable = true)
	private Integer userPrivateAgree=0;
	
	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "USER_SERVICE_AGREE", length = 1, nullable = true)
	private Integer userServiceAgree=0;
	
	@Column(name = "USER_LOCKED_AUTH", length = 100, nullable = true)
	private String userLockedAuth;
	
	//Type==1이면 Password를 바꾼다.
	@Transient
	private Integer type;
}