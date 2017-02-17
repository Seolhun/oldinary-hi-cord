//package com.brain.home.controller.message;
//
//import java.security.Principal;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SendToUser;
//import org.springframework.messaging.simp.annotation.SubscribeMapping;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import imedisyn.home.mybatis.message.MessageDAO;
//import imedisyn.home.mybatis.message.model.Message;
//import imedisyn.home.mybatis.message.model.Result;
//
//@Controller
//public class MessageController {
//
//	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
//	
//	@Autowired
//	private MessageDAO mDao;
//
//	@Autowired
//	private SimpMessagingTemplate messaging;
//
//	@RequestMapping("/chat")
//	public String textChat() {
//		return "message/chat";
//	}
//
//	@RequestMapping("/gift")
//	public String sendGift(Principal principal, ModelMap model) {
//		model.addAttribute("principal", principal.getName());
//		return "message/gift";
//	}
//	
//	@RequestMapping("/post")
//	public String post(Principal principal, ModelMap model) {
//		model.addAttribute("principal", principal.getName());
//		return "message/post";
//	}
//
//	@RequestMapping("/subscribe")
//	public String subscribe(Principal principal, ModelMap model) {
//		model.addAttribute("principal", principal.getName());
//		return "message/subscribe";
//	}
//	
//	@MessageMapping("/send")
//	@SendTo("/queue/message")
//	public Result sendChat(Message message, Principal principal) throws Exception {
//		logger.info("TEST sendChat : " + message.getText() + " : " + message.getToUser());
//		Result result = new Result(message.getText(), principal.getName());
//		mDao.saveMessage(result);
//		return result;
//	}
//
//	@MessageMapping("/gift/{toUser}")
//	@SendToUser(broadcast=true)
//	public void sendGift(Message message, Principal principal, @DestinationVariable String toUser) throws Exception {
//		logger.info("TEST sendGift : Message : " + message.getText() + " : toUser : "+toUser);
//		Result result = new Result(message.getText(), principal.getName(), toUser);
//		mDao.saveMessage(result);
//		messaging.convertAndSendToUser(toUser, "/topic/message", result);
//	}
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
//}