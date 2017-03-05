package com.hi.cord.common.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hi.cord.common.model.Paging;

@Service
public class CommonServiceImpl implements CommonService {
	static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	JavaMailSender mailSender;

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
	public void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		Date date = new Date();
		String currentTime = formatter.format(date);
		String from = "imedisyndev@gmail.com";
		String mailSubject = "안녕하세요. (주)Hi-Cord입니다. "+userName+"의 계정이 5회 로그인 실패로 잠금설정 되었습니다.";
		String mailContent = 
				userName+"님 안녕하세요."+" (주)Hi-Cord입니다."
				+ "<br>회원님의 아이디에 누군가가 "+currentTime+"에 로그인 시도를 하여, 5회 이상 실패로 계정이 잠금처리 되었습니다."
				+ "<br>먼저, 비밀번호를 바꾸시길 요청드립니다. 밑의 비밀번호 변경 버튼을 눌러 1회용 Password를 입력하여 비밀번호 변경해주시기 바랍니다."
				+ "<h4>Password : "+password+"</h4>"
				+ "<br><a href="+httpPath+"?key="+authentication+"><button>비밀번호 변경</button></a>"
				+ "<br>추가로 로그인 시도된 정보를 제공해드리오니, 확인하시고 궁금하신 것이 있으시면 문의부탁드립니다.";
		mainSendMail(toEmail, from, mailSubject, mailContent);
	}
	
	public void mainSendMail(String toEmail, String from, String mailSubject, String mailContent) {
		new Thread(){
			public void run(){
				String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				if (toEmail.matches(emailregex)) {
					try {
						MimeMessage message = mailSender.createMimeMessage();
						// true로서 멀티파트 메세지라는 의미
						MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
						messageHelper.setFrom(from);
						messageHelper.setTo(toEmail);
						messageHelper.setSubject(mailSubject);
						messageHelper.setText(
							"<html>"
								+ "<body>"
									+ "<div style='text-align : left; font-color:black; font-size : 14px;'>"
										+ mailContent
									+ "</div>"
								+ "</body>"
							+ "</html>", true);
						// 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
						// FileSystemResource fileImage=new
						// FileSystemResource("/resources/img/google.png");
						// messageHelper.addAttachment("Google Png", fileImage);
		
						// 로고 넣기
						// ClassPathResource image=new
						// ClassPathResource("/resources/img/google.png");
						// messageHelper.addInline("Google_Logo", image);
						mailSender.send(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void validCheckAndSendError(MessageSource messageSource, BindingResult result, HttpServletRequest request, String getValue, String objectName, String fieldName, String messagePropertyName) {
		FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[] { getValue }, request.getLocale()));
		result.addError(error);
	}
}
