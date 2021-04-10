package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IWebBoardDao {


	public List<JdbcBoardVO> getAllBoardList(Map<String, Object> map) throws SQLException;
	
	public int getTotalCount() throws SQLException;
	
	public int insertBoard(JdbcBoardVO boardVo) throws SQLException;
	
	public int deleteBoard(int board_No) throws SQLException;
	
	public int updateBoard(JdbcBoardVO boardVo) throws SQLException;
	
	public int setCountIncrement(int board_No) throws SQLException;

	public List<JdbcBoardVO> getSearchBoardList(String title) throws SQLException;
	
}
