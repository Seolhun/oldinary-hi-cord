package com.hi.cord.first.entity.user;

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

import com.hi.cord.first.entity.price.PriceRecord;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_USER")
public class User implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message="Invalid Email")
	@Column(name = "USER_EMAIL", unique = true, nullable = false, length = 60)
	private String userEmail;

	@Column(name = "USER_NAME", nullable = false, length = 30)
	private String userName;
	
	//@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})", message="Invalid Password")
	@Column(name = "USER_PASSWORD", nullable = false, length = 100)
	private String userPassword;
	
	@Column(name = "USER_RECEIVE_MAIL", length = 1)
	private int userReceiveEmail;
	
	@Column(name = "USER_STATE", nullable = false, length = 20)
	private String userState = State.ACTIVE.getState();
	
	//휴면 계좌 체크
	@Column(name = "USER_ACCOUNT_NON_EXPIRED", length = 1)
	private int userAccountNonExpired;
	
	//비밀번호 오류 체크
	@Column(name = "USER_CREDENTIALS_NON_EXPIRED", length = 1)
	private int userCredentialsNonExpired;
	
	//잠겨있는지 확인
	@Column(name = "USER_ACCOUNT_NON_LOCKED", length = 1)
	private int userAccountNonLocked;
	
	@Column(name = "USER_MODIFIED_BY", length = 60)
	private String userModifiedBy;
	
	@CreationTimestamp
	@Column(name = "USER_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreatedDate;
	
	@UpdateTimestamp
	@Column(name = "USER_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userModifiedDate;
	
	@Column(name = "USER_DELCHECK", length=5)
	private int userDelCheck;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "TB_USER_PROFILE_REFER",foreignKey=@ForeignKey(name="USER_REFER_FK"),  
		joinColumns = { @JoinColumn(name = "USER_ID", columnDefinition="BIGINT(20)") }, 
		inverseForeignKey=@ForeignKey(name="USER_PROFILE_REFER_FK"), inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	@OneToMany(mappedBy="paidByUser", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PriceRecord> paidHistoryList;
}