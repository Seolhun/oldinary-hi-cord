package com.brain.home.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brain.home.entity.common.Language;
import com.brain.home.entity.common.Paging;

@Repository
public class CommonFnImpl implements CommonFn {

	@Autowired
	private JavaMailSender mailSender;

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
	public Language setLanguageData(Properties text_ko, Properties text_en, HttpServletRequest request) {
		String serverName = request.getContextPath();
		Language Language = new Language();
		// setDefault Values
		String language_code = "kr";
		Properties text = text_ko;
		if (serverName.contains("/kr")) {
			language_code = "kr";
			text = text_ko;
		} else if (serverName.contains("/en")) {
			language_code = "en";
			text = text_en;
		}
		Language.setLanguage_code(language_code);
		Language.setText(text);
		return Language;
	}

	@Override
	public void setDefaultSetting(ModelMap model, Language language, String target, String targetName) {
		// 기본 설정
		String companyName = language.getText().getProperty("common.company.name");
		String dwblank = language.getText().getProperty("common.text.blank");
		model.addAttribute("language_code", language.getLanguage_code());
		// 매핑 설정
		String title;
		if (target.equals("home")) {
			title = companyName;
		} else {
			title = targetName + dwblank + companyName;
		}

		model.addAttribute("target", target);
		model.addAttribute("title", title);
		model.addAttribute("targetName", targetName);
	}

	//
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
	// POST 메소드에서는 가져오지 못함.
	public boolean checkValidConnectPage(String email) {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		if (email.equals(userName)) {
			return true;
		}
		return false;
	}

	@Override
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public void applicationSendMail(ServletResponse res, HttpSession session, String type, String job, String name) {
		new Thread() {
			public void run() {
				try {
					MimeMessage message = mailSender.createMimeMessage();
					// true로서 멀티파트 메세지라는 의미
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setFrom("Shooney");
					messageHelper.setTo("shun10114@gmail.com");
					messageHelper.setSubject("이력서 접수 알림");

					int authentication = (int) (Math.random() * 1000000);
					session.setAttribute("authentication", authentication);

					String tp = "";
					String jb = "";
					if (type.equals("exp")) {
						tp = "경력";
					} else {
						tp = "신입";
					}

					if (job.equals("d")) {
						jb = "디자이너";
					} else if (job.equals("pg")) {
						jb = "개발자";
					} else if (job.equals("pb")) {
						jb = "퍼블리셔";
					} else if (job.equals("m")) {
						jb = "기술영업";
					} else {
						jb = "기타";
					}

					messageHelper.setText(
							"" + "<html><body><div><h3>새로운 이력서가 <span style='color:#74b45f;'>접수</span>되었습니다.</h3><br>"
									+ "지원 정보<br><table style='font-size:13px;width:380px;border-top: 2px solid #74b45f;border-bottom: 1px solid #ccc;margin-top: 10px;text-indent: 1.2em;'>"
									+ "<tr><td style='width: 40%;'>지원분야</td><td>" + jb + "</td></tr><tr><td>분류</td><td>"
									+ tp + "</td></tr><tr><td>지원자명</td><td>" + name + "</td></tr>"
									+ "</table><br><br><button style='background:#74b45f;color:white; border-style: none;width:150px;height:40px;font-size:12px;margin-left: 114px;'>"
									+ "아이메디신 홈페이지로 이동</button><br><br><div style='border: 1px solid #ccc;padding:10px;font-size:11px;color: #8C8C8C;width: 380px;'>"
									+ "본 메일은 발신 전용 메일입니다. 문의는 imedisyndev@gmail.com에 접수바랍니다.<br>TEL. 02-742-7422 | FAX. 02-745-7422 | E-mail. imedisyndev@gmail.com	"
									+ "</div></div></body></html>",
							true);

					mailSender.send(message);

					System.out.println("메일 전송 완료");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
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
