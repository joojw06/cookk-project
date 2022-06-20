package com.board.domain;

import java.util.Date;

public class MemberDetailVO {

	private String mem_id,writer,passwd,user_type;//id,nickname,passwd
	private int grade;//등급
	
	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
		System.out.println("mem_id ()호출됨=>"+mem_id);
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
		System.out.println("writer()호출됨=>"+writer);
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
		System.out.println("passwd()호출됨=>"+passwd);
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
		System.out.println("grade()호출됨=>"+grade);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MemberDetailVO[mem_id="+mem_id+",writer="+writer
											+"passwd="+passwd+"grade="+grade+"]";
	}
	//추가
			//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
				private static String convert(String name) {
					if(name!=null){
				    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
				    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
				    	
				    	name=name.replaceAll("<","&lt");
				    	name=name.replaceAll(">","&gt");
				    	//추가 eval(" " or ' ')
				    	name=name.replaceAll("\\(","&#40");
				    	name=name.replaceAll("\\)","&#41");
				    	//"test"  'test'
				    	name=name.replaceAll("\"","&quot");
				    	name=name.replaceAll("\'","&apos");
				    }else{ //name==null
				    	return null; //입력을 하지 않았다면 더 이상 실행X
				    }
					return name;
				}	
}
