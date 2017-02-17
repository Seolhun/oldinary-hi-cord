package com.hi.cord.common.model;

import java.util.List;

import lombok.Data;

@Data
public class AjaxResult {
	private Object result;
	private String errorMsg;
	private List<Object> resultList;
}
