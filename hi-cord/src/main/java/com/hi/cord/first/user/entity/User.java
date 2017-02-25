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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hi.cord.common.model.State;
import com.hi.cord.first.price.entity.PriceRecord;
import com.hi.cord.first.stadium.model.Stadium;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stadiumWithUser")
	private List<Stadium> userWithStardium;

	// User, What did you paid money for service. or How many
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paidByUser")
	private List<PriceRecord> userPaidFor;

	// User UK Email
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message = "Invalid Email")
	@Column(name = "USER_EMAIL", unique = true, nullable = false, length = 60)
	private String userEmail;

	// User UK Value
	@Column(name = "USER_PHONE", unique = true, nullable = false, length = 30)
	private String userPhone;

	// User Name
	@Column(name = "USER_NAME", nullable = false, length = 30)
	private String userName;

//	// User Photo to show others
	@Column(name = "USER_IMAGE_LIST", length = 100)
	private String imageList;

	// @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})",
	// message="Invalid Password")
	// User Bcrypt encod Password
	@Column(name = "USER_PASSWORD", nullable = false, length = 100)
	private String userPassword;

	@Column(name = "USER_ZIP_CODE", nullable = false, length = 20)
	private String userZipCode;

	@Column(name = "USER_ADDRESS", nullable = false, length = 100)
	private String userAddress;

	@Column(name = "USER_NATION_CODE", nullable = false, length = 10)
	private String userNationCode;

	@Column(name = "USER_ACTIVE_POINT", length = 10)
	private Integer userActivePoint;

	@Column(name = "USER_PAID_POINT", length = 10)
	private Integer userPaidPoint;

	// User boolean if receive mail or not through the Email
	@Column(name = "USER_RECEIVE_MAIL", length = 1)
	private Integer userReceiveEmail;

	// User boolean if receive message or not through the Phone
	@Column(name = "USER_RECEIVE_PHONE", length = 1)
	private Integer userReceivePhone;

	// User, Who did their's data modified?
	@Column(name = "USER_MODIFIED_BY", length = 60)
	private String userModifiedBy;

	// User, When did their's data is registerd?
	@CreationTimestamp
	@Column(name = "USER_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreatedDate;

	// User, When did their's data is modified?
	@UpdateTimestamp
	@Column(name = "USER_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userModifiedDate;

	// User, Boolean account is deleted or not
	@Column(name = "USER_DELCHECK", length = 1)
	private Integer userDelCheck;

	// User, about Account state
	@Column(name = "USER_STATE", nullable = false, length = 20)
	private String userState = State.ACTIVE.getState();

	// User, Boolean account is NON_EXPIRED or not.
	@Column(name = "USER_ACCOUNT_NON_EXPIRED", length = 1)
	private Integer userAccountNonExpired;

	// User, Boolean account is CREDENTIALS_NON_EXPIRED or not.
	@Column(name = "USER_CREDENTIALS_NON_EXPIRED", length = 1)
	private Integer userCredentialsNonExpired;

	// User, Boolean account is NON_LOCKED or not.
	@Column(name = "USER_ACCOUNT_NON_LOCKED", length = 1)
	private Integer userAccountNonLocked;

	// User, How many have Privileges.
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USER_PROFILE_REFER", foreignKey = @ForeignKey(name = "USER_REFER_FK"), joinColumns = {
			@JoinColumn(name = "USER_ID", columnDefinition = "BIGINT(20)") }, inverseForeignKey = @ForeignKey(name = "USER_PROFILE_REFER_FK"), inverseJoinColumns = {
					@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
}