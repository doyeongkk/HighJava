package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface JdbcBoardDao {
	/**
	 * JdbcBoardVo에 담겨진 자료를 DB에 insert하는 메서드
	 * @param boardVo DB에 insert할 자료가 저장될 JdbcBoardVo 객체
	 * @return 작업성공: 1, 작업실패:0
	 */
	
     public int insertBoard(JdbcBoardVO boardVo);
     /**
      * 게시글 번호를 매개값으로 받아서 그 게시글 정보를 삭제하는 메서드
      * @param boardNo 삭제할 게시글 번호 
      * @return 작업 성공:1 , 작업 실패: 0
      */
     
     
     public int deleteBoard(int boardNo);
   /**
    * 하나의 JdbcBoardVO 자료를 이용하여 DB에 update 하는 메서드
    * @param boardVo update할 게시글 정보가 저장될 JdbcBoardVO객체
    * @return 작업성공 1 작업실패:0
    */
      public int updateBoard(JdbcBoardVO boardVo);
      /**
       * 
       * @return
       */
       
      public List<JdbcBoardVO> getAllBoardList();
      /**
       * 게시글 번호를 매개값으로 받아서 그 게시글 정보의 내용을 가져와 반환하는 메서드
       * @param boardNo 가져올 게시글 번호
       * @return 게시글 번호에 맞는 자료가 있으면 게시글 정보를 담고 잇는 JdbcBoardVO객체, 자료가 없으면 null반환
       */
      public JdbcBoardVO getBoard(int boardNo);
      
      /**
       *  게시글의 제목을 이용하여 데이터를 검색하는 메서드
       * @param title 검색할 게시글 제목
       * @return 검색된 결과를 담은 List객체
       */
      public List<JdbcBoardVO> getSearchBoardList(String title);
      /**
       * 게시글 번호를 매개값으로 받아서 해당 자료의 조회수를 증가시키는 메서드 
       * @param boardNo 조회수를 증가할 게시글 번호 
       * @return 작업성공: 1, 작업실패: 0
       */
      public int setCountIncrement(int boardNo);
      
}


















