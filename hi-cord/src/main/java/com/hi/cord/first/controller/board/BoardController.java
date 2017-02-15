package com.hi.cord.first.controller.board;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hi.cord.common.CommonFn;
import com.hi.cord.first.entity.board.Board;
import com.hi.cord.first.entity.board.Comment;
import com.hi.cord.first.entity.board.Reply;
import com.hi.cord.first.service.board.BoardService;
import com.hi.cord.first.service.board.reply.ReplyService;

@Controller
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class })
@RequestMapping("/board")
public class BoardController {
	static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private CommonFn cFn;

	@RequestMapping(value = { "/{boardType}" }, method = RequestMethod.GET)
	public String moveBoardList(Model model, HttpServletRequest request, @PathVariable("boardType") String boardType, Board board) throws Exception {
		model.addAttribute("whereIs", "Board");
		model.addAttribute("board", board);
		model.addAttribute("boardType", boardType);
		return "views/board/board-list";
	}
	
	@RequestMapping(value = { "/{boardType}/list-json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Board> boardList(Model model, HttpServletRequest request, @PathVariable("boardType") String boardType, Board board) throws Exception {

//		int currentPage = cFn.checkVDInt(request.getParameter("cPage"), 1);
//		int sType = cFn.checkVDInt(request.getParameter("sType"), -1);
//		String rawQuestion = request.getParameter("sText");
//		int sDate = cFn.checkVDInt(request.getParameter("sDate"), 0);
//		int limit = cFn.checkVDInt(request.getParameter("limit"), 20);
//
//		Paging paging = new Paging(currentPage, sType, rawQuestion, sDate, limit, tableName);
//		int totalCount = boardService.getCount(paging);
//		paging.setTotalPage(totalCount);
//		cFn.setPaging(paging);

		//답글 객체 저장
		board.setBoardType(boardType);
		List<Board> boardList = (ArrayList<Board>) boardService.findAll(board);
//		for (int i = 0; i < boardList.size(); i++) {
//			List<Reply> replyList = (ArrayList<Reply>) replyService.findAll(paging);
//			boardList.get(i).setReplyListInBoard(replyList);
//		}
		log.info("TEST : "+boardList.toString());
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("board", board);
		model.addAttribute("boardType", boardType);
		return boardList;
	}
	
	@Secured("SUPERADMIN")
	@RequestMapping(value = "/{tableName}/detail/{path}", method = RequestMethod.GET)
	public String boardDetail(Model model, @PathVariable("path") Long path, @PathVariable("tableName") String tableName, HttpServletRequest request, HttpServletResponse response) {
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
			board.setBoardHits(board.getBoardHits()+1);
			boardService.update(board);
		}

		model.addAttribute("board", board);
		model.addAttribute("tableName", tableName);
		return "views/board/detail";
	}

	@RequestMapping(value = "/{tableName}/add", method = RequestMethod.GET)
	public String boardAdd(Model model, @PathVariable("tableName") String tableName) {
		model.addAttribute("board", new Board());
		model.addAttribute("tableName", tableName);
		return "views/board/add";
	}

	@RequestMapping(value = "/{tableName}/add", method = RequestMethod.POST)
	public String boardAddIn(Board board, @PathVariable("tableName") String tableName, final RedirectAttributes redirectAttributes) {
		try {
			board.setBoardType(tableName);
			boardService.save(board);
			redirectAttributes.addFlashAttribute("saveBoard", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("saveBoard", "unsuccess");
		}
		return "redirect:/talk/" + tableName;
	}
	
	@RequestMapping(value = "/{tableName}/edit/{path}", method = RequestMethod.GET)
	public String boardEditGet(Model model, @PathVariable("path") Long path, @PathVariable("tableName") String tableName) {
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
	public String boardAjaxDelete(@RequestBody Board board, HttpServletRequest request){
		Board temp = boardService.findById(board.getBoardId());
		temp.setBoardDelCheck("Y");
		if(boardService.update(temp) == null);
			System.out.println(board.getBoardId());
		return "true";
	}
	
	@RequestMapping(value = "/{tableName}/reply/{path}", method = RequestMethod.POST)
	public String replyAddIn(Reply reply, @PathVariable("path") Long path, @PathVariable("tableName") String tableName) {
		
		System.out.println(reply.getContent());
		Board board = new Board();
		board.setBoardId(path);
		reply.setType(tableName);
		reply.setBoardInReply(board);
		replyService.save(reply);

		//게시판 ReplyDepth 증가
		board = boardService.findById(path);
		board.setBoardReplyDepth(board.getBoardReplyDepth()+1);
		boardService.update(board);
		
		return "redirect:/talk/" + tableName + "/";
	}

	/*----------- 댓글 기능 Start ---------------------------------------------------------------*/
	@RequestMapping(value = "/{tableName}/comment/add", method = RequestMethod.POST)
	@ResponseBody
	public void commentAjaxAdd(@RequestBody Comment comment, HttpServletRequest request){
		System.out.println(comment.getContent());
//			commentService.save(comment);
	}	
	
	
	/*----------- end 댓글 ---------------------------------------------------------------------*/
	
	private boolean checkHitCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String id = board.getBoardId().toString();
		String[] tableNames = request.getRequestURI().split("/");
		String tableName = tableNames[2];
//		System.out.println("**************" + request.getRemoteAddr() + "**************");
//		System.out.println(tableName);
//		System.out.println(id);
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
}