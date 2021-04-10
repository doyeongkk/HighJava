package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	//1번
	 private static  MemberDaoImpl  dao;
	
	//2번
	 public MemberDaoImpl() {}
	
	//3번
    public static MemberDaoImpl getInstance() {
	   if(dao ==null) dao = new MemberDaoImpl();
	   return dao;
   }
	 
	 
   @Override
   public int insertMember(MemberVO memVo) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int cnt = 0;   // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
               + " values(?, ?, ?, ?)";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memVo.getMem_id());
         pstmt.setString(2, memVo.getMem_name());
         pstmt.setString(3, memVo.getMem_tel());
         pstmt.setString(4, memVo.getMem_addr());
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      return cnt;
   }

   @Override
   public int deleteMember(String memId) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int cnt = 0;   // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "delete from mymember where mem_id = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memId);
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
         
      } finally {
         if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      return cnt;
   }

   @Override
   public int updateMember(MemberVO memVo) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      int cnt = 0;   // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)
      
      try {
         conn = DBUtil3.getConnection();
         String sql = "update mymember set mem_name = ?, mem_tel = ?, "
               + "mem_addr = ? where mem_id = ? ";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, memVo.getMem_name());
         pstmt.setString(2, memVo.getMem_tel());
         pstmt.setString(3, memVo.getMem_addr());
         pstmt.setString(4, memVo.getMem_id());
         
         
         cnt = pstmt.executeUpdate(); 
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      return cnt;
   }

   @Override
   public List<MemberVO> getAllMemberList() {
      // TODO Auto-generated method stub
	   

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		List<MemberVO> memList = null; //MemberVO객체가 저장될 List객체 변수 선언
     
		try {
			conn = DBUtil3.getConnection();

			String sql = "SELECT * FROM MYMEMBER";

			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			memList = new ArrayList<>();  // List객채 생성
			
			while(rs.next()) {
				MemberVO memVo = new MemberVO(); // MemberVO객체 생성
				
				
				// ResultSet객체의 데이터를 가져와 MemberVO객체에 넣는다.
				 memVo. setMem_id(rs.getString("mem_id"));
			     memVo. setMem_name(rs.getString("mem_name"));
			    memVo. setMem_tel(rs.getString("mem_tel"));
			    memVo. setMem_tel(rs.getString("mem_addr"));
			    
			    memList.add(memVo);
			}
			
			
			
		} catch (SQLException e) {
			memList =null;
			e.printStackTrace();
		}if (rs != null) try {rs.close();} catch (SQLException e) {}
		if (stmt != null) try {stmt.close();} catch (SQLException e) {}
		if (conn != null)try {conn.close();} catch (SQLException e) {}
		
		return memList;
   }

   @Override
   public int getMemberCount(String memId) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int cnt = 0;
      try {
         conn = DBUtil3.getConnection();
         String sql = "select count(*) cnt from mymember where mem_id = ?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memId);
         
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            cnt = rs.getInt("cnt");
         }
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         if(rs!=null) try {rs.close();}catch(SQLException e) {}
         if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      return cnt;
   }




@Override
public int updateMember2(Map<String, String> paramMap) {
	// key값 ==> 회원ID(memID), 변경할
	
	Connection conn =null;
	PreparedStatement pstmt =null;
	int cnt =0;
	try {
		conn =DBUtil3.getConnection();
		
		String sql ="  update mymember set " + 
							paramMap.get("field") + "=?  where mem_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memId"));
		
		cnt = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		cnt=0;
		e.printStackTrace();
	} finally {
        if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
        if(conn!=null) try {conn.close();}catch(SQLException e) {}
     }
	
	
	
	return cnt;
}
   
}