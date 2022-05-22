<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.board.domain.*,com.board.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<jsp:include page="./logininclude.jsp" />

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>관리자레시피 게시판</title>
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link rel='stylesheet' href='../css/style.css' />
  <link rel='stylesheet' href='../css/r1_style.css' />
</head>
<body>
    
 <div id="header">
   <jsp:include page="Header.jsp" />
 </div>
    
    <!-- 게시글 상세보기 영역 시작 -->
	<div class="container" style="padding-bottom:0">
	<div class="row vertical-center-row">
		<div class="col-lg-7 col-lg-offset-3">
		<h1 class="r1_tit"><b>인기 레시피</b></h1>
			<div class="conbox">
				<span class="r1_category">카테고리 > ${board.category}</span>
				<div class="con_title_t">
					<div>
						<h3 id="num">${board.board_num}</h3>
					</div>
					<div>
						<h3><b>${board.title}</b></h3>
					</div>
				</div><!-- con_title_t -->
				<p>
				<div class="r1_con">
				<div class="main_image" style="margin:30px 0 18px;">
					<img src="../upload/${board.main_img}" style=" width:450px; height:300px;" onerror="this.style.display='none'" />
					<div id="click" style="display:none">${board.click}</div>
				</div>
				</div>
				
				<p>
				<div class="rp_title">
					<div class="second">
						<h3><b>${board.writer2}</b></h3>
					</div>
					<!-- 좋아요 -->
					<div class="third">
					<h4 id="login" style="display:none">${mem_id}</h4>
					<script>
                     //좋아요 하트 출력
                     $(function() {
                     $("#like-img2").show();//빈하트
                           $("#like-img2").on("click", function() {//빈하트 클릭시
                        	   var board_num =  parseInt($("#num").text()); 
                               var mem_id=$('#login').text();
                               var click= parseInt($('#click').text());
                        	   console.log('board_num=>',board_num,'mem_id=>',mem_id,'click=>',click)//5 click추가
                        	   
                        	   $.ajax({
                                   url : "clickHeart.do",
                                   type : "post",
                                   //data : {board_num:board_num,clickyes:click}, /* {key:value}*/
                                   data : {board_num:board_num,mem_id:mem_id,click:click},
                                   dataType: "json", //json표기법
                                   success : function (data) {     
                                	 console.log("success함수호출(data)=>"+data)
                                     if(data.result == 1) {//채워진 하트로 변경
										$("#like-img2").attr("src", "../images/heart1.png");
									}else if(data.result == 0) {//빈하트로 변경
										$("#like-img2").attr("src", "../images/heart0.png");
									}
									//$("#likeCnt").html(data.likeCnt);
								    console.log('data.goodsu=>',data.goodsu)
									$('#heart').text(data.goodsu);
								    //<div id="click">${board.click}</div>
								    
								    $('#click').text(data.clickCheck);
									/*
									console.log('data.checkLike->'+data.checkLike)
									$('#click').html(data.checkLike);
									*/
                                   }
                               });
                           //$(this).attr("src", "../images/heart1.png");//꽉찬하트로 이미지변경
                            });                
                        });
                     </script>
                     <h4>좋아요</h4>
                     <h4 id="heart">${board.heart}</h4>
                     <!-- <img id="like-img1" style="width: 20px; height: 20px; cursor: pointer;" alt="" src="../images/heart1.png" />--> 
                     <img id="like-img2" style="width: 20px; height: 20px; cursor: pointer;" alt="" src="../images/heart0.png" />
					</div>
					<div class="fourth">
						<h4>등록일</h4>
						${board.postdate}
					</div>
					<h4>조회수</h4>
						${board.re_view}
					</div><!-- rp_title -->
					<p>
					<span class="t_box">
						<b>재료</b>
						${board.cook1}
					</span>
					</p>
					<p>
					<span class="h_box">
						<b>만드는 방법</b>
						${board.cook2}
					</span>
					<p>
					<!-- 글쓰기 버튼 생성 -->
					<p>
					<span class="btn_write">
						<%-- 원본
						<input type="button" value="수정" 
						onclick="location.href='r1BoardUpdate.do?board_num=${board.board_num}'" /> 
						--%>
						<input type="button" value="수정" 
						onclick="location.href='r1BoardUpdate.do?board_num=${board.board_num}&manager_id=${manager_id}&mem_id=${mem_id}&writer2=${writer2}'" />
						<input type="button" class="pull-right" onclick="location.href='r1BoardList.do?manager_id=${manager_id}&mem_id=${mem_id}&writer2=${writer2}'" value="목록으로" />
					</span>
					</p>
				</div>	<!-- conbox -->
			</div><!-- grid -->
		</div> <!--Row-->
	</div> <!-- container -->
	<div id="footer">
       <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>