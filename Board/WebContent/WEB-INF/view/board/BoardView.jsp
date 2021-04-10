<%@page import="vo.JdbcBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<style>
		label {
			display : inline-block;
			width : 100px;
			height : 30px;
		}
		div {
			border: 2px solid lightgray;
			width : 300px;
			height : 300px;
			margin : 15px;
			padding : 20px;
		}
	</style>
	<script>
		$(function() {
			$('#back').on('click', function() {
				location.href = "<%=request.getContextPath()%>/board/boardList.do";
			})
			
			// 수정 버튼 클릭
			$('#btnUpdate').on('click', function() {
				var form = $("#boardForm").get(0);
				form.action = "<%=request.getContextPath()%>/board/boardUpdateForm.do";
				form.submit();
			})
			$('#btnDelete').on('click',function() {
				var form = $("#boardForm").get(0);
				form.action = "<%=request.getContextPath()%>/board/boardDelete.do";
				form.submit();
			})
		})
	</script>
</head>
<body>
	<%
		JdbcBoardVO vo = (JdbcBoardVO)request.getAttribute("boardVo");
	%>
	<form id="boardForm" method="post" action="">
		<input type = "hidden" name="board_no" id="board_no" value = "<%=vo.getBoard_no() %>">
		<div>
			<h2><%=vo.getBoard_no() %>번 게시글 </h2>
			<hr>
			<label>- 제 목 : </label><%=vo.getBoard_title() %><br>
			<label>- 작성자 : </label><%=vo.getBoard_writer() %><br>
			<label>- 작성일 : </label><%=vo.getBoard_date() %><br>
			<label>- 조회수 : </label><%=vo.getBoard_cnt() %><br> 
			<label>- 내  용 : </label><br><br><%=vo.getBoard_content() %><br>
		</div>
		<input type = "button" value = "수정하기" id = "btnUpdate">
		<input type = "button" value = "삭제하기" id = "btnDelete">
		<input type = "button" value = "뒤로가기" id = "back">
	</form>
</body>
</html>