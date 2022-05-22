<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>header</title>
    
	 <link rel='stylesheet' href='../css/normalize.css' />
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <link rel='stylesheet' href='../css/style.css' />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    <!-- 로그아웃 추가 -->
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			//추가
			if(confirm("정말로 로그아웃하시겠습니까?")){
			location.href="logout.do";
			}
		})
	})
	</script>
  </head>
  <body>  
  
    <div id="header">
     <div class="headerCon">
      <div class="headerTop">
       
       <c:if test="${manager_id == null}"> 
           <a href="../board/manager.do" style="float:left" >관리자</a>
       </c:if>
       
        <c:if test="${manager_id != null}">
              <a id="logoutBtn" onclick="location.href='logout.do'">${writer2} 로그아웃</a>         
        </c:if>
        <c:if test="${manager_id==null}">
        <ul class="headerMenu">
        	 <c:if test="${mem_id == null}"> 
	            <li><a href="../board/login.do">로그인</a></li> 
	             <li><a href="../board/register.do">회원가입</a></li>
	         </c:if>    
	         <c:if test="${mem_id != null }">
	         	<li style="width: 150; color:white">${writer}님 환영합니다.</li>
				<li id="logoutBtn" style="color:white">로그아웃</a></li>	
			        
	            <li><a href="../board/MyPage.do?mem_id=${mem_id}">마이페이지</a></li>
	           </c:if>
        	</ul>  
          </c:if>  
      </div>
    </div>
      
   <div class="m_headerCon">
    <ul class="m_topMenu">
          <li>
          	<a href="../board/Main.do" class="logoB" >
          		<img class="logo" src="../images/cookk_png.png" />
          	</a>
          </li> 
    </ul>  
    
</div><!-- m_headerCon: 메인로고 -->

<script>
    $(document).ready(function () {
        var sc = $(".gnbRight").offset();
        //console.log(sc);
        //console.log($(document).scrollTop());

        $(window).scroll(function () {
            //console.log($(document).scrollTop());
            if ($(document).scrollTop() > sc.top) {
                $(".gnbRight").addClass("menu_fixed");
            }
            else {
                $(".gnbRight").removeClass("menu_fixed");
            }
        });
    });

</script>

      <div class="gnbCon">
    <div class="gnbBox">
        <div class="gnbLeft">
        
        <c:if test="${manager_id== null && mem_id== null}">
          	<a href="../board/Main.do" class="logoB" >
          		<img class="logo" src="../images/cookk_png.png" />
          	</a>
         </c:if>
         <c:if test="${manager_id!= null}">
	         <a href="../board/Main.do?manager_id=${manager_id}&writer2=${writer2}" class="logoB" >
	          	<img class="logo" src="../images/cookk_png.png" />
	         </a>
	     </c:if>
	     <c:if test="${mem_id!= null}">
	         <a href="../board/Main.do?mem_id=${mem_id}&writer=${writer}" class="logoB" >
	          	<img class="logo" src="../images/cookk_png.png" />
	         </a>
	     </c:if>
        </div>
        
        <div class="gnbRight">
            <ul class="gnb">
            <c:if test="${manager_id!= null}">
                <li><a href="../board/r1BoardList.do?manager_id=${manager_id}&writer2=${writer2}">관리자작성</a></li>
	            <li><a href="../board/R2BoardList.do?manager_id=${manager_id}">회원작성</a></li>
	            <li><a href="../board/freeBoardList.do?manager_id=${manager_id}">자유게시판</a></li>
	            <li><a href="../board/eventList.do?manager_id=${manager_id}&writer2=${writer2}">이벤트</a></li>
	         </c:if> 
	         <c:if test="${mem_id!= null}">  
	            <li><a href="../board/r1BoardList2.do?mem_id=${mem_id}&writer=${writer}">관리자작성</a></li>
	            <li><a href="../board/R2BoardList2.do?mem_id=${mem_id}&writer=${writer}">회원작성</a></li>
	            <li><a href="../board/freeBoardList2.do?mem_id=${mem_id}&writer=${writer}">자유게시판</a></li>
	            <li><a href="../board/eventList2.do?mem_id=${mem_id}&writer=${writer}">이벤트</a></li>
	         </c:if>
            </ul>
        </div>
    </div><!-- gnbBox -->
</div> <!-- gnbCon -->

 </div><!-- header -->
  </body>
</html>