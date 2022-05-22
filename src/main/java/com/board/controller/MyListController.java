package com.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;//로그를 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.MemberDao;
import com.board.dao.R1BoardDao;
import com.board.domain.R1BoardVO;
import com.board.util.PagingUtil; //페이징처리패키지

@Component
@Controller
public class MyListController {
	
	private Logger log=Logger.getLogger(this.getClass());//현재클래스명을 불러와서 지정

	@Autowired
	private R1BoardDao boardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/board/MyPage.do")
	public ModelAndView process(
		@RequestParam("mem_id") String mem_id,
			HttpServletRequest req,
		@RequestParam(value="pageNum",defaultValue="1") int currentPage,//페이지번호
		@RequestParam(value="keyField",defaultValue="") String keyField,//검색분야
		@RequestParam(value="keyWord",defaultValue="") String keyWord//검색어		
			) {
			if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
				System.out.println("/board/MyPage.do 요청중");//?을 출력X
				log.debug("currentPage"+currentPage);//?를 출력가능(select~where num=?)
				log.debug("keyField"+keyField);
				log.debug("keyWord"+keyWord);
				log.debug("/board/MyPage.do의 mem_id"+mem_id);
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			//총레코드수 또는 검색된 글의 총레코드수
			int count=boardDao.getRowCount(map);
			System.out.println("MyListController 클래스의 count=>"+count);//10
			
		PagingUtil page=new PagingUtil(currentPage,count,3,3,"MyPage.do");
		
		map.put("start", page.getStartCount());//<->map.get("start")=>#{start}
		map.put("end", page.getEndCount());//<->map.get("end")=>#{end}
		
		List<R1BoardVO> list=null;
		if(count>0) {
			list=boardDao.list(map);//keyField,keyWord,start,end
			System.out.println("R1ListController2의 list=>"+list);
		}else {
			list=Collections.EMPTY_LIST;//0 적용
		}
		
		ModelAndView mav=new ModelAndView("MyPage");//boardList.jsp 에서 찾아서 뿌린다
		mav.addObject("count",count);//총레코드수
		mav.addObject("list", list);//검색된 레코드리스트
		mav.addObject("pagingHtml",page.getPagingHtml());//<a href="~?>링크문자열
		mav.addObject("mem_id",mem_id);
		return mav;
	}
}
