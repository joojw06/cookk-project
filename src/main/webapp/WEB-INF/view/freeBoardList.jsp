<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import="com.board.domain.*,com.board.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="./logininclude.jsp" />

<!DOCTYPE html >
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   <title>freeBoard</title>
   <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel='stylesheet' href='../css/style.css' />
    <link rel='stylesheet' href='../css/fb2_style.css' />
</head>
<body>
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
   
 <div id="header">
        <jsp:include page="Header.jsp" />
    </div>
    
	<div class="container con_wrap">
		<div class="con_box">
			<div class="con_title">
				<div>
					<img src="../images/freeboard.png"/>
				</div>
				<h1><b>자유게시판</b></h1>
			</div>
			<span class="btn_write" style="display:none">	
			     <input type="button" onclick="location.href='freeBoardWrite.do?mem_id=${mem_id}'" value="글쓰기">
			</span>
			<div class="view_tab">
				<select>
					<option>조회순</option>
					<option>오래된순</option>
					<option>최신순</option>
				</select>
			</div>
			
			
		<div class="table-responsive con_table">
			<table class="table table-hover" width="100%" 
			cellpadding="0" cellspacing="0" > 
				<thead>
			    	<tr height="30"> 
				      <td width="50">번호</td> 
				      <td>제   목</td> 
				      <td width="70">작성자</td>
				      <td width="100">작성일</td> 
				      <td width="70">조 회</td> 
			  	  </tr>
			    </thead>
			    <!-- 레코드가 없다면 -->
			<c:if test="${count==0}">
				<tr>
					<td colspan="5" align="center">
						등록된 게시물이 없습니다.		
					</td>
				</tr>
			</c:if>
		<c:forEach var="article" items="${list}">	
				    <tr>
				    	<td>${article.board_num}</td>
				    	<td><a href="freeBoardDetail.do?board_num=${article.board_num}">[${article.f_category}]${article.f_title}</a></td>
				    	<td>${writer}</td>
				    	<td>${article.postdate}</td>
				    	<td>${article.f_view}</td>
				    </tr>
			</c:forEach>   
			
			</table>
			</div> <!-- table-responsive con_table -->
			
			<div class="paging">${pagingHtml}
				<!--<ul class="pagination pagination-xs">
				 	 <li><a href="#"> << </a></li>
				 	<li class="active"><a href="#">1</a></li>
				 	<li><a href="#">2</a></li>
				 	<li><a href="#">3</a></li>
				 	<li><a href="#">4</a></li>
				 	<li><a href="#">5</a></li>
				 	<li><a href="#">6</a></li>
				 	<li><a href="#">7</a></li>
				 	<li><a href="#">8</a></li>
				 	<li><a href="#">9</a></li>
				 	<li><a href="#">10</a></li>
				 	<li><a href="#"> >> </a></li> 
				 </ul>-->
			</div>
			
		</div> <!-- conbox -->
	</div> <!-- container -->

	
	<div id="footer">
       <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>