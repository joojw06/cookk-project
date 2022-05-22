package com.board.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.FreeBoardDao;
import com.board.dao.FreeReplyDao;
import com.board.domain.FreeBoardVO;
import com.board.domain.FreeReplyVO;
import com.board.util.StringUtil;

@Component
@Controller
public class FreeDetailController2 {
	
	private Logger log=Logger.getLogger(this.getClass());//로그객체 생성구문
	
	@Autowired
	private FreeBoardDao boardDao;//byType을 이용해서 BoardDao객체를 자동으로 의존주입
	
	@Autowired
	private FreeReplyDao replyDao;
	
	//String->페이지만 이동, 페이지가 이동하면서 데이터도 함께 전달(ModelAndView)
	//board/detail.do?seq=4->boardView.jsp
	//글상세보기
	@RequestMapping("/board/freeBoardDetail2.do")
	public ModelAndView process(@RequestParam("board_num") int board_num,
												@RequestParam("mem_id") String mem_id, 
												@RequestParam("writer") String writer){

		if(log.isDebugEnabled()) { //로그객체가 작동중이라면(디버그상태)
			log.debug("board_num=>"+board_num);//System.out.println("seq=>"+seq);
			log.debug("mem_id=>"+mem_id);
			log.debug("writer=>"+writer);
		}
		//1.조회수 증가
		boardDao.updateHit(board_num);
		FreeBoardVO board=boardDao.selectBoard(board_num);
		//글내용에 \r\n  aaaa \r\n->메서드가 있다.<pre></pre>
		board.setF_content(StringUtil.parseBr(board.getF_content()));//지금은 사용X
		//추가
		List<FreeReplyVO> reply=replyDao.list(board_num);
		System.out.println("reply=>"+reply);
		//확장 for문
		for(FreeReplyVO free:reply) {
			System.out.println("free.getR_content()=>"+free.getR_content());
			System.out.println("free.getWriter()=>"+free.getWriter());
			System.out.println("free.getR_write_date()=>"+free.getR_write_date());
		}
		
		ModelAndView mav=new ModelAndView("freeBoardDetail2");
		mav.addObject("board",board);
		mav.addObject("reply",reply);
		//추가
		mav.addObject("board_num",board_num);
		//추가2
		mav.addObject("mem_id",mem_id);
		mav.addObject("writer",writer);
		//1.이동할페이지명 2.전달할키명 3.전달할값
		return mav;
	}
	
	//댓글등록
	@RequestMapping(value="/board/freeReply.do")
	public String submit(@ModelAttribute("FreeReplyVO")  FreeReplyVO fvo,
			                      @RequestParam(value="r_content" ,defaultValue="세번째요청중") String r_content,
			                      @RequestParam("mem_id") String mem_id, 
								  @RequestParam("writer") String writer){
		if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			System.out.println("/board/freeReply.do 요청중");//?을 출력X
			log.debug("FreeReplyVO"+fvo);//?를 출력가능(select~where num=?)
			log.debug("r_content=>"+r_content);
			log.debug("mem_id=>"+mem_id);
			log.debug("writer=>"+writer);
			//로그객체명.debug(출력대상자)
		}
		//최대값 +1
		int newRnum=replyDao.getNewRnum()+1;
		System.out.println("newRnum=>"+newRnum);
		
		fvo.setBoard_num(fvo.getBoard_num());
		fvo.setMem_id(fvo.getMem_id());
		fvo.setWriter(fvo.getWriter());
		fvo.setR_content(fvo.getR_content());//fvo.setR_content(fvo.getR_content());
		//게시물번호=>계산->저장
		fvo.setR_num(newRnum);
		replyDao.insert(fvo);
		
		return "redirect:/board/freeBoardDetail2.do?board_num="+fvo.getBoard_num();
	}
	
	//댓글삭제
	@RequestMapping(value="/board/delete.do")
	/*
	public String delete(@ModelAttribute("FreeReplyVO")  FreeReplyVO fvo,
            @RequestParam("r_num") int r_num){ */
	
	public String delete(@RequestParam("board_num") int board_num,
                                  @RequestParam("r_num") int r_num,
									@RequestParam("mem_id") String mem_id, 
									@RequestParam("writer") String writer){	
		
		if(log.isDebugEnabled()) {
			log.debug("r_num=>"+r_num);
			log.debug("board_num=>"+board_num);
			log.debug("mem_id=>"+mem_id);
			log.debug("writer=>"+writer);
		}
		replyDao.delete(r_num);
		return "redirect:/board/freeBoardDetail2.do?board_num="+board_num;
	}
	//추가->글상세보기와 연관(다운로드)
	/*
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("f_img") String f_img) {
		//다운로드 받을 파일의 위치와 이름
		File downloadFile=new File(FileUtil.UPLOAD_PATH+"/"+f_img);
		//2. 스프링에서 다운로드 받는 뷰를 따로 작성->AbstractView를 상속받은 뷰클래스에게 전달
		//1.다운로드받을 뷰객체  2.모델객체명(키),3.전달할값(다운로드받을 파일명)
		//1) 이동할 페이지 X (jsp 페이지 X)
		return new ModelAndView("downloadView","downloadFile",downloadFile);
	}
	*/
	
}
