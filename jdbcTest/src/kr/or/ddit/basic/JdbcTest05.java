package kr.or.ddit.basic;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * MVC패턴에 대하여 조사하여 학습하기 
 * 
 * 
 * 
  LPROD테이블에 새로운 데이터 추가하기
  
    추가 할 데이터 중 Lprod_gu와 Lprod_nm은 직접 입력 받아서  처리하는데 
    입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
    그리고 Lprod_id값은 현재의 Lprod_id값 중 제일 큰 값 보다 1 증가된 값으로 한다. 
  
   
*/
public class JdbcTest05 {

	public static void main(String[] args) {
		
Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
/*
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "doyeong", "java");
*/

			conn = DBUtil.getConnection();
			
			String gu = "";

			while (true) {

				System.out.print("Lprod_gu 입력 : ");
				gu = scan.nextLine();
				String sql1 = "select count(*) from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, gu);
				rs = pstmt.executeQuery();
				rs.next();

				if (rs.getInt(1) == 1) {

					System.out.println("lprod_gu 중복!! 다시 입력하세요");

				}else break;

			}

			pstmt.close();

			System.out.println("Lprod_nm 입력 : ");

			String nm = scan.nextLine();

			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values ( (select max(lprod_id)+1 from lprod), ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gu);
			pstmt.setString(2, nm);

			

			int result = pstmt.executeUpdate();
			System.out.println(result + "개의 정보가 추가 되었습니다!");

			

		} catch (SQLException e) {
			e.printStackTrace();
			
		/*
		} catch (ClassNotFoundException e) {
			e.printStackTrace();*/

		} finally {

			if(rs !=null) try { rs.close(); } catch (SQLException e) {}
			if(pstmt !=null) try { pstmt.close(); } catch (SQLException e) {}
			if(stmt !=null) try { stmt.close(); } catch (SQLException e) {}
			if(conn !=null) try { conn.close(); } catch (SQLException e) {}

		}

}
}