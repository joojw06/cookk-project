<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.board.domain.*,com.board.dao.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
   <title>r1BoardWrite</title>
   <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
   <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>

<div id="header">
	<jsp:include page="Header.jsp" />
	<jsp:include page="logininclude.jsp" />
</div>
<!-- 유효성검사 -->
<script type="text/javascript">
	/* $(document).ready(function(){
		$("#form").submit(function(){
			var is = true;
			//유효성 검사
			$.each($("input, textarea, select"), function(){
				if($(this).val() ===null || $(this).val() ===' ' || $(this).val().length ===0){
					alert('모든 칸을 작성해주세요!(사진 첨부 포함)');
					return is =false;
				}
			})
			return is;
		});
	});  */
	function form(){
		if($("#title").val() == ""){
		    alert("제목을 적어주세요.");
		    $("#title").focus();
		    return false;
		}
		if($("select[name=category]").val() == ""){
		    alert("카테고리를 선택해주세요.");
		    $("#category").focus();
		    return false;
		}
		
		if($("#upload").val() == ""){
		    alert("사진을 첨부해주세요.");
		    $("#upload").focus();
		    return false;
		}
		
		if($("#cook1").val() == ""){
		    alert("재료를 적어주세요.");
		    $("#cook1").focus();
		    return false;
		}
		
		if($("#cook2").val() == ""){
		    alert("조리방법을 적어주세요.");
		    $("#cook2").focus();
		    return false;
		}
		else{
			$('#form').submit();
		}
	}
</script>
	<!-- 게시판 글쓰기 양식 영역 시작 -->
	<div class="container">
		<div class="con_title">
			<div>
			<img src="../images/recipe1_icon.png"/>
			<h1><b>레시피 작성하기</b></h1>
			</div>
		</div><!-- con_title -->
		<%-- <input type="hidden" name="mem_id"  value="${mem_id}" /> 관리자 회원 id로 대체 --%>
		<%-- <input type="hidden" name="heart"  value="${heart}"  /> 0이기 때문에--%>
		<%-- 	<!-- <input type="hidden" name="manager_id"  value="mg" /> --%>
					
		 <h1 style="display:none">manaer_id=${manager_id}</h1>
		 <h1 style="display:none">writer2=${writer2}</h1>
		 
		<div class="row vertical-center-row">
				<div class="col-lg-6 col-lg-offset-3">
				<spring:hasBindErrors name="vo" />
                <form:errors path="vo" />
				<form id="form" method="post" action="r1BoardWrite.do"  enctype="multipart/form-data" >
					<input type="hidden" name="mem_id"  value="nup" />
					<input type="hidden" name="manager_id"  value="${manager_id}" />
					<input type="hidden" name="writer2"  value="${writer2}"  />
					<div class="form-group">
						<input type="text" class="form-control" placeholder="제목(요리이름)을 입력해주세요." 
								name="title" id="title" />
					</div>
						<div class="form-group">
							<select name=category class="form-control" id="category"  value="${vo.category}" >
								<option value="">카테고리선택</option>
			 					<option value="메인요리">메인요리</option>
			 					<option value="밑반찬">밑반찬</option>
			 					<option value="간단요리">간단요리</option>
			 					<option value="안주">안주</option>
			 					<option value="베이킹">베이킹</option>
			 					<option value="다이어트">다이어트</option>
							</select>
						</div>
						<div class="form-group">
				            <input type="file" class="form-control"  id="upload" name="upload" accept="image/png, image/jpeg">
				        </div>
						<p>
							<textarea rows="5" class="form-control" id="cook1" name="cook1" placeholder="재료를 입력해주세요">${vo.cook1}</textarea>
						</p>
						<p>
							<textarea rows="10" class="form-control" id="cook2" name="cook2" placeholder="만드는 방법을 입력해주세요">${vo.cook2}</textarea>
						</p>
						<!-- 글쓰기 버튼 생성 -->
						<p>
						<span class="btn_write">	
					    	<input type="button" onclick="location.href='r1BoardList.do?manager_id=${manager_id}&mem_id=${mem_id}&writer2=${writer2}'" value="목록으로" />
							<!-- <input type="submit" class="pull-right" value="등록하기" /> -->
							<input type="submit" class="pull-right" value="등록하기" onclick="form();"/>
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