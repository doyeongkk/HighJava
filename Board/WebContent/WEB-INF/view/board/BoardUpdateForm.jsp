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
			height : 250px;
			margin : 15px;
			padding : 20px;
		}
	</style>
	<script>
		$(function() {
			$("#btnList").on("click", function(){
				location.href = "<%=request.getContextPath()%>/board/boardList.do";
			});
		})
	</script>
</head>
<body>
	<%
		JdbcBoardVO vo = (JdbcBoardVO)request.getAttribute("boardVo");
	%>
	<form id = "boardForm" method = "post" action="<%=request.getContextPath() %>/board/boardUpdate.do">
		<input type = "hidden" name = "board_no" id = "board_no" value = "<%=vo.getBoard_no() %>">
		<div>
			<label>제 목 :</label> 
			<input type = "text" name = "board_title" id="board_title" value="<%=vo.getBoard_title() %>"><br>
			<label>내용</label><br>
			<textarea name = "board_content" id = "board_content" cols = "40" rows = "10"><%=vo.getBoard_content()%></textarea><br>
		</div>
		<input type="submit" value="저장">
		<input type="reset" value="취소">
		<input type="button" value="게시글 목록" id="btnList">
	</form>
</body>
</html>