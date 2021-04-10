package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {

	private SqlMapClient smc;
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao == null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		
		int cnt = 0;
		
		try {
			
			Object obj = smc.insert("board.insertBoard" , boardVo);
			if(obj == null) cnt = 1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			
			
			cnt = smc.delete("board.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			
			
			cnt = smc.update("board.updateBoard", boardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		
		List<JdbcBoardVO> boardList =  null;
		
		try {
			
			boardList = smc.queryForList("board.getAllBoard");
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			
			boardVo = (JdbcBoardVO) smc.queryForObject("board.getBoard", boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} 
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		
		List<JdbcBoardVO> boardList =  null;
		
		try {
			
			boardList = smc.queryForList("board.getSearch", title);
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public int setCountincrement(int boardNo) {
		int cnt = 0;
		
		try {
			
			cnt = smc.update("board.setCount", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

}
