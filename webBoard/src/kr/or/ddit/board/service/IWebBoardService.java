package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IWebBoardService {

	public List<JdbcBoardVO> getAllBoardList(Map<String, Object> map);
	
	public int getTotalCount();

	public int insertBoard(JdbcBoardVO boardVo);
	
	public int deleteBoard(int board_No);
	
	public int updateBoard(JdbcBoardVO boardVo);
	
	public int setCountIncrement(int board_No);
	
	public List<JdbcBoardVO> getSearchBoardList(String title);

}
