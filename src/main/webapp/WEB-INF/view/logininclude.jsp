<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import="com.board.domain.*,com.board.dao.*" %>
<%
		// 메인 페이지로 이동했을 때 세션에 값이 담겨있는지 체크
		String manager_id = null;
		if(session.getAttribute("member2") != null){
			ManagerVO manager= (ManagerVO)session.getAttribute("member2");
			System.out.println("manager=>"+manager);
			manager_id=manager.getManager_id();
			System.out.println("manager_id=>"+manager_id);
			String writer2=manager.getWriter2();
			System.out.println("writer2=>"+writer2);
		}
		String mem_id = null;
		if(session.getAttribute("member") != null){
		   // MemberVO member= (MemberVO)session.getAttribute("member");
		    MemberDetailVO member= (MemberDetailVO)session.getAttribute("member");
		   //------------------------------------------------------------------------
		    mem_id=member.getMem_id();
		    System.out.println("mem_id=>"+mem_id);
		    String writer=member.getWriter();
			System.out.println("writer=>"+writer);
		}
%>