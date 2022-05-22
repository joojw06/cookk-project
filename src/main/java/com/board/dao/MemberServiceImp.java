package com.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.board.domain.ManagerVO;
import com.board.domain.MemberDetailVO;
import com.board.domain.MemberVO;

@Component
@Service("MemberServiceImp")
public class MemberServiceImp implements MemberService { 
	
	@Autowired
	private MemberDao dao; 
	
	//1. 회원가입
	public void register(MemberVO vo) throws Exception {		
		dao.register(vo);	
	}
	//1-1 회원가입2
		
	public void registerDetail(MemberDetailVO vo) throws Exception {
			// TODO Auto-generated method stub
			dao.registerDetail(vo);
	}
	
	// 아이디 중복확인
	public int idCheck(String mem_id) throws Exception {		
		return dao.idCheck(mem_id);
	}
	
	//닉네임 찾기
	public String getNickName(String mem_id) {
		// TODO Auto-generated method stub
		return dao.getNickName(mem_id);
	}
	//관리자 닉네임 찾기
	public String getManagerName(String manager_id) {
		// TODO Auto-generated method stub
		return dao.getManagerName(manager_id);
	}
	
	// 닉네임 중복확인
	public int writerCheck(String writer) throws Exception {		
		return dao.writerCheck(writer);
	}
	
	// 2. 로그인
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//추가
	//@Override
	public MemberDetailVO loginDetail(MemberDetailVO vo) throws Exception {
			// TODO Auto-generated method stub
			return dao.loginDetail(vo);
	}
		
	// 암호찾기
	public MemberVO findPassword(MemberVO vo) {
		return dao.findPassword(vo);
	}
	
	// 3. 관리자 로그인
	public ManagerVO login2(ManagerVO vo) throws Exception {
		return dao.login2(vo);
	}
	
	//회원가입 정보를 회원상세에 넘기기
	public void register2(MemberDetailVO vo) {
		dao.register2(vo);
	}
	
	//추가(회원수정할 데이터 찾기)
	//@Override
	public MemberVO updateMemberView(String mem_id) {
		// TODO Auto-generated method stub
		return dao.updateMemberView(mem_id);
	}
	
	//추가2(회원수정할 메서드)
	//@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.updateMember(vo);
	}	
	
	//추가3(회원탈퇴 디테일)
	//@Override
	public void deleteMemberDetail(String mem_id) {
		// TODO Auto-generated method stub
		dao.deleteMemberDetail(mem_id);
	}
	
	//추가4(회원탈퇴)
	//@Override
	public void deleteMember(String mem_id) {
		// TODO Auto-generated method stub
		dao.deleteMember(mem_id);
	}
	//추가5(탈퇴 암호)
	//@Override
	public String getPw(String mem_id) {
		// TODO Auto-generated method stub
		return dao.getPw(mem_id);
	}
}


