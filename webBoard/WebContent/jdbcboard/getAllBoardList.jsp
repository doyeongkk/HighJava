
<%@page import="kr.or.ddit.board.vo.JdbcBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  List<JdbcBoardVO> list = (List<JdbcBoardVO>)request.getAttribute("list");
  int total = (Integer)request.getAttribute("tp");
  int startp = (Integer)request.getAttribute("sp");
  int endp = (Integer)request.getAttribute("ep");
%>
{
  "tp"  : "<%= total %>",
  "sp"  : "<%= startp %>",
  "ep"  : "<%= endp %>",
  "datas"  : 

[
	<%
		for(int i=0; i<list.size(); i++){
			JdbcBoardVO vo = list.get(i);
			if(i>0) out.print(",");
	%>
			{
				"no" 	:  "<%= vo.getBoard_no() %>",
				"name"  :  "<%= vo.getBoard_writer() %>",
				"title" :  "<%= vo.getBoard_title() %>",
				"hit"   :  "<%= vo.getBoard_cnt() %>",
				"cont"  :  "<%= vo.getBoard_content().replaceAll("\r","").replaceAll("\n", "<br>") %>",
				"date" :  "<%= vo.getBoard_date()%>"
			}


<% 	}
%>
]
}



