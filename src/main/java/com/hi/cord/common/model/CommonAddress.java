package com.hi.cord.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CommonAddress {
	@Column(name = "COMMON_ZIP_CODE", length = 20, nullable = false)
	private String commonZipCode;

	@Column(name = "COMMON_ADDRESS", length = 100, nullable = false)
	private String commonAddress;
	
	@Column(name = "COMMON_ADDRESS2", length = 100, nullable = false)
	private String commonAddress2;

	@Column(name = "COMMON_NATION_CODE", length = 10, nullable = false)
	private String commonNationCode;
}
