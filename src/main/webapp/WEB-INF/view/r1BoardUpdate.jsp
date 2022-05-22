<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import="com.board.domain.*,com.board.dao.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="./logininclude.jsp" />

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link rel='stylesheet' href='../css/style.css' />
  <link rel='stylesheet' href='../css/r1_style.css' />
  <title>r1BoardUpdate</title>
  <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
  <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script>
    function imsi(){
		if(confirm("삭제하시겠습니까?")){
			//location.href="r1BoardDelete.do?board_num="+${command.board_num}+"&manager_id="+${manager_id}
			location.href='r1BoardDelete.do?board_num='+${command.board_num}+'&manager_id='${manager_id}
		}
    }
</script>
</head>
<body>

<div id="header">
	<jsp:include page="Header.jsp" />
</div>

	<!-- 게시판 글쓰기 양식 영역 시작 -->
	<div class="container">
			<div class="con_title">
				<div>
					<img src="../images/recipe1_icon.png"/>
					<h1><b>레시피 수정하기</b></h1>
				</div>
			</div><!-- con_title -->
			<div class="row vertical-center-row">
				<div class="col-lg-6 col-lg-offset-3">
				<spring:hasBindErrors name="command" />
                <form:errors path="command" />
				<form id="form" method="post" action="r1BoardUpdate.do"  enctype="multipart/form-data" name="r1">
					<input type="hidden" name="board_num"  value="${command.board_num}" />
					<input type="hidden" name="mem_id"  value="${mem_id}" />
					<input type="hidden" name="manager_id"  value="${manager_id}" />
					<input type="hidden" name="writer2"  value="${writer2}"  />
					<!-- <input type="hidden" name="mem_id"  value="kim" />
					<input type="hidden" name="manager_id"  value="mg" />
					<input type="hidden" name="writer"  value="manager" /> -->
					<!-- <input type="text" name="heart"  value="1" readonly="readonly" /> -->
					<div class="form-group">
						<input type="text" class="form-control" placeholder="제목(요리이름)을 입력해주세요." 
								name="title" id="title" value="${command.title} "/>
					</div>
						<div class="form-group">
							<select name="category" class="form-control" id="category">
								<option value="">카테고리선택</option>
			 					<option value="메인요리">메인요리</option>
			 					<option value="밑반찬">밑반찬</option>
			 					<option value="간단요리">간단요리</option>
			 					<option value="안주">안주</option>
			 					<option value="베이킹">베이킹</option>
			 					<option value="다이어트">다이어트</option>
							</select>
							<script>
								document.r1.category.value="${command.category}"
							</script>
						</div>
						<div class="form-group">
				            <input type="file" class="form-control"  id="upload" name="upload">
				            <!--이미 업로드된 파일이 있는 경우에만 수행  -->
							<c:if test="${!empty command.main_img }">
											(${command.main_img }) 파일이 등록되어있습니다.<br>
							</c:if>
				        </div>
						<p>
							<textarea rows="5" class="form-control" name="cook1">${command.cook1}</textarea>
						</p>
						<p>
							<textarea rows="10" class="form-control" name="cook2">${command.cook2}</textarea>
						</p>
						<!-- 글쓰기 버튼 생성 -->
						<p style="text-align:center; margin-top:30px">
						<span class="btn_write" >	
					    	<input type="button" onclick="location.href='r1BoardList.do?&manager_id=${manager_id}&mem_id=${mem_id}&writer2=${writer2}'" value="목록으로" />
					    	<input type="button" value="삭제하기"  onclick="imsi()" />
							<input type="submit"  value="등록하기" />
							<!-- <input type="submit" id="form" value="등록하기" /> -->
						</span>
						</p>
					</form>
				</div>
			</div><!-- row -->
	</div><!-- container -->
	<!-- 게시판 글쓰기 양식 영역 끝 -->
	<div id="footer">
       <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>