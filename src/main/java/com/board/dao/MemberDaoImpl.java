package com.board.dao;

import org.apache.catalina.Manager;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.board.domain.ManagerVO;
import com.board.domain.MemberDetailVO;
import com.board.domain.MemberVO;

@Component
@Service("MemberDaoImpl")
 public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao{

	 // 1. 가입
	public void register(MemberVO vo) {
		getSqlSession().insert("register",vo);	
	}
	
	//1-1. 가입2
	public void registerDetail(MemberDetailVO vo) {
			// TODO Auto-generated method stub
			getSqlSession().insert("registerDetail",vo);
	}
	// 아이디 중복 확인
	public int idCheck(String mem_id) {
		return (Integer)getSqlSession().selectOne("idCheck", mem_id);
	}
	
	//추가(닉네임 찾기)
	public String getNickName(String mem_id) {
		// TODO Auto-generated method stub
		return (String)getSqlSession().selectOne("getNickName",mem_id);
	}
	//추가(관리자 닉네임 찾기)
	public String getManagerName(String manager_id) {
		// TODO Auto-generated method stub
		return (String)getSqlSession().selectOne("getManagerName",manager_id);
	}
	
	// 닉네임 중복 확인
	public int writerCheck(String writer) {
		return (Integer)getSqlSession().selectOne("writerCheck", writer);
	}
	
	// 2. 로그인
	public MemberVO login(MemberVO vo) {		
		return (MemberVO)getSqlSession().selectOne("login",vo);
	}
	
	//2-2) 로그인2(MemberDetail)
	public MemberDetailVO loginDetail(MemberDetailVO vo) {
			// TODO Auto-generated method stub
		return (MemberDetailVO)getSqlSession().selectOne("loginDetail",vo);
	}
	
	// 3. 암호찾기
	public MemberVO findPassword(MemberVO vo) {
		return (MemberVO)getSqlSession().selectOne("findPassword", vo);
	}

	// 4. 관리자 로그인
	public ManagerVO login2(ManagerVO vo) {
		// TODO Auto-generated method stub
		return (ManagerVO)getSqlSession().selectOne("login2",vo);
	}
	//회원가입 정보를 회원상세에 넘기기
	public void register2(MemberDetailVO vo) {
		// TODO Auto-generated method stub
		getSqlSession().insert("register2",vo);
	}
	
	//추가(회원수정할 데이터 찾기)
	//@Override
	public MemberVO updateMemberView(String mem_id) {
		// TODO Auto-generated method stub
		return (MemberVO)getSqlSession().selectOne("findMember",mem_id);
	}
	
	//추가2(회원수정할 메서드)
	//@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateMember",vo);
	}
	
	//추가3(회원탈퇴 디테일) 
	//@Override
	public void deleteMemberDetail(String mem_id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteDetail",mem_id);
	}
	//추가4(회원탈퇴)
	//@Override
	public void deleteMember(String mem_id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteMember",mem_id);
	}
	
	//추가5(회원탈퇴 암호)
	//@Override
	public String getPw(String mem_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("searchPw",mem_id);
	}
}
