package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IJdbcBoardService;
import service.JdbcBoardServiceImpl;
import vo.JdbcBoardVO;
import web.IAction;

public class BoardInsertAction implements IAction{

	@Override
	public boolean isRedirect() {
		return true;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String board_title = request.getParameter("board_title");
		String board_writer = request.getParameter("board_writer");
		String board_content = request.getParameter("board_content");
		
		JdbcBoardVO vo = new JdbcBoardVO();
		vo.setBoard_title(board_title);
		vo.setBoard_writer(board_writer);
		vo.setBoard_content(board_content);
		
		IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();
		
		service.insertBoard(vo);
		
		
		return "/board/boardList.do";
		
	}

}
