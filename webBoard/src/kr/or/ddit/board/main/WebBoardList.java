package kr.or.ddit.board.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IWebBoardService;
import kr.or.ddit.board.service.WebBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

/**
 * Servlet implementation class JdbcBoardList
 */
@WebServlet("/WebBoardList.do")
public class WebBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebBoardList() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전체 리스트 가져오기
		
		// 0.
		String input = request.getParameter("board_title");
		
		// 1. service객체
		IWebBoardService service = WebBoardServiceImpl.getService();
		
		// 2. 메서드 호출
		List<JdbcBoardVO> list = service.getSearchBoardList(input);
		
		// 3. 결과값 저장
		request.setAttribute("list", list);
		
		// 4.
		request.getRequestDispatcher("jdbcboard/searchBoardList.jsp").forward(request, response);
	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 페이지별 리스트 가져오기
//
//		//0. page번호 가져오기
//		int cpage = Integer.parseInt(request.getParameter("page"));
//		
//		//1. service객체
//		IWebBoardService service = WebBoardServiceImpl.getService();
//		
//		//전체 글 갯수 가져오기
//		int totalCount = service.getTotalCount();
//		
//		//한 페이지당 출력할 글 갯수
//		int perList = 5;
//		
//		int start = (cpage-1) * perList + 1;
//		//cpage = 1 -> 1
//		//cpage = 2 -> 4
//		//cpage = 3 -> 7
//		int end = start + perList -1;
//		//start 17 ~ 19  start = 20;
//		if(end>totalCount) end = totalCount;
//		
//		//한 화면에 출력될 페이지 갯수
//		int perPage = 3;
//		int totalPage = (int)Math.ceil(totalCount / (double)perList);
//		
//		int startPage =((cpage -1) /perPage * perPage) +1;
//		//cpage=1 -> 1 //cpage=2 -> 1 //cpage=3 -> 3 //cpage=4 -> 3
//		int endPage = startPage + perPage - 1;
//		if(endPage > totalPage) endPage = totalPage;
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("start", start);
//		map.put("end", end);
//		
//		//2. 메서드 호출
//		List<JdbcBoardVO> list = service.getAllBoardList(map);
//		
//		//3. 결과값 저장
//		request.setAttribute("list", list);
//		request.setAttribute("sp", startPage);
//		request.setAttribute("ep", endPage);
//		request.setAttribute("tp", totalPage);
//		
//		//4.
//		request.getRequestDispatcher("jdbcboard/getAllBoardList.jsp").forward(request, response);
//	}

}
