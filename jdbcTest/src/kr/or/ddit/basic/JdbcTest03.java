package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제2) lprod_id값 2개를 차례로 이벽 받아서 두 값 중 작은값부터 큰값사이의 자료들을 출력하시오. 
 
public class JdbcTest03 {
   
   
   public static void main(String[] args) {
      
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "doyeong";
      String password = "java";
      
      Scanner scan = new Scanner(System.in);
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      System.out.print("첫번 째 Lprod_Id값 입력");
      int input1 = Integer.parseInt(scan.nextLine());
      System.out.print("두번 째 Lprod_Id값 입력");
      int input2 = Integer.parseInt(scan.nextLine());
      int temp;
      
      if(input1 > input2) {
         temp = input1;
         input1 = input2;
         input2 = temp;
      }
      
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(url, user, password);
         
         String sql = "select * from lprod where lprod_id between " + input1 + " AND " + input2;
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         System.out.println("처리결과 출력");
         
         
            while(rs.next()) {
               System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
               System.out.println("Lprod_gu : " + rs.getString(2));
               System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
               System.out.println("--------------------------------------------");
               
            }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } finally {
         // 6. 사용했던 자원 반납하기
         if(rs!=null)try {rs.close(); }catch(SQLException e) {}
         if(stmt!=null)try {stmt.close(); }catch(SQLException e) {}
         if(conn!=null)try {conn.close(); }catch(SQLException e) {}
      }
      
   }

}