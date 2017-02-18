package com.hi.cord.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hi.cord.common.model.Paging;

@Repository
public class CommonFnImpl implements CommonFn {
	
	static final Logger log = LoggerFactory.getLogger(CommonFnImpl.class);

	@Override
	public int checkVDInt(String parameter, int default_value) {
		int int_value;
		try {
			int_value = Integer.parseInt(parameter);
		} catch (Exception e) {
			int_value = default_value;
		}
		return int_value;
	}

	@Override
	public float checkVDFloat(String parameter, int default_value) {
		float int_value;
		try {
			int_value = Float.parseFloat(parameter);
		} catch (Exception e) {
			int_value = default_value;
		}
		return int_value;
	}

	@Override
	public String checkVDQuestion(String question) {
		String question_text = "";
		try {
			if (question.length() > 0) {
				question_text = question.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
						.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "%").trim();
			} else {
				question_text = null;
			}
		} catch (Exception e) {
			question_text = null;
		}
		return question_text;
	}

	@Override
	public Paging setPaging(Paging paging) {
		int blockLimit = 10; // 페이지 당 보여줄 블록 번호 limit
								// [1],[2],[3],[4],[5],[6],[7],[8],[9],[10]
		int totalPage = paging.getTotalPage();
		int limit = paging.getLimit();
		int cPage = paging.getCPage();

		int totalBlock = totalPage / limit + (totalPage % limit > 0 ? 1 : 0); // 전체
		int currentBlock = cPage / blockLimit + (cPage % blockLimit > 0 ? 1 : 0);// 현재
		int blockEndNo = currentBlock * blockLimit;
		int blockStartNo = blockEndNo - (blockLimit - 1);

		if (blockEndNo > totalBlock) {
			blockEndNo = totalBlock;
		}

		int prev_cPage = blockStartNo - blockLimit; // << *[이전]*
		int next_cPage = blockStartNo + blockLimit; // *[다음]* >>

		if (prev_cPage < 1) {
			prev_cPage = 1;
		}

		if (next_cPage > totalBlock) {
			next_cPage = totalBlock / blockLimit * blockLimit + 1;
		}
		paging.setBlockLimit(blockLimit);
		paging.setCurrentBlock(currentBlock);
		paging.setTotalBlock(totalBlock);
		paging.setBlockEndNo(blockEndNo);
		paging.setBlockStartNo(blockStartNo);
		paging.setNext_cPage(next_cPage);
		paging.setPrev_cPage(prev_cPage);
		return paging;
	}

	@Override
	public ObjectMapper setJSONMapper() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse. Thank
		// Mapping에 실패했을 때도 그냥 실행할 수 있게 하기.
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	@Override
	public String getJSONData(Object rawData) throws JsonProcessingException {
		// 담을 JSON 매핑
		ObjectMapper mapper = new ObjectMapper();
		// Null일 때, Null값을 가져오지 마세요.
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// 추출한 자료를 JSON으로 매핑하기
		String dtoAsString = mapper.writeValueAsString(rawData);
		return dtoAsString;
	}

	@Override
	public String getUserIP() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public boolean getLoginAuthValidation(Authentication auth, String authNameYouWant) {
		boolean valid=false;
		try {
			for (GrantedAuthority authority: auth.getAuthorities()) {
				String authName=authority.getAuthority();
				if(authName.equals(authNameYouWant)){
					valid=true;
				}
			}	
		} catch (NullPointerException e) {
			log.error("ERROR NullPointException - getLoginAuthValidation = "+valid);
		}
		log.info("TEST : getLoginAuthValidation = "+valid);
		return valid;
	}

	@Override
	public String buildSHA256(String str) {
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			str = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			str = null;
		}
		return str;
	}
}
