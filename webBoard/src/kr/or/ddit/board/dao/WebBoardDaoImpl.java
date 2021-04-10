package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class WebBoardDaoImpl implements IWebBoardDao{
	private SqlMapClient client;
	private static IWebBoardDao dao;
	
	private WebBoardDaoImpl() {
		client = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IWebBoardDao getDao() {
		if(dao==null) dao = new WebBoardDaoImpl();
		return dao;
	}

	
	@Override
	public List<JdbcBoardVO> getAllBoardList(Map<String, Object> map) throws SQLException {
		// TODO Auto-generated method stub
		return client.queryForList("jdbcboard.getAllBoard", map);
	}
	
	@Override
	public int getTotalCount() throws SQLException {
		// TODO Auto-generated method stub
		return (Integer)client.queryForObject("jdbcboard.getTotalCount");
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) throws SQLException {
		// TODO Auto-generated method stub
		return (Integer)client.insert("jdbcboard.insertBoard", boardVo);
	}

	@Override
	public int deleteBoard(int board_No) throws SQLException {
		// TODO Auto-generated method stub
		return (Integer)client.delete("jdbcboard.deleteBoard", board_No);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) throws SQLException {
		// TODO Auto-generated method stub
		return client.update("jdbcboard.updateBoard", boardVo);
	}

	@Override
	public int setCountIncrement(int board_No) throws SQLException {
		// TODO Auto-generated method stub
		return client.update("jdbcboard.setCountIncrement", board_No);
	}




	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) throws SQLException {
		// TODO Auto-generated method stub
		return client.queryForList("jdbcboard.getSearchBoard", title);
	}


}
