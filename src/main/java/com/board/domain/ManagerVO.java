package com.board.domain;

import java.util.Date;

public class ManagerVO {
	// 관리자
	private String manager_id,passwd2,writer2;
	
	// 관리자
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
		System.out.println("manager_id 출력");
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
		System.out.println("passwd2 출력");
	}
	public String getWriter2() {
		System.out.println("관리자 getWriter2()호출됨 =>"+writer2);
		return writer2;
	}
	public void setWriter2(String writer2) {
		this.writer2 = writer2;
		System.out.println("setWriter2()호출됨 ");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ManagerVO[manager_id="+manager_id+
				   ",passwd2="+passwd2+",writer2="+writer2+
				   "]";
	}
} 
	

