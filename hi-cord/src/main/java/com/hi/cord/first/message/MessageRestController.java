package com.hi.cord.first.message;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.first.board.entity.Board;
import com.hi.cord.first.board.service.BoardService;
import com.hi.cord.first.message.entity.HistoryMessage;
import com.hi.cord.first.message.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageRestController {
	static final Logger log = LoggerFactory.getLogger(MessageRestController.class);

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private SimpMessagingTemplate messaging;
	
	/**
	 * Message 인서트
	 * 
	 * @param HistoryMessage
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
	public AjaxResult insertHistoryMessage(@Valid HistoryMessage historyMessage, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	/**
	 * Message Select
	 * 
	 * @param HistoryMessage
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/one" }, method = RequestMethod.GET)
	public HistoryMessage selectOne(@Valid HistoryMessage historyMessage, ModelMap model) throws Exception{
		
		
		return historyMessage;
	}
	
	/**
	 * Message SelectList
	 * 
	 * @param HistoryMessage
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public List<HistoryMessage> selectList(@Valid HistoryMessage historyMessage, ModelMap model) throws Exception{
		
		List<HistoryMessage> historyMessageList=new ArrayList<>();
		return historyMessageList;
	}
	
	/**
	 * Message Update
	 * 
	 * @param HistoryMessage
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/{path}" }, method = RequestMethod.PUT)
	public AjaxResult updateHistoryMessage(@Valid HistoryMessage historyMessage, @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	
	/**
	 * Message delete
	 * 
	 * @param HistoryMessage
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/delete/{path}" }, method = RequestMethod.DELETE)
	public AjaxResult deleteHistoryMessage(@Valid HistoryMessage historyMessage,  @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		return ajaxResult;
	}
	
	/**
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
	
	@MessageMapping("/whisper-user")
	@SendToUser(broadcast=true)
	public void sendMessageWhatIDid(HistoryMessage historyMessage, Principal principal) throws Exception {
		log.info("param : sendMessageWhatIDid : {} ",historyMessage.toString());
//		//이동해야 할 데이터의 PK값. => Script에서 넘겨준다.
//		messageData.setMessageKeyValue(primaryKey);
		Long primaryKey=historyMessage.getHistoryMessageToMovePK();
		
		//로그인 한 유저(기록을 남겨야 할 유저)
		String logUser=principal.getName();
		historyMessage.setHistoryMessageCreatedBy(logUser);
		
		//메세지를 보내야 할 유저(다른 메소드에서 바뀔 수 있게 만든 것뿐)
		historyMessage.setHistoryMessageToUser(logUser);
		
		//POST메세지가 발생한 URI
		String fromUri=historyMessage.getHistoryMessageToMoveUri();
		historyMessage.setHistoryMessageToMoveUri(fromUri);
		String[] uri=fromUri.split("/");		
		String messageUri="";
		String toMoveUri="";
		
		//[2]는 board, comment, survey등의 위치.
		historyMessage.setHistoryMessageParamType(uri[1]);
		
		//각 기능들의 결과값을 다르게 저장하기때문에, 메소드로 나누었다. - build messageUri
		//devideObjectNameForMessage
		String orginUri=devideObjectNameForMessage(primaryKey, uri, messageUri, toMoveUri, historyMessage, fromUri, principal);
		
		log.info("param : orginUri : {}", orginUri);
		
		historyMessage.setHistoryMessageContent(logUser+"님께서 "+orginUri+"에 #"+primaryKey+"의 새 "+messageUri+"을 등록하셨습니다.");
		
		log.info("param : messageData : {}", historyMessage);
		
//		messageService.insertMessageData(message);
		messaging.convertAndSendToUser(logUser, "/queue/whisper-message", historyMessage);
	}
	
	private String devideObjectNameForMessage(Long primaryKey, String uri[], String messageUri, String toMoveUri, HistoryMessage historyMessage, String fromUri, Principal principal) throws Exception{
		String defaultParam="/";
		/** 게시판 POST URI */
		//	ex) /tunner/board/notice/insert
		//	[0] "" : [1] tunner : [2] board : [3]notice
		
		/** 댓글 POST URI */
		/// ex) /tunner/comment/insert
		//	[0] "" : [1] tunner : [2] comment : [3]insert
		String originUri="게시물";
		switch (uri[2]) {
		case "board": messageUri="게시물";
			originUri="게시물";
			//Split Uri를 재결합한다.(이동 할 페이지로) - 2까지가 기본 Page의 Mapping공통된 속성.
			for (int i = 2; i <= 3; i++) {
				toMoveUri+=defaultParam+uri[i];
			}
			toMoveUri+=defaultParam+"detail";
			log.info("return : devideObjectNameForMessage : {} ", toMoveUri);
			historyMessage.setHistoryMessageToMoveUri(toMoveUri);
			//게시판 타입데이터.
			return originUri;
		case "comment": messageUri="댓글";
			//댓글의 결과는 게시판에 있어 POST가 발생한 곳과 URI가 다르기 때문에, 이동해야 할 URI값을 주입시킨다.
			toMoveUri="/board/detail";
			historyMessage.setHistoryMessageToMoveUri(toMoveUri);
			
			//댓글 단 게시물을 가져온다.
			originUri="게시물";
			Board board=boardService.selectById(historyMessage.getHistoryMessageToMovePK());
			String createdBoard=board.getBoardCreatedBy();
			
			//게시물의 글쓴이에게 댓글이 달렸음을 보낸다.
			if(!createdBoard.equals(principal.getName())){
				sendMessageToUser(createdBoard, primaryKey, uri, toMoveUri, originUri, messageUri, historyMessage, fromUri, principal);	
			}
			return originUri;
		case "survey": messageUri="설문지";
			originUri="설문지";
			return originUri;
		case "diagnosis": messageUri="진단";
			originUri="진단";
			return originUri;
		}
		return originUri;
	}
	
	private void sendMessageToUser(String toUser, Long primaryKey, String[] uri, String toMoveUri, String originUri, String messageUri, HistoryMessage historyMessage, String fromUri, Principal principal) throws Exception {
		HistoryMessage newHistoryMessage=new HistoryMessage();
		newHistoryMessage=historyMessage;
		
		//이동해야 할 데이터의 PK값.
		newHistoryMessage.setHistoryMessageToMovePK(primaryKey);
		
		//로그인 한 유저(기록을 남겨야 할 유저)
		String logUser=principal.getName();
		newHistoryMessage.setHistoryMessageCreatedBy(logUser);
		
		//메세지를 보내야 할 유저(다른 메소드에서 바뀔 수 있게 만든 것뿐)
		newHistoryMessage.setHistoryMessageToUser(toUser);
		
		newHistoryMessage.setHistoryMessageContent(toUser+"님의 #"+primaryKey+"의 "+originUri+"에 "+logUser+ "님의 새 "+messageUri+"이 등록되었습니다.");
		
		messageService.insert(historyMessage);
		messaging.convertAndSendToUser(toUser, "/queue/whisper-message", newHistoryMessage);
	}
}