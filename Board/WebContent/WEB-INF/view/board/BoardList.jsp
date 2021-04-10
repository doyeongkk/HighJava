<%@page import="vo.JdbcBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
	<title>Insert title here</title>
	<style>
		td {
			width : 100px;
			height : 30px;
			text-align: center;
			
		}
		.a{
			text-align : left;
			padding-left: 10px;
			width : 250px;
		}
		div {
			border: 2px solid lightgray;
			width : 500px;
			height : 400px;
			margin : 15px;
			padding : 20px;
		}
	</style>
	<script>
		$(function() {
			$('#btnAdd').on('click', function() {
				location.href = "<%=request.getContextPath()%>/board/boardAddForm.do";
			})
			$('#close').on('click', function(){
				window.open.close();
			})
		})
	</script>
</head>
<body>
	<div>
		<h2>게시판</h2>
		<form action="<%=request.getContextPath()%>/board/boardList.do" method="get">
	    	검색 <input type="search" id = "gsearch" name="gsearch">
	    	<input type="submit" value = "검색">
		</form><br>
		<table border="1">
			<tr>
				<th>No</th>
				<th class = a>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		<%
			List<JdbcBoardVO> list = (List<JdbcBoardVO>)request.getAttribute("list");
			
			for(JdbcBoardVO vo : list) {
		%>
			<tr>
				<td><a href ="<%=request.getContextPath()%>/board/boardView.do?board_no=<%=vo.getBoard_no() %>"><%=vo.getBoard_no() %></a></td>
				<td class = a><%=vo.getBoard_title() %></td>
				<td><%=vo.getBoard_writer() %></td>
				<td><%=vo.getBoard_cnt() %></td>
			</tr>
		<%
			}
		%>
		</table>
		<br>
		<input type="button" id="btnAdd" value="글쓰기">
		<input type="button" id="close" value="종료">
	</div>
</body>
</html>