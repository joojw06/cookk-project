package com.board.dao;

import org.springframework.stereotype.Component;

import com.board.domain.ManagerVO;
import com.board.domain.MemberDetailVO;
import com.board.domain.MemberVO;

@Component
public interface MemberDao {
	
	// 회원가입
	public void register(MemberVO vo);
	
	//추가
	public void registerDetail(MemberDetailVO vo);
	
	//아이디 중복확인
	public int idCheck(String mem_id);
	
	//닉네임  찾기
	public String getNickName(String mem_id);
	
	//관리자 닉네임 찾기
	public String getManagerName(String manager_id);
	
	//닉네임 중복확인
	public int writerCheck(String writer);

	// 로그인
	public MemberVO login(MemberVO vo);

	//추가
	//로그인2(MemberDetail)
	public MemberDetailVO loginDetail(MemberDetailVO vo);
	///////////////////////////////////////////////////////////
		
	// 암호찾기
	public MemberVO findPassword(MemberVO vo);
	
	// 관리자 로그인
	public ManagerVO login2(ManagerVO vo);
	
	//회원가입 정보를 회원상세에 넘기기
	public void register2(MemberDetailVO vo);

	//추가(회원수정할 데이터 찾기)
	public MemberVO updateMemberView(String mem_id);//select * from member where mem_id=?
	
	//추가2(회원수정할 메서드)
	public void updateMember(MemberVO vo);//수정버튼을 눌렀을때 수정해주는 메서드 호출
	
	//추가3(회원탈퇴)
	public void deleteMember(String mem_id);
	
	//추가4(회원탈퇴 디테일)
	public void deleteMemberDetail(String mem_id);
	
	//추가5(회원탈퇴할 암호찾기)
	public String getPw(String mem_id);
}

