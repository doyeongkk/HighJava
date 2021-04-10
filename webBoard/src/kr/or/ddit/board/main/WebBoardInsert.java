package kr.or.ddit.board.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IWebBoardService;
import kr.or.ddit.board.service.WebBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

/**
 * Servlet implementation class WebBoardInsert
 */
@WebServlet("/WebBoardInsert.do")
public class WebBoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebBoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//0. 클라이언트 데이터 가져오기
		String board_writer = request.getParameter("board_writer");
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_writer(board_writer);
		boardVo.setBoard_title(board_title);
		boardVo.setBoard_content(board_content);
		
		//1.
		IWebBoardService service = WebBoardServiceImpl.getService();
		
		//2.
		int seq = service.insertBoard(boardVo);
		
		//3.
		request.setAttribute("result", seq);
		
		//4.
		request.getRequestDispatcher("jdbcboard/result.jsp").forward(request, response);
	}

}
