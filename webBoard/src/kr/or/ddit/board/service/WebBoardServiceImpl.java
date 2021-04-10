package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.IWebBoardDao;
import kr.or.ddit.board.dao.WebBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class WebBoardServiceImpl implements IWebBoardService {
	private IWebBoardDao dao;
	private static IWebBoardService service;
	
	private WebBoardServiceImpl() {
		dao = WebBoardDaoImpl.getDao();
	}
	
	public static IWebBoardService getService() {
		if(service==null) service = new WebBoardServiceImpl();
		
		return service;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList(Map<String, Object> map){
		
		List<JdbcBoardVO> list = null;
		
		try {
			list = dao.getAllBoardList(map);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		int cnt = 0;
		try {
			cnt = dao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int seq = 0;
		
		try {
			seq = dao.insertBoard(boardVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return seq; 
	}

	@Override
	public int deleteBoard(int board_No) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			cnt = dao.deleteBoard(board_No);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			cnt = dao.updateBoard(boardVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int setCountIncrement(int board_No) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			cnt = dao.setCountIncrement(board_No);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}


	
	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> list = null;
		
		try {
			list = dao.getSearchBoardList(title);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	

}
