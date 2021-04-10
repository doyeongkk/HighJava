package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IJdbcBoardService;
import service.JdbcBoardServiceImpl;
import vo.JdbcBoardVO;
import web.IAction;

public class BoardViewAction implements IAction{

	@Override
	public boolean isRedirect() {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();
		
		JdbcBoardVO vo = service.getBoard(board_no);
		
		request.setAttribute("boardVo", vo);
		
		return "/board/BoardView.jsp";
	}

}
