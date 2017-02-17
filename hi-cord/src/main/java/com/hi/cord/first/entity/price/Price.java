package com.hi.cord.first.entity.price;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_PRICE")
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICE_ID")
	private Long priceId;

	@Column(name = "PRICE_NAME", nullable = false, length = 50)
	private String priceName;

	@Column(name = "PRICE_VALUE", nullable = false, length = 20)
	private int priceValue;

	@Column(name = "PRICE_DISCOUNT_RATE", length = 20)
	private int priceDiscountRate;

	@Column(name = "PRICE_EVENT_START_DATE", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceEventStartDate;
	
	@Column(name = "PRICE_EVENT_END_DATE", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceEventEndDate;
	
	@Column(name = "PRICE_CREATED_BY", nullable = false, length = 60)
	private String priceCreatedBy;
	
	@Column(name = "PRICE_MODIFIED_BY", length = 60)
	private String priceModifiedBy;
	
	@CreationTimestamp
	@Column(name = "PRICE_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceCreatedDate;
	
	@UpdateTimestamp
	@Column(name = "PRICE_MODIFIED_DATE", updatable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceModifiedDate;;
	
	// 가격표 사용/미사용 선택시 활용
	@Column(name = "PRICE_DELCHECK", length=5, nullable=false)
	private String priceDelCheck;
}
