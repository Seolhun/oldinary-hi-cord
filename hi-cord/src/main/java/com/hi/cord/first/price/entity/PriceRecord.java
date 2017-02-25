package com.hi.cord.first.price.entity;

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

import com.hi.cord.first.user.entity.User;

import lombok.Data;

@Entity
@Table(name = "TB_PRICE_RECORD")
@Data
public class PriceRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICE_RECORD_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRICE_RECORD_PAID_BY_USER", foreignKey = @ForeignKey(name = "PRICE_USER_FK"), referencedColumnName = "USER_ID", nullable = false)
	private User paidByUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "PRICE_RECOURD_FK"), name = "PRICE_VALUE_FROM_PRICE", referencedColumnName = "PRICE_ID", nullable = false)
	private Price priceValueFromPrice;

	@Column(name = "PRICE_RECORD_NAME", nullable = false, length = 50)
	private String priceRecordName;

	@Column(name = "PRICE_RECORD_ORIGINAL_VALUE", nullable = false, length = 40)
	private int priceRecordOriginalPrice;

	@Column(name = "PRICE_RECORD_DISCOUNT_RATE", length = 20)
	private String priceRecordDiscountRate;

	@Column(name = "PRICE_RECORD_DISCOUNTED_VALUE", length = 40)
	private int priceRecordDiscountedPrice;

	@Column(name = "PRICE_RECORD_ALLOW_STATE", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private PriceRecordState priceRecordAllowState;

	@CreationTimestamp
	@Column(name = "PRICE_RECORD_END_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceRecordEndDate;

	@CreationTimestamp
	@Column(name = "PRICE_RECORD_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceRecordCreatedDate;

	@UpdateTimestamp
	@Column(name = "PRICE_RECORD_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceRecordModifiedDate;

	// 환불 시 사용
	@Column(name = "PRICE_RECORD_DELCHECK", length = 5, nullable = false)
	private String priceRecordDelCheck;
}
