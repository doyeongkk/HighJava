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
			height : 400px;
			margin : 15px;
			padding : 20px;
		}
	</style>
	<script>
		$(function() {
			$('#back').on('click', function() {
				location.href = "<%=request.getContextPath()%>/board/boardList.do";
			})
		})
	</script>
	
</head>
<body>
	<div>
		<h2>글쓰기</h2>
		<form id = "boardForm" method = "post" action="<%=request.getContextPath() %>/board/boardInsert.do">
				<label>제 목 :</label> 
				<input type = "text" name = "board_title" id="board_title"><br>
				<label>작성자 :</label>
				<input type = "text" name = "board_writer" id = "board_writer"><br>
				<label>내용</label><br>
				<textarea name = "board_content" id = "board_content" cols = "40" rows = "10"></textarea><br>
				<input type = "submit" value = "확인">
				<input type = "reset" value = "취소">
				<input type = "button" value = "뒤로가기" id = "back">
		</form>
	</div>
</body>
</html>