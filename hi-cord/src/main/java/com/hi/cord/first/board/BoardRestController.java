package com.hi.cord.first.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.first.board.entity.Board;
import com.hi.cord.first.board.service.BoardService;
import com.hi.cord.first.comment.entity.Comment;
import com.hi.cord.first.stadium.model.Stadium;

@RestController
@RequestMapping("/board")
public class BoardRestController {
	static final Logger log = LoggerFactory.getLogger(BoardRestController.class);

	@Autowired
	private BoardService boardService;

	/**
	 * 스타디움 인서트
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = "/{bType}/write", method = RequestMethod.POST, produces="application/json")
	public AjaxResult boardAddIn(@RequestBody Board board, @PathVariable("bType") String bType, AjaxResult ajaxResult, Authentication auth) throws Exception{
		log.info("param : "+ board.toString());
		
		//게시판 저장.
		try {
			loginNameSetToObject(board, auth);
			board.setBoardType(bType);
			boardService.save(board);
		} catch (NullPointerException e) {
			log.error("ERROR : NullPointerException");
			ajaxResult.setResult("fail");
			return ajaxResult;
		}
		ajaxResult.setResult("success");
		return ajaxResult;
	}
	
	
	/**
	 * 스타디움 Select
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/one" }, method = RequestMethod.GET)
	public Stadium selectOne(@Valid Stadium stadium, ModelMap model) throws Exception{
		
		
		return stadium;
	}
	
	/**
	 * 스타디움 SelectList
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public List<Stadium> selectList(@Valid Stadium stadium, ModelMap model) throws Exception{
		
		List<Stadium> stadiumList=new ArrayList<>();
		return stadiumList;
	}
	
	/**
	 * 스타디움 Update
	 * 
	 * @param Stadium
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/{path}" }, method = RequestMethod.PUT)
	public AjaxResult updateStadium(@Valid Stadium stadium, @PathVariable Long path, ModelMap model, AjaxResult ajaxResult) throws Exception{
		ajaxResult.setResult("fail");
		
		
		return ajaxResult;
	}
	
	/**
	 * 게시판 Json 리스트 출력.
	 * 
	 * @param Board
	 *            board
	 * @return List<Board>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/{bType}/list-json" }, method = RequestMethod.GET)
	public List<Board> boardList(Model model, HttpServletRequest request, @PathVariable("bType") String bType,
		Board board) throws Exception {
		// 답글 객체 저장
		board.setBoardType(bType);
		List<Board> boardList = boardService.findAll(board);

		model.addAttribute("boardList", boardList);
		model.addAttribute("bType", bType);
		return boardList;
	}
	
	@RequestMapping(value = "/{bType}/delete/{path}", method = RequestMethod.POST)
	public String boardAjaxDelete(@RequestBody Board board, HttpServletRequest request, @PathVariable("bType") String bType, @PathVariable Long path) {
		Board temp = boardService.findById(board.getBoardId());
		temp.setBoardDelCheck(0);
		if (boardService.update(temp) == null)
			;
		System.out.println(board.getBoardId());
		return "true";
	}
	
	/*----------- 댓글 기능 Start ---------------------------------------------------------------*/
	@RequestMapping(value = "/{bType}/comment/add-json", method = RequestMethod.POST)
	public void commentAjaxAdd(@RequestBody Comment comment, HttpServletRequest request, @PathVariable("bType") String bType) {
		
	}

	private Board loginNameSetToObject(Board board, Authentication auth){
		String loginName=auth.getName();
		board.setBoardCreatedBy(loginName);
		return board;
	}
}