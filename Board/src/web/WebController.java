package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		
		uri = uri.substring(request.getContextPath().length());
		
		String viewPage = null;				// view 페이지가 저장될 변수 선언
		IAction action = null;
		
		action = URIActionMapper.getAction(uri);
		
		if(action == null) {
			// 실행할 Action이 없으면 404에러 처리
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			// 실행 작업 완료 후 View페이지를 받는다.
			viewPage = action.process(request, response);
			
			if(viewPage!=null) {
				if(action.isRedirect()) {	// 리다이렉트가 true일 경우
					response.sendRedirect(request.getContextPath() + viewPage);
					
				} else {		// 포워딩 처리
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view"+ viewPage);
					rd.forward(request, response);
				}
			}
		}
		
	}		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
