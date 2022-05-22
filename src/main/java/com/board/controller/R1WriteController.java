package com.board.controller;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;//로그를 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.R1BoardDao;
import com.board.domain.ManagerVO;
import com.board.domain.R1BoardVO;
import com.board.util.FileUtil;

@Component
@Controller
public class R1WriteController {

	//로그객체 생성문
	//private Logger log=Logger.getLogger(ListController.class);//로그를 처리할 클래스명
	private Logger log=Logger.getLogger(this.getClass());//현재클래스명을 불러와서 지정
	
	@Autowired
	private R1BoardDao boardDao;//자동적으로 Setter Method호출X(의존성객체 넣어줌)
	//추가
	
	//@Autowired
	//private ManagerVO manager;
	
	/*
	 * 하나의 요청명령어=>하나의 컨트롤러만 사용X
	 * 하나의 컨트롤러=>여러개의 요청명령어를 등록해서 사용이 가능(ex writeForm.do,write.do)
	 * 같은 요청명령어를 GET or POST으로 전송할지를 결정하는 속성
	 * 			write.do(페이지 이동)							write.do
	 * method=RequestMethod.GET			method=RequestMethod.POST	
	 */
	//1. 글쓰기 폼으로 이동(GET방식)
	@RequestMapping(value="/board/r1BoardWrite.do",method=RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="manager_id",defaultValue="imsi") String manager_id,
                                           @RequestParam(value="writer2",defaultValue="kktest") String writer2) {//메서드명은 임의로 작성
		 System.out.println("다시 처음부터 값을 입력을 받기 위해서(초기화) form()호출됨.");
		 //return "r1BoardWrite";//return "이동할페이지명"//definition name과 동일
		 ModelAndView mav=new ModelAndView("r1BoardWrite");
		 mav.addObject("manager_id",manager_id);
		 mav.addObject("writer2",writer2);
		 
		 return mav;
	}
	
	/*
	 * @RequestMapping(value="/board/r1BoardWrite.do",method=RequestMethod.GET)
	 * public String form() {//메서드명은 임의로 작성
	 * System.out.println("다시 처음부터 값을 입력을 받기 위해서(초기화) form()호출됨."); return
	 * "r1BoardWrite";//return "이동할페이지명"//definition name과 동일 }
	 */
	//2. 에러메시지출력->다시 초기화가 가능하게 설정->@ModelAttribute("커맨드객체 별칭")
	@ModelAttribute("vo")
	public R1BoardVO forBacking() {//반환형(DTO형 or VO형)임의의 메서드명
		System.out.println("forBacking()호출됨");
		return new R1BoardVO();//return new UserVO
	}
	//3.입력해서 유효성검사->에러발생
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
	@RequestMapping(value="/board/r1BoardWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("vo") R1BoardVO com, //입력받은데이터만 자동으로 Setter Method호출
			                       @RequestParam(value="manager_id",defaultValue="imsi") String manager_id,
			                       @RequestParam(value="mem_id",defaultValue="nup") String mem_id,
			                       @RequestParam(value="writer2",defaultValue="mgtest") String writer2){ //hidden으로 넘겼기때문
	
			if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
				System.out.println("/board/r1BoardWrite.do 요청중(post)");//?을 출력X
				log.debug("/board/r1BoardWrite.do의 R1BoardVO"+com);//?를 출력가능(select~where num=?)
				log.debug("/board/r1BoardWrite.do의 mem_id=>"+mem_id);
				log.debug("/board/r1BoardWrite.do의 writer2=>"+writer2);
				log.debug("/board/r1BoardWrite.do의 manager_id=>"+manager_id);
				//로그객체명.debug(출력대상자)
		  }
		//유효성검사
		//new BoardValidator().validate(com,result);
		//에러정보가 있다면
		/* jQuery로 검사하기
		 if(result.hasErrors()) {
			return form();//"boardWrite"->"boardWrite.jsp"로 이동하라
		} */
		//글쓰기 및 업로드=>입출력=>예외처리
		try {
			String newName="";//업로드한 파일이 변경된 파일명을 저장
			//업로드 되어 있다면
			if(!com.getUpload().isEmpty()) {
				//탐색기에서 선택한 파일->getOriginalFileName() aaaa.txt->123456
				newName=FileUtil.rename(com.getUpload().getOriginalFilename());
				System.out.println("newName=>"+newName);
				//DTO에 변경=>테이블에서도 변경저장
				com.setMain_img(newName);//
			}
			//최대값 +1
			int newSeq=boardDao.getNewSeq()+1;
			System.out.println("newSeq=>"+newSeq);
			//게시물번호=>계산->저장
			com.setBoard_num(newSeq);//1->2
			//추가
			com.setManager_id(manager_id);
			com.setMem_id(mem_id);//관리자 id의 대신에 관리자 회원 id값을 저장
			com.setWriter2(writer2);//닉네임
			////추가////////////////////////////////////////////////////
			/*
			ManagerVO manager=new ManagerVO();
			manager.setWriter2(writer2);//관리자 테이블에서 별칭부여
			System.out.println("/board/r1BoardWrite.do의 manager.getWriter2()=>"+manager.getWriter2()); */
			//----------------------------------------------------------------------
			boardDao.insert(com);//DB상에서 반영(<insert>~</insert>)
			
			//실제로 upload폴더로 업로드한 파일을 전송(복사)
			if(!com.getUpload().isEmpty()) {
				File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				//물리적으로 데이터전송(파일전송)
				com.getUpload().transferTo(file);//파일업로드 위치로 전송
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		//return "redirect:/요청명령어";=>retrun "이동할 페이지";
		//return "redirect:/board/r1BoardList.do";
		return "redirect:/board/r1BoardList.do?manager_id="+manager_id+"&writer2="+writer2;
	}
}