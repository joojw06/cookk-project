package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.EventBoardDao;
import com.board.dao.MainDao;
import com.board.dao.MemberDao;
import com.board.domain.EventVO;
import com.board.domain.R1BoardVO;
import com.board.domain.R2BoardVO;

@Component
@Controller
//@RequestMapping(value="/board/Main.do")
public class MainController2 {

		 private Logger log=Logger.getLogger(this.getClass());//현재클래스명을 불러와서 지정
	
	@Autowired
	private EventBoardDao eventboardDao; //자동적으로 Setter Method호출X(의존성객체 넣어줌)
	
	@Autowired
	private MainDao mainDao;
	
	//추가
	@Autowired
	private MemberDao memberDao;

	@RequestMapping(value="/board/Main2.do")
	   public ModelAndView EventBoardList2
		(@RequestParam("manager_id") String manager_id,
		 @RequestParam("writer2") String writer2,HttpServletRequest req) throws Exception {	
				
				List<EventVO> list=null;	 // 이벤트
				List<R2BoardVO> getDescRe_main=null; // 회원
				List<R1BoardVO> getDescRe_main2=null; // 관리자
				
				list=eventboardDao.list(); // 이벤트
				getDescRe_main=mainDao.getDescRe_main(); // 회원
				getDescRe_main2=mainDao.getDescRe_main2(); // 관리자
				//추가
				System.out.println("/board/Main2.do의 manager_id=>"+manager_id);
				System.out.println("/board/Main2.do의 writer2=>"+writer2);
			
				ModelAndView mav=new ModelAndView("Main2");
				/*
				//세션에서 꺼내오기
				HttpSession session=req.getSession();
				ManagerVO vo=(ManagerVO)session.getAttribute("member2");
				String manager_id=vo.getManager_id();
				System.out.println("/board/Main2.do의 manager_id=>"+manager_id);
				
				String writer2=memberDao.getManagerName(vo.getManager_id());
				//String writer2=vo.getWriter2();//관리자 nickname
				System.out.println("/board/Main2.do로 요청시 writer2->"+writer2);
				*/
				mav.addObject("list", list); // 이벤트
				mav.addObject("getDescRe_main", getDescRe_main); // 회원
				mav.addObject("getDescRe_main2", getDescRe_main2); // 회원
				//추가
				mav.addObject("manager_id",manager_id);
				mav.addObject("writer2",writer2);
				//----------------------------------------------------------------------
				return mav;				
			}			
}