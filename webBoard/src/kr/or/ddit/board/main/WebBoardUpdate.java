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
 * Servlet implementation class WebBoardUpdate
 */
@WebServlet("/WebBoardUpdate.do")
public class WebBoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebBoardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String board_writer = request.getParameter("board_writer");
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		//0. 
		// 가져와서 BoardVO에 저장
		JdbcBoardVO vo = new JdbcBoardVO();
		vo.setBoard_writer(board_writer);
		vo.setBoard_title(board_title);
		vo.setBoard_content(board_content);
		vo.setBoard_no(board_no);
		
		
		//1
		IWebBoardService service = WebBoardServiceImpl.getService();
		//2
		int cnt = service.updateBoard(vo);
		//3
		request.setAttribute("result", cnt);
		//4
		request.getRequestDispatcher("jdbcboard/result.jsp").forward(request, response);
	}

}
