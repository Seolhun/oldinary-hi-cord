package com.hi.cord.first.message;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.first.message.entity.HistoryMessage;
import com.hi.cord.first.message.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageRestController {
	static final Logger log = LoggerFactory.getLogger(MessageRestController.class);

	@Autowired
	private MessageService messageService;
	
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
}