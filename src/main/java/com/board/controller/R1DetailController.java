package com.board.controller;

import java.io.File;
import java.util.HashMap;
import org.json.simple.JSONObject;//JSON객체를 만드는데 사용

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.R1BoardDao;
import com.board.domain.R1BoardVO;
import com.board.util.FileUtil;
import com.board.util.StringUtil;

@Component
@Controller
public class R1DetailController {
	
	private Logger log=Logger.getLogger(this.getClass());//로그객체 생성구문
	
	@Autowired
	private R1BoardDao boardDao;//byType을 이용해서 BoardDao객체를 자동으로 의존주입
	
	//String->페이지만 이동, 페이지가 이동하면서 데이터도 함께 전달(ModelAndView)
	//board/detail.do?seq=4->boardView.jsp
	@RequestMapping("/board/r1BoardDetail.do")
	
	public ModelAndView process(@RequestParam("board_num") int board_num) { 
	/*
	public ModelAndView process(@RequestParam("board_num") int board_num) {*/
		//@RequestParam("seq") int seq->어노테이션
		//int seq=Integer.parseInt(request.getParameter("seq"));->자바코드
		//?,?
		if(log.isDebugEnabled()) { //로그객체가 작동중이라면(디버그상태)
			log.debug("board_num=>"+board_num);//System.out.println("seq=>"+seq);
		}
		//1.조회수 증가
		boardDao.updateHit(board_num);
		R1BoardVO board=boardDao.selectBoard(board_num);
		//글내용에 \r\n  aaaa \r\n->메서드가 있다.<pre></pre>
		board.setCook2(StringUtil.parseBr(board.getCook2()));//지금은 사용X
		
		ModelAndView mav=new ModelAndView("r1BoardDetail");
		mav.addObject("board",board);//${board}
		//추가
		//mav.addObject("manager_id",manager_id);
		//mav.addObject("manager_id",manager_id);
		return mav; //1.이동할페이지명 2.전달할키명 3.전달할값
	}
	
	/*
	 //추가->글상세보기와 연관(다운로드)
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("main_img") String main_img) {
		//다운로드 받을 파일의 위치와 이름
		File downloadFile=new File(FileUtil.UPLOAD_PATH+"/"+main_img);
		//2. 스프링에서 다운로드 받는 뷰를 따로 작성->AbstractView를 상속받은 뷰클래스에게 전달
		//1.다운로드받을 뷰객체  2.모델객체명(키),3.전달할값(다운로드받을 파일명)
		//1) 이동할 페이지 X (jsp 페이지 X)
		return new ModelAndView("downloadView","downloadFile",downloadFile);
	}
	
	  // 좋아요
	@RequestMapping("/board/clickHeart.do")
	@ResponseBody
	public int transferGood(@RequestParam(value="board_num",defaultValue="5") int bo_num) {
		System.out.println("/board/clickHeart.do의 중에서 bo_num=>"+bo_num);
		boardDao.updateHeart(bo_num);
		int goodsu=boardDao.backgood(bo_num);
		return goodsu;
	}
	*/
	//public String transferGood~
		/*
		@RequestMapping("/board/clickHeart.do")
		@ResponseBody
		public String transferGood(@RequestParam(value="board_num",defaultValue="5") int board_num,
				                                 @RequestParam(value="mem_id",defaultValue="") String mem_id,
				                                 @RequestParam(value="click",defaultValue="0") int click){ //추가
			System.out.println("/board/clickHeart.do의 중에서 board_num=>"+board_num+",mem_id=>"+mem_id+",click=>"+click);
			//추가
			JSONObject obj = new JSONObject();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("board_num", board_num);
			hashmap.put("mem_id", mem_id);
			//추가 주석
			//hashmap.put("click", click);
			
			int result = boardDao.checkLike(hashmap);//좋아요할 갯수=>1 (안눌른경우)
			System.out.println("result의 값 확인=>"+result);//1*/
			
			/*//주석
			HashMap<String, Object> hashmap2 = new HashMap<String, Object>();
			hashmap2.put("board_num", board_num);
			//hashmap2.put("click", click); */
			/*
			if(result == 1) { //빈하트일 때 누름 -> 하트로 변경
				//mainService.downLike(boardNo);
				boardDao.updateHeart(board_num);
				boardDao.check_ok(hashmap);//0->1
			} else { //하트일 때 누름 -> 빈하트로 변경(1->0)
				boardDao.deleteHeart(board_num);
				boardDao.check_cancel(hashmap);
			}
			*/
	
			/*//주석
			if(result == 1) { //하트일 때 누름 -> 빈하트로 변경
				//mainService.downLike(boardNo);
				boardDao.deleteHeart(bo_num);
				result = 0;
			} else { //빈하트일 때 누름 -> 하트로 변경
				//mainService.upLike(boardNo);
				boardDao.updateHeart(bo_num);
				result = 1;
			}
			*/
	/*
			 int clickCheck=boardDao.getCheck(hashmap);//현재 클릭의 상태
			//하트수 값 구해오기
			 String goodsu=String.valueOf(boardDao.backgood(board_num));
		     System.out.println("goodsu=>"+goodsu);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			*/
			/*   //주석
			map.put("goodsu", goodsu);//좋아요 수
			map.put("result", result);
			map.put("clickCheck", clickCheck);
			*/
			/*
			  obj.put("goodsu", goodsu);
			  obj.put("result", result);
			  obj.put("clickCheck", clickCheck);
			 

			System.out.println(map);

			return obj.toJSONString();//return obj.toJSONString();
		}*/
	}
