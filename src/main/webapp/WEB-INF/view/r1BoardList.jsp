<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.board.domain.*,com.board.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="./logininclude.jsp" />

<!DOCTYPE html >
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   <title>r1Board</title>
   <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel='stylesheet' href='../css/style.css' />
    <link rel='stylesheet' href='../css/r1_style.css?ver=1' />
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
					<img src="../images/recipe1_icon.png"/>
				</div>
				<h1><b>인기레시피</b></h1>
			</div>
			<!-- 인기레시피 이미지 -->
			 <ul>
                  <li>
                    <c:forEach var="review" items="${reviewList}">
                      <a href="r1BoardDetail.do?board_num=${review.board_num }">
                        <img src="../upload/${review.main_img}"/>
                        <p class=videoTit >
                      	[${review.category}] &nbsp;${review.title}
                        </p>
                      </a>
	                   <%--  review.board_num=><c:out value="${review.board_num}" />
	                    review.cook2=><c:out value="${review.cook2}" /> --%>
	                </c:forEach>
                  </li>
			</ul>
			<!-- 글쓰기 버튼 생성 -->
		<c:if test="${manager_id != null}">
			<span class="btn_write">	
			     <input type="button" 
			     onclick="location.href='r1BoardWrite.do?manager_id=${manager_id}&writer2=${writer2}'"  value="글쓰기">
			</span>
		</c:if>	
		<div class="table-responsive con_table">
			<table class="table table-hover" width="100%" 
			cellpadding="0" cellspacing="0" > 
				<thead>
			    	<tr height="30"> 
				      <td width="50">번호</td> 
				      <td width="200">제목</td> 
				      <td width="60">작성자</td>
				      <td width="70">작성일</td> 
				      <td width="20">조 회</td>  
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
				        <td><a href="r1BoardDetail.do?board_num=${article.board_num}">[${article.category}]${article.title}</a></td>
				    <td>${writer2}</td>
				    <td>${article.postdate}</td>
				    <td>${article.re_view}</td>
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
			<!-- 검색창 -->
			<div class="row vertical-center-row">
			<div class="text-center col-lg-7 col-lg-offset-3">
			<form id="searchForm" class="navbar-form" role="search" action="r1BoardList.do">
	     		<div class="form-group"><!--view_tab-->
	     		<select name="keyField">
	     			<option value="0">선택</option>
					<option value="title">제목</option>
					<option value="cook1">재료</option>
					<option value="category">카테고리</option>
				</select>
					<input type="text" name="keyWord" class="form-control" placeholder="레시피를 검색해보세요">
					<button class="btn btn-default">검색</button>
	     		</div><!-- form-group -->
	     	</form>
	     	</div>
		</div> 
		</div> <!-- conbox -->
	</div> <!-- container -->

	<div id="footer">
       <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>