package com.brain.home.controller.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.brain.home.common.CommonFn;
import com.brain.home.entity.board.Board;
import com.brain.home.entity.board.Comment;
import com.brain.home.entity.board.Reply;
import com.brain.home.entity.common.Paging;
import com.brain.home.service.board.BoardService;
import com.brain.home.service.board.comment.CommentService;
import com.brain.home.service.board.reply.ReplyService;

@Controller
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "txManager", noRollbackFor = {NullPointerException.class })
@RequestMapping("/talk")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommonFn cFn;

	@RequestMapping(value = { "/{tableName}" }, method = RequestMethod.GET)
	public String boardList(Model model, HttpServletRequest request, @PathVariable("tableName") String tableName) {

		int currentPage = cFn.checkVDInt(request.getParameter("cPage"), 1);
		int sType = cFn.checkVDInt(request.getParameter("sType"), -1);
		String rawQuestion = request.getParameter("sText");
		int sDate = cFn.checkVDInt(request.getParameter("sDate"), 0);
		int limit = cFn.checkVDInt(request.getParameter("limit"), 20);

		Paging paging = new Paging(currentPage, sType, rawQuestion, sDate, limit, tableName);
		int totalCount = boardService.getCount(paging);
		paging.setTotalPage(totalCount);
		setPaging(paging);

		//답글 객체 저장
		ArrayList<Board> boardList = (ArrayList<Board>) boardService.findAll(paging);
		for (int i = 0; i < boardList.size(); i++) {
			ArrayList<Reply> replyList = (ArrayList<Reply>) replyService.findAll(paging);
			boardList.get(i).setReplyListInBoard(replyList);
		}
		
		model.addAttribute("board", new Board());
		model.addAttribute("boards", boardList);
		model.addAttribute("tableName", tableName);
		model.addAttribute("paging", paging);
		
		return "views/board/list";
	}
	
	@Secured("SUPERADMIN")
	@RequestMapping(value = "/{tableName}/detail/{path}", method = RequestMethod.GET)
	public String boardDetail(Model model, @PathVariable("path") Long path, @PathVariable("tableName") String tableName, HttpServletRequest request, HttpServletResponse response) {
		Board item = boardService.findById(path);

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
		if (checkHitCookie(item, request, response)) {
			item.setHits(item.getHits()+1);
			boardService.update(item);
		}

		model.addAttribute("item", item);
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

		return "redirect:/board/" + tableName + "/";
	}
	
	@RequestMapping(value = "/edit/{path}", method = RequestMethod.POST)
	public String boardUpdateIn(Model model, Board board, @PathVariable("path") Long path,
			@PathVariable("tableName") String tableName, final RedirectAttributes redirectAttributes) {
		board.setId(path);
		if (boardService.update(board) != null) {
			redirectAttributes.addFlashAttribute("editBoard", "success");

			Board temp = boardService.findById(path);
			model.addAttribute("item", temp);

		} else {
			redirectAttributes.addFlashAttribute("editBoard", "unsuccess");

			return "redirect:/board/" + tableName + "/reply/" + path;
		}
		return "redirect:/board/" + tableName + "/detail/" + path;
	}
	
	@RequestMapping(value = "/delete/ajax", method = RequestMethod.POST)
	@ResponseBody
	public String boardAjaxDelete(@RequestBody Board board, HttpServletRequest request){
		Board temp = boardService.findById(board.getId());
		temp.setDelCheck(0);
		if(boardService.update(temp) == null);
			System.out.println(board.getId());
		return "true";
	}
	
	@RequestMapping(value = "/reply/{path}", method = RequestMethod.POST)
	public String replyAddIn(Reply reply, @PathVariable("path") Long path, @PathVariable("tableName") String tableName) {
		
		System.out.println(reply.getContent());
		Board board = new Board();
		board.setId(path);
		reply.setType(tableName);
		reply.setBoardInReply(board);
		replyService.save(reply);

		//게시판 ReplyDepth 증가
		board = boardService.findById(path);
		board.setReplyDepth(board.getReplyDepth()+1);
		boardService.update(board);
		
		return "redirect:/board/" + tableName + "/";
	}

	/*----------- 댓글 기능 Start ---------------------------------------------------------------*/
	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	@ResponseBody
	public void commentAjaxAdd(@RequestBody Comment comment, HttpServletRequest request){
		System.out.println(comment.getContent());
//			commentService.save(comment);
	}	
	
	
	/*----------- end 댓글 ---------------------------------------------------------------------*/

	
	
	/*----------- 페이징 메소드 Start ------------------------------------------------------------*/
	private Paging setPaging(Paging paging) {
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
	
	private boolean checkHitCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String id = board.getId().toString();
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