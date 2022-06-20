package com.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.MemberService;
import com.board.domain.ManagerVO;
import com.board.domain.MemberDetailVO;
import com.board.domain.MemberVO;

@Component
@Controller
	public class MemberController {
	
		//로그객체 생성문
	private Logger log=Logger.getLogger(this.getClass());//현재클래스명을 불러와서 지정
	
	@Autowired
	private MemberService memberService;
	
	//추가
	
	// 회원가입 get
		@RequestMapping(value = "/board/register.do", method = RequestMethod.GET)
		public String getRegister() throws Exception {
			log.info("get register");
			return "register";
		}
		
		//회원정보 넘기기
		/*
		@RequestMapping(value="/board/register2.do", method = RequestMethod.GET)
		public String getRegister2() throws Exception{
			log.info("get register2");
			return "register2";
		}
		*/
		
		// 아이디	중복 확인
		@RequestMapping(value = "/board/memberIdChk.do", method = RequestMethod.POST)
		@ResponseBody
		public String memberIdChk(String mem_id) throws Exception{			
			int result = memberService.idCheck(mem_id);
			
			log.info("결과값 = " + result);
			
			if(result == 1) {				
				return "fail";	// 중복 아이디가 존재				
			} else {
				return "success";	// 중복 아이디 x				
			}				
		}
		
		// 닉네임	중복 확인
				@RequestMapping(value = "/board/writerChk.do", method = RequestMethod.POST)
				@ResponseBody
				public String writerChk(@RequestParam("writer") String writer) throws Exception{			

					int result = memberService.writerCheck(writer);
					
					log.info("결과값 = " + result);
					
					if(result == 1) {				
						return "fail";	// 중복 닉네임 존재				
					} else {
						return "success";	// 중복 닉네임 x				
					}					
				}
		
		// 회원가입 post(1:1 관계설정)=>Member(가입,수정,탈퇴)->MemberDetail(로그인 전용)(탈퇴)
		/*
		@RequestMapping(value = "/board/register.do", method = RequestMethod.POST)
		public String postRegister(MemberVO vo) throws Exception {
			log.info("post register");
			
			memberService.register(vo);		
			return "/login";
		}
		*/
				// 회원가입2
				 @RequestMapping(value = "/board/register.do", method = RequestMethod.POST)
				  public String postRegister(MemberVO vo) throws Exception {
						 log.info("post register");
						 memberService.register(vo);
						 ////Member->MemberDetail에 저장
						 MemberDetailVO mdv=new MemberDetailVO();
						 mdv.setMem_id(vo.getMem_id());
						 mdv.setWriter(vo.getWriter());
						 mdv.setUser_type(vo.getUser_type());
						 mdv.setPasswd(vo.getPasswd());
						 mdv.setGrade(vo.getGrade());//기본적인 회원등급을 'B'라고 정해놨음
						 memberService.registerDetail(mdv);
						 //------------------------------------------------
						 return "/login";  //회원가입에 성공하면 /login으로 요청이 된다.
				  }
		
		// 로그인 get
		@RequestMapping(value = "/board/login.do", method = RequestMethod.GET)
			public String getLogin() throws Exception {
				log.info("get login");
				return "login";
			}		
	
		// 로그인 post
		/*
		@RequestMapping(value = "/board/login.do", method = RequestMethod.POST)
		public String login(MemberVO vo, Model model ,HttpServletRequest req) throws Exception{
			log.info("post login");
		*/	
		@RequestMapping(value = "/board/login.do", method = RequestMethod.POST)
		public String login(MemberDetailVO vo, Model model ,HttpServletRequest req) throws Exception{
			
			log.info("post login");
			HttpSession session = req.getSession();//세션처리
			//--------------------------------------------------------------
			MemberDetailVO login = memberService.loginDetail(vo);	
			//---------------------------------------------------------------
			//추가 닉네임 저장
			String writer=memberService.getNickName(login.getMem_id());
			System.out.println("writer=>"+writer);//회원닉네임

			if(login == null) { // 틀리면
				session.setAttribute("member", null);
				model.addAttribute("msg", false);
				log.info("login == null");
				return "login";
	
			}else {
				//id와 nickname 저장
				session.setAttribute("member",login);
				//추가
				//mav.addObject("mem_id",login.getMem_id())
				model.addAttribute("mem_id",login.getMem_id());//회원id
				model.addAttribute("writer",writer);//닉네임
				
				log.info("login != null");
				System.out.println("MemberController의 login.getMem_id()=>"+login.getMem_id());
			}		
			return  "redirect:/board/MainLogin.do";
		}
		// 로그아웃
		@RequestMapping(value = "/board/logout.do", method = RequestMethod.GET)
		public String logout(HttpSession session) throws Exception{		
			session.invalidate();		
			return "redirect:/";
		}
	 
		// 비밀번호 찾기 페이지로 이동
		@RequestMapping(value="/board/FindPassword.do",method = RequestMethod.GET)
		public String findPasswordView() throws Exception{
			log.info("get FindPassword");
			return "FindPassword";
		}
		
	    // 비밀번호 찾기 실행
		@RequestMapping(value="/board/find_password.do", method=RequestMethod.POST)
		public String findPasswordAction(MemberVO vo, Model model) {
			MemberVO user = memberService.findPassword(vo);
			
			log.info("post find_password");
			
			if(user == null) { 
				model.addAttribute("check", 1);
			} else { 
				model.addAttribute("check", 0);
				model.addAttribute("passwd", user.getPasswd());
			}
			
			return "FindPassword";
		}
	
		
		// 관리자 로그인 get
		@RequestMapping(value = "/board/manager.do", method = RequestMethod.GET)
		public String getLogin2() throws Exception {
			log.info("get login2");
			return "/manager";
		}		

		// 관리자 로그인 post
		@RequestMapping(value = "/board/manager.do", method = RequestMethod.POST)
		public String login2(ManagerVO vo, Model model ,HttpServletRequest req) throws Exception{
			log.info("post login2");
			
			HttpSession session = req.getSession();//세션처리
			ManagerVO login2 = memberService.login2(vo);	//에러유발	
			System.out.println("/board/manager.do요청시 login2->"+login2);
            //추가
			String manager_id="";
			String writer2="";
			
			if(login2 == null) { // 틀리면
				session.setAttribute("member2", null);
				model.addAttribute("msg2", false);
				log.info("login2 == null");
				return "manager";
			}else {
				session.setAttribute("member2",login2);
				//추가 닉네임 저장
				manager_id=login2.getManager_id();
				writer2=memberService.getManagerName(manager_id);
				
				System.out.println("/board/Main2.do의 manager_id=>"+manager_id);
			    System.out.println("MemberController의 login2.getManager_id=>"+login2.getManager_id());
			    System.out.println("writer2=>"+writer2);
			    
			   model.addAttribute("manager_id",manager_id);
			   model.addAttribute("writer2",writer2);
				//log.info("login2 != null");
			}		
			//return  "redirect:/"; //manager.jsp로 이동
			//return  "redirect:/Main2.do?manager_id="+manager_id+"&writer2="+writer2;
			return  "redirect:/board/Main2.do";
			}
		//회원수정을 할 데이터 불러오기
		@RequestMapping(value = "/board/findMemberView.do")
		public ModelAndView findMember(@RequestParam("mem_id") String mem_id) throws Exception {
			log.info("findMember() 호출됨=>"+mem_id);
			MemberVO member=memberService.updateMemberView(mem_id);
		    System.out.println("수정하기위해서 찾은 member=>"+member);
			ModelAndView mav= new ModelAndView("MyUpdate");
			mav.addObject("mem_id",mem_id);	
			mav.addObject("member",member);
			return mav;
		}
		/*
		@RequestMapping(value = "/board/updateMember.do")
		public String submit(@ModelAttribute("member") MemberVO vo,
										@RequestParam("mem_id")String mem_id) throws Exception {//vo대신에 연결해서 사용될 객체명("member")
			log.info("submit()호출됨 ");
			memberService.updateMember(vo);
			return "redirect:/board/findMemberView.do?mem_id="+vo.getMem_id(); 
		}
		*/
		//회원탈퇴하는 경우
		//@ModelAttribute("member")
		@RequestMapping(value = "/board/deleteDetail.do") 
		public ModelAndView deleteMember(@RequestParam("mem_id") String mem_id) throws Exception {
			//디테일먼저 삭제학고 삭제
			//memberService.deleteMemberDetail(mem_id);
			//memberService.deleteMember(mem_id);
			ModelAndView mav=new ModelAndView("MyDelete");
			mav.addObject("mem_id",mem_id); //${mem_id}
			return mav;
		}
		
		//회원탈퇴하는 경우
		@RequestMapping(value = "/board/deleteDetailPro.do" ) 
		public String deleteMember(@RequestParam("mem_id") String mem_id,
				                                  @RequestParam("passwd") String passwd,
				                                   HttpServletRequest req) throws Exception {
		
			//log.info("deleteMember()호출됨 ");
			//memberService.deleteMember(mem_id);
			System.out.println("deleteMember의 mem_id=>"+mem_id+",passwd=>"+passwd);
			String pw=memberService.getPw(mem_id);
			//암호가 같으면 삭제
			if(pw.equals(passwd)) {
			   //memberService.deleteMemberDetail(mem_id);//MemberDetail
			   memberService.deleteMember(mem_id);//(=memberDetail이다)자동으로 member테이블을 삭제시킨다.
			   HttpSession memSession=req.getSession();
			   memSession.removeAttribute("member");//세션해제
			   //세션해제 구문
			}else if(!(pw.equals(passwd))){
				//memberService..addFlashAttribute("msg", false);
				System.out.println("암호가 틀립니다.");//jQuery에서 처리
			}
			return "redirect:/";
		}
	}			
		
		
		
		

		




	

