package com.hi.cord.first.controller.price;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hi.cord.common.model.Paging;
import com.hi.cord.common.CommonFn;
import com.hi.cord.first.entity.price.Price;
import com.hi.cord.first.service.price.PriceRecordService;
import com.hi.cord.first.service.price.PriceService;

@Controller
public class PriceController {
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private PriceRecordService priceRecordService;
	
	@Autowired
	private CommonFn cFn;
	
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public String pricePage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int currentPage = cFn.checkVDInt(request.getParameter("cPage"), 1);
		int sType = cFn.checkVDInt(request.getParameter("sType"), -1);
		String rawQuestion = request.getParameter("sText");
		int sDate = cFn.checkVDInt(request.getParameter("sDate"), 0);
		int limit = cFn.checkVDInt(request.getParameter("limit"), 20);

		Paging paging = new Paging(currentPage, sType, rawQuestion, sDate, limit);
		int totalCount = priceService.getCount(paging);
		paging.setTotalPage(totalCount);
		setPaging(paging);

		//답글 객체 저장
		ArrayList<Price> priceList = (ArrayList<Price>) priceService.findAllPrices(paging);
		
		model.addAttribute("prices", priceList);
		model.addAttribute("paging", paging);
		return "views/price/list";
	}
	
	@RequestMapping(value = "/superadmin/price", method = RequestMethod.GET)
	public String superAdminPriceList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		return "views/price/list";
	}
	
	@RequestMapping(value = "/superadmin/price/add", method = RequestMethod.GET)
	public String superAdminPriceAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		return "views/price/list";
	}
	
	@RequestMapping(value = "/superadmin/price/edit", method = RequestMethod.GET)
	public String superAdminPriceEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		return "views/price/list";
	}
	
	@RequestMapping(value = "/superadmin/price/delete", method = RequestMethod.GET)
	public String superAdminPriceDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		return "views/price/list";
	}
	
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

}
