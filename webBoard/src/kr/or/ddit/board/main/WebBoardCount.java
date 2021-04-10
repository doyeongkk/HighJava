package kr.or.ddit.board.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IWebBoardService;
import kr.or.ddit.board.service.WebBoardServiceImpl;

/**
 * Servlet implementation class HitUpdate
 */
@WebServlet("/WebBoardCount.do")
public class WebBoardCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebBoardCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//0.
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		//1.
		IWebBoardService service = WebBoardServiceImpl.getService();
		
		//2.
		int cnt = service.setCountIncrement(board_no);
		
		//3.
		request.setAttribute("result", cnt);
		
		//4.
		request.getRequestDispatcher("jdbcboard/result.jsp").forward(request, response);
		
		
	}

}
