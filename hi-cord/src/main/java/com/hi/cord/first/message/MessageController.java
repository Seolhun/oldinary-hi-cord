package com.hi.cord.first.message;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hi.cord.first.message.entity.HistoryMessage;
import com.hi.cord.first.message.entity.Result;

@Controller
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private SimpMessagingTemplate messaging;

	@RequestMapping("/message")
	public String textChat() {
		return "/views/message/message-list";
	}
	
	//채팅전용
	@MessageMapping("/send")
	@SendTo("/queue/message")
	public Result sendChat(Result result, Principal principal) throws Exception {
		logger.info("param sendChat : {}", result.getMessage()+ " : " + result.getToUser());
		return result;
	}

	@RequestMapping("/gift")
	public String sendGift(Principal principal, ModelMap model) {
		model.addAttribute("principal", principal.getName());
		return "message/gift";
	}
	
	@MessageMapping("/gift/{toUser}")
	@SendToUser(broadcast=true)
	public void sendGift(Result result, Principal principal, @DestinationVariable String toUser) throws Exception {
		logger.info("param sendGift : {}", result.getMessage()+ " : " + result.getToUser());
//		messaging.convertAndSendToUser(toUser, "/topic/message", result);
	}
	
	@RequestMapping("/post")
	public String post(Principal principal, ModelMap model) {
		model.addAttribute("principal", principal.getName());
		return "message/post";
	}

	@RequestMapping("/subscribe")
	public String subscribe(Principal principal, ModelMap model) {
		model.addAttribute("principal", principal.getName());
		return "message/subscribe";
	}
	
//
//
//	@MessageExceptionHandler
//	@SendToUser("/topic/errors")
//	public String handleException(Exception ex) {
//		System.out.println(ex);
//		return ex.getMessage();
//	}
//	
//	@MessageMapping("/post")
//	public Result messagePost(Message message, Principal principal) throws Exception {
//		logger.info("TEST messagePost : " + message.getText() + " : " + message.getToUser());
//		Result result = new Result(message.getText(), principal.getName());
//		mDao.saveMessage(result);
//		return result;
//	}
//	
//	@SubscribeMapping("/subscribe")
//	public Result subscribePost() throws Exception {
//		logger.info("TEST messageSubscribe : ");
//		Result result=new Result();
//		result.setMessage("구독");
//		return result;
//	}
	
	/**
	 * 알림 메세지에 자기기록 남기기 And 타인에게 관련 정보 전송..
	 * @param MessageData, String primaryKey
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/whisper-user")
	@SendToUser(broadcast=true)
	public void sendMessageWhatIDid(HistoryMessage message, HttpServletRequest request, Principal principal) throws Exception {
		logger.info("historyMessage : {} : ", message.toString());
	}
//	@MessageMapping("/whisper-user")
//	@SendToUser(broadcast=true)
//	public void sendMessageWhatIDid(HistoryMessage historyMessage, HttpServletRequest request, Principal principal) throws Exception {
////		//이동해야 할 데이터의 PK값. => Script에서 넘겨준다.
////		messageData.setMessageKeyValue(primaryKey);
//		String primaryKey=message.getMessageKeyValue();
//		
//		//로그인 한 유저(기록을 남겨야 할 유저)
//		String logUser=principal.getName();
//		message.setMessageCreatedBy(logUser);
//		
//		//메세지를 보내야 할 유저(다른 메소드에서 바뀔 수 있게 만든 것뿐)
//		message.setMessageToUser(logUser);
//		
//		//POST메세지가 발생한 URI
//		String fromUri=request.getRequestURI();
//		message.setMessageFromUri(fromUri);
//		String[] uri=fromUri.split("/");		
//		String messageUri="";
//		String toMoveUri="";
//		
//		//[2]는 board, comment, survey등의 위치.
//		message.setMessageParamType(uri[2]);
//		
//		//각 기능들의 결과값을 다르게 저장하기때문에, 메소드로 나누었다. - build messageUri
//		//devideObjectNameForMessage
//		String orginUri=devideObjectNameForMessage(primaryKey, uri, messageUri, toMoveUri, message, request, principal);
//		
//		logger.info("param : orginUri : {}", orginUri);
//		
//		message.setMessageContent(logUser+"님께서 "+orginUri+"에 #"+primaryKey+"의 새 "+messageUri+"을 등록하셨습니다.");
//		
//		logger.info("param : messageData : {}", message);
//		
////		messageService.insertMessageData(message);
//		messaging.convertAndSendToUser(logUser, "/queue/whisper-message", message);
//	}
	
//	/**
//	 * 알림 클릭시 페이지 이동(10개)
//	 * @param MessageData, Long path
//	 * @return String : redirect
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/move/{path}", method=RequestMethod.GET)
//	public String moveSelectedMessage(@PathVariable Long path, MessageData messageData) throws Exception {
//		//Message PK값을 담는다.
//		messageData.setMessageIdx(path);
//		messageData=messageService.selectMessageData(messageData);
//		
//		//읽은것으로 업데이트.
//		messageService.update(messageData);
//		logger.info("return : moveSelectedMessage : {} ", messageData);
//		
//		//message에서 필요한 값을 담는다.
//		String messageToMoveUri=messageData.getMessageToMoveUri();
//		String messageParamType=messageData.getMessageParamType();
//		String messageKeyValue=messageData.getMessageKeyValue();
//		
//		//Mapping을 통일하지 않는이상 controller마다 매핑위치가 다를 수 있기에 여러개로 놓는다.
//		switch (messageParamType) {
//		case "board": 
//			return "redirect:"+messageToMoveUri+"/"+messageKeyValue;
//		case "comment": 
//			return "redirect:"+messageToMoveUri+"/"+messageKeyValue;
//		case "survey": 
//			return "redirect:"+messageToMoveUri+"/"+messageKeyValue;
//		case "diagnosis":
//			return "redirect:"+messageToMoveUri+"/"+messageKeyValue;
//		}
//		return "redirect:"+messageToMoveUri+"/"+messageKeyValue;
//	}
	
//	private String devideObjectNameForMessage(String primaryKey, String uri[], String messageUri, String toMoveUri, MessageData messageData, HttpServletRequest request, Principal principal) throws Exception{
//		String defaultParam="/";
//		/** 게시판 POST URI */
//		//	ex) /tunner/board/notice/insert
//		//	[0] "" : [1] tunner : [2] board : [3]notice
//		
//		/** 댓글 POST URI */
//		/// ex) /tunner/comment/insert
//		//	[0] "" : [1] tunner : [2] comment : [3]insert
//		String originUri="게시물";
//		switch (uri[2]) {
//		case "board": messageUri="게시물";
//			originUri="게시물";
//			//Split Uri를 재결합한다.(이동 할 페이지로) - 2까지가 기본 Page의 Mapping공통된 속성.
//			for (int i = 2; i <= 3; i++) {
//				toMoveUri+=defaultParam+uri[i];
//			}
//			toMoveUri+=defaultParam+"detail";
//			logger.info("return : devideObjectNameForMessage : {} ", toMoveUri);
//			messageData.setMessageToMoveUri(toMoveUri);
//			//게시판 타입데이터.
//			return originUri;
//		case "comment": messageUri="댓글";
//			//댓글의 결과는 게시판에 있어 POST가 발생한 곳과 URI가 다르기 때문에, 이동해야 할 URI값을 주입시킨다.
//			toMoveUri="/board/detail";
//			messageData.setMessageToMoveUri(toMoveUri);
//			
//			//댓글 단 게시물을 가져온다.
//			Map<String, Object> boardMap=new HashMap<>();
//			boardMap.put("BOARD_IDX", primaryKey);
//			originUri="게시물";
//			boardMap=boardService.selectBoardDetail(boardMap);
//			logger.info("return : devideObjectNameForMessage : {} ", boardMap);
//			
//			//게시물에서 글쓴이를 가져온다.
//			@SuppressWarnings("unchecked") 
//			Map<String, Object> map=(Map<String, Object>)boardMap.get("map");
//			String createdBoard=map.get("BOARD_CREATED_BY").toString();
//			
//			//게시물의 글쓴이에게 댓글이 달렸음을 보낸다.
//			if(!createdBoard.equals(principal.getName())){
//				sendMessageToUser(createdBoard, primaryKey, uri, toMoveUri, originUri, messageUri, messageData, request, principal);	
//			}
//			return originUri;
//		case "survey": messageUri="설문지";
//			originUri="설문지";
//			return originUri;
//		case "diagnosis": messageUri="진단";
//			originUri="진단";
//			return originUri;
//		}
//		return originUri;
//	}
//	
//	private void sendMessageToUser(String toUser, String primaryKey, String[] uri, String toMoveUri, String originUri, String messageUri, MessageData messageData, HttpServletRequest request, Principal principal) throws Exception {
//		MessageData newMessageData=new MessageData();
//		newMessageData=messageData;
//		
//		//이동해야 할 데이터의 PK값.
//		newMessageData.setMessageKeyValue(primaryKey);
//		
//		//로그인 한 유저(기록을 남겨야 할 유저)
//		String logUser=principal.getName();
//		newMessageData.setMessageCreatedBy(logUser);
//		
//		//메세지를 보내야 할 유저(다른 메소드에서 바뀔 수 있게 만든 것뿐)
//		newMessageData.setMessageToUser(toUser);
//		
//		newMessageData.setMessageContent(toUser+"님의 #"+primaryKey+"의 "+originUri+"에 "+logUser+ "님의 새 "+messageUri+"이 등록되었습니다.");
//		
//		messageService.insertMessageData(messageData);
//		messaging.convertAndSendToUser(toUser, "/queue/whisper-message", newMessageData);
//	}
}