package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IJdbcBoardService;
import service.JdbcBoardServiceImpl;
import vo.JdbcBoardVO;
import web.IAction;

public class BoardListAction implements IAction{

	@Override
	public boolean isRedirect() {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String gs = request.getParameter("gsearch");
		
		System.out.println(gs);
		IJdbcBoardService service = JdbcBoardServiceImpl.getInstance();
		List<JdbcBoardVO> list = null;
		
		if(gs == null) {
			list = service.getAllBoardList();
		
		} else {
			list = service.getSearchBoardList(gs);
		}
		request.setAttribute("list", list);
		
		return "/board/BoardList.jsp";
	}

}
