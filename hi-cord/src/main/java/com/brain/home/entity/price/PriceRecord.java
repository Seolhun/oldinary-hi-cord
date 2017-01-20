package com.brain.home.entity.price;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.brain.home.entity.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICE_RECORD")
@Getter
@Setter
public class PriceRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICE_RECORD_ID")
	private Long id;

	@Column(name = "PRICE_RECORD_NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "PRICE_RECORD_ORIGINAL_VALUE", nullable = false, length = 40)
	private int originalPrice;

	@Column(name = "PRICE_RECORD_DISCOUNT_RATE", length = 20)
	private String discountRate;
	
	@Column(name = "PRICE_RECORD_DISCOUNTED_VALUE", length = 40)
	private int discountedPrice;

	@Column(name = "PRICE_RECORD_ALLOW_STATE", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)	
	private PriceRecordState allowState;
	
	@CreationTimestamp
	@Column(name = "PRICE_RECORD_END_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@CreationTimestamp
	@Column(name = "PRICE_RECORD_CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@UpdateTimestamp
	@Column(name = "PRICE_RECORD_MODIFICATION_DATE")
	// @Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	// 환불 시 사용
	@Column(name = "PRICE_RECORD_DELCHECK", length = 5)
	private int delCheck;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICE_RECORD_PAID_BY_USER", foreignKey = @ForeignKey(name = "PRICE_USER_FK"), referencedColumnName = "USER_ID", nullable = false)
	private User paidByUser;
}
