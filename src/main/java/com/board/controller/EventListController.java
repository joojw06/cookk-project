package com.board.controller;

import java.util.List;

import org.apache.log4j.Logger;//로그를 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.EventBoardDao;
import com.board.dao.MemberDao;
import com.board.domain.EventVO;

@Component
@Controller
public class EventListController {

	private Logger log=Logger.getLogger(this.getClass());//현재클래스명을 불러와서 지정
	
	@Autowired
	private EventBoardDao eventboardDao; //자동적으로 Setter Method호출X(의존성객체 넣어줌)
	
	//추가
	@Autowired
	private MemberDao memberDao;
	
	// 1. 이벤트 게시판으로 이동
	@RequestMapping(value="/board/eventList.do")
	public ModelAndView EventBoardList(@RequestParam("manager_id") String manager_id,
			                                             @RequestParam("writer2") String writer2) throws Exception {	
		
		//int count=boardDao.getRowCount(map);
		List<EventVO> list=null;		
		
		list=eventboardDao.list();//keyField,keyWord,start,end
	
		ModelAndView mav=new ModelAndView("eventList");//eventList.jsp
		mav.addObject("list", list);//검색된 레코드리스트
		//추가
		mav.addObject("manager_id",manager_id);
		mav.addObject("writer2",writer2);
		
		return mav;
	}
}
		