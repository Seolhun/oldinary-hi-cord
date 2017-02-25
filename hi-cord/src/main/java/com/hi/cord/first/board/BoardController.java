package com.hi.cord.first.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hi.cord.common.model.AjaxResult;
import com.hi.cord.common.service.CommonService;
import com.hi.cord.first.board.entity.Board;
import com.hi.cord.first.board.entity.BoardType;
import com.hi.cord.first.board.service.BoardService;
import com.hi.cord.first.comment.entity.Comment;
import com.hi.cord.first.reply.entity.Reply;
import com.hi.cord.first.reply.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {
	static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private CommonService cFn;

	/**
	 * 게시판 이동.
	 * 
	 * @param String
	 *            boardType
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/{bType}" }, method = RequestMethod.GET)
	public String moveBoardList(Model model, HttpServletRequest request, @PathVariable("bType") String bType)
			throws Exception {
		model.addAttribute("whereIs", "Board");
		model.addAttribute("board", new Board());
		model.addAttribute("bType", bType);
		return "views/board/board-list";
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
	@ResponseBody
	public List<Board> boardList(Model model, HttpServletRequest request, @PathVariable("bType") String bType,
			Board board) throws Exception {
		// 답글 객체 저장
		board.setBoardType(bType);
		List<Board> boardList = boardService.findAll(board);

		model.addAttribute("boardList", boardList);
		model.addAttribute("bType", bType);
		return boardList;
	}

	/**
	 * Board Data Insert
	 * 
	 * @param Board
	 *            board
	 * @return List<Board>
	 * @throws Exception
	 */
	@RequestMapping(value = "/{bType}/write", method = RequestMethod.GET)
	public String boardAdd(Model model, @PathVariable("bType") String bType, Authentication auth) {
		model.addAttribute("board", new Board());
		model.addAttribute("boardTypes", BoardType.values());
		model.addAttribute("bType", bType);
		return "views/board/board-write";
	}

	@RequestMapping(value = "/{bType}/write", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
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
	 * 게시판 상세보기 출력.
	 * 
	 * @param Board
	 *            board
	 * @return List<Board>
	 * @throws Exception
	 */
	@Secured("SUPERADMIN")
	@RequestMapping(value = "/{bType}/detail/{path}", method = RequestMethod.GET)
	public String boardDetail(Model model, @PathVariable("path") Long path, @PathVariable("bType") String bType,
			HttpServletRequest request, HttpServletResponse response) {
		Board board = boardService.findById(path);

		// 저장된 hit 쿠키 불러오기
		Cookie cookies[] = request.getCookies();
		Map<String, String> mapCookie = new HashMap<String, String>();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("hit")) {
					Cookie obj = cookies[i];
					mapCookie.put(obj.getName(), obj.getValue());
				}
			}
		}

		// 조회수 증가
		if (checkHitCookie(board, request, response)) {
			board.setBoardHits(board.getBoardHits() + 1);
			boardService.update(board);
		}

		model.addAttribute("board", board);
		model.addAttribute("bType", bType);
		return "views/board/detail";
	}

	@RequestMapping(value = "/{tableName}/edit/{path}", method = RequestMethod.GET)
	public String boardEditGet(Model model, @PathVariable("path") Long path,
			@PathVariable("tableName") String tableName) {
		model.addAttribute("board", new Board());
		model.addAttribute("tableName", tableName);
		return "views/board/add";
	}

	@RequestMapping(value = "/{tableName}/edit/{path}", method = RequestMethod.POST)
	public String boardEditPost(Model model, Board board, @PathVariable("path") Long path,
			@PathVariable("tableName") String tableName, final RedirectAttributes redirectAttributes) {
		board.setBoardId(path);
		if (boardService.update(board) != null) {
			redirectAttributes.addFlashAttribute("editBoard", "success");

			Board temp = boardService.findById(path);
			model.addAttribute("item", temp);

		} else {
			redirectAttributes.addFlashAttribute("editBoard", "unsuccess");

			return "redirect:/talk/" + tableName + "/reply/" + path;
		}
		return "redirect:/talk/" + tableName + "/detail/" + path;
	}

	@RequestMapping(value = "/{tableName}/delete/ajax", method = RequestMethod.POST)
	@ResponseBody
	public String boardAjaxDelete(@RequestBody Board board, HttpServletRequest request) {
		Board temp = boardService.findById(board.getBoardId());
		temp.setBoardDelCheck(0);
		if (boardService.update(temp) == null)
			;
		System.out.println(board.getBoardId());
		return "true";
	}

	@RequestMapping(value = "/{tableName}/reply/{path}", method = RequestMethod.POST)
	public String replyAddIn(Reply reply, @PathVariable("path") Long path,
			@PathVariable("tableName") String tableName) {

		System.out.println(reply.getContent());
		Board board = new Board();
		board.setBoardId(path);
		reply.setType(tableName);
		reply.setBoardInReply(board);
		replyService.save(reply);

		// 게시판 ReplyDepth 증가
		board = boardService.findById(path);
		board.setBoardReplyDepth(board.getBoardReplyDepth() + 1);
		boardService.update(board);

		return "redirect:/talk/" + tableName + "/";
	}

	/*----------- 댓글 기능 Start ---------------------------------------------------------------*/
	@RequestMapping(value = "/{tableName}/comment/add", method = RequestMethod.POST)
	@ResponseBody
	public void commentAjaxAdd(@RequestBody Comment comment, HttpServletRequest request) {
		System.out.println(comment.getCommentContent());
		// commentService.save(comment);
	}

	/*----------- end 댓글 ---------------------------------------------------------------------*/

	private boolean checkHitCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String id = board.getBoardId().toString();
		String[] tableNames = request.getRequestURI().split("/");
		String tableName = tableNames[2];
		// System.out.println("**************" + request.getRemoteAddr() +
		// "**************");
		// System.out.println(tableName);
		// System.out.println(id);
		// 쿠키 이름값 보안처리하기
		tableName = cFn.buildSHA256(tableName).substring(0, 5);

		// 쿠키 가져오기(체크 하는것)
		Map<String, String> cookieMap = new HashMap<>();
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i].getValue());
			}
		}

		// 저장된 쿠키 중 읽었었던 해당 게시판의 번호 불러오기
		String originalNo = (String) cookieMap.get(tableName);
		Cookie cookie = null;
		if (originalNo == null) {
			cookie = new Cookie(tableName, id);
		} else {
			String[] upHitByNo = originalNo.split("T3Y1");
			for (int i = 0; i < upHitByNo.length; i++) {
				if (upHitByNo[i].equals(id)) {
					return validHit;
				}
			}
			originalNo = originalNo + "T3Y1" + id;
			cookie = new Cookie(tableName, originalNo);
		}

		// cookie.setPath("/imedisyn");
		cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
		response.addCookie(cookie);
		validHit = true;
		return validHit;
	}
	
	private Board loginNameSetToObject(Board board, Authentication auth){
		String loginName=auth.getName();
		board.setBoardCreatedBy(loginName);
		return board;
	}
}