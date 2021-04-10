package kr.or.ddit.board.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IWebBoardService;
import kr.or.ddit.board.service.WebBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;
import sun.security.krb5.internal.SeqNumber;

/**
 * Servlet implementation class JdbcBoardList
 */
@WebServlet("/WebSearchList.do")
public class WebSearchList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebSearchList() {
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
}
