package com.board.controller;

import org.apache.log4j.Logger; //로그객체를 불러오겠다고 선언한 부분
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dao.EventBoardDao;
import com.board.domain.EventVO;
import com.board.util.FileUtil;
import com.board.validator.BoardDeleteValidator;


@Component
@Controller
public class DeleteController {
    //Logger객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private EventBoardDao eventBoardDao;

	//1.글삭제 폼으로 이동(Get방식)
	@RequestMapping(value="/even/eventUpdate.do",method=RequestMethod.GET)
	public String form() {
		return "eventUpdate"; //return "이동할페이지명"; //defintion name과 동일
	}

	//에러메세지 출력->다시 초기화가 가능하게 설정->@ModelAttribute("별칭")
	@ModelAttribute("command")
	public EventVO forBacking() { //반환형(BoardCommand) 메서드명
		return new EventVO();//BoardCommand bc=new BoardCommand();
	}
	
	//입력해서 유효성검사->에러발생->DB저장후->boardList.jsp로 이동
	//BindingResult->유효성 검사때문에 필요(에러정보 객체)
	
	@RequestMapping(value="/event/deleteBoard2.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") EventVO com,
			                           BindingResult result) {
		if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			  System.out.println("/event/deleteBoard.do 요청중(post)");//? 을 출력X
			  log.debug("EventVO:"+com);//? 을 출력 가능(select ~ where num=?)
	          //로그객체명.debug(출력대상자)
		  }
	
		  /*
		  //유효성검사->command조사 문제발생->result에 에러정보를 담는다. 
		    newBoardDeleteValidator().validate(com,result); //에러정보가 있다면
		  if(result.hasErrors()) { return form();//"boardDelete"->boardDelete.jsp }
		*/
		
		 //변경전의 데이터를 불러오기->board(비밀번호)=웹상에서의 입력비밀번호
		  EventVO board=null;
		  board=eventBoardDao.selectBoard(com.getEvent_code());
	    
	    /*
	   // DB상의 암호!=웹상의 암호를 확인 절차
	    if (!board.getPwd().equals(com.getPwd())) {
			result.rejectValue("pwd", "invalidPassword");// 적용필드명,에러코드명
			return form();// boardDelete.jsp로 이동
		}else { //암호가 맞는경우
		*/
	    	eventBoardDao.delete(com.getEvent_code());//DB상의 데이터가 삭제
	    	
			//업로드한 파일까지 삭제
			if(board.getEvent_image()!=null) {//업로드한 파일이 존재한다며 삭제
				FileUtil.removeFile(board.getEvent_image());
			}

		return "redirect:/event/eventList"; //return "이동할페이지명"; //defintion name과 동일
	}
	
}