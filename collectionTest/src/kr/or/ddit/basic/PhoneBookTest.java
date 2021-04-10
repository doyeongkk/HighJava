package kr.or.ddit.basic;
/*
 *    문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고 
 *       Map을 이용해서 전화번호 정보를 관리하는 프로그램을 작성하시오.
 *       이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
 *       
 *       (Map의 구조는 key값으로 '이름'을 사용하고, value값으로 'Phone클래스의 인스턴스'로 한다.)
 * 
 *       - 삭제, 검색기능은 '이름'을 입력받아 처리한다.
 *    -------------------------------------------------------------------------------
 *    실행예시)
 *    ----------------------
 *    다음 메뉴를 선택하세요.
 *    1. 전화번호 등록
 *    2. 전화번호 수정
 *    3. 전화번호 삭제
 *    4. 전화번호 검색
 *    5. 전화번호 전체 출력
 *    0. 프로그램 종료
 *    ----------------------
 *    번호입력 >> 1
 *    
 *    새롭게 등록할 전화번호 정보를 입력하세요.
 *    이름 >> 홍길동
 *    전화번호 >> 010-1234-5678
 *    주소 >> 대전시 중구 대흥동
 *    
 *    '홍길동' 전화번호 등록 완료
 * 
 *    ----------------------
 *    다음 메뉴를 선택하세요.
 *    1. 전화번호 등록
 *    2. 전화번호 수정
 *    3. 전화번호 삭제
 *    4. 전화번호 검색
 *    5. 전화번호 전체 출력
 *    0. 프로그램 종료
 *    ----------------------
 *    번호입력 >> 1
 * 
 *    새롭게 등록할 전화번호 정보를 입력하세요.
 *    이름 >> 홍길동
 * 
 *    '홍길동'은 이미 등록된 사람입니다.
 * 
 * ----------------------
 *    다음 메뉴를 선택하세요.
 *    1. 전화번호 등록
 *    2. 전화번호 수정
 *    3. 전화번호 삭제
 *    4. 전화번호 검색
 *    5. 전화번호 전체 출력
 *    0. 프로그램 종료
 *    ----------------------
 *    번호입력 >> 5
 * 
 *    -------------------------------------
 *    번호   이름       전화번호          주소
 *    -------------------------------------
 *     1  홍길동    010-1234-5678  대전시 중구 대흥동
 *    ~~~~~~~~~~~~~~~~~~
 *    ~~~~~~~~~~~~~~~~~~
 *  -------------------------------------
 *  출력 완료...
 *  ----------------------
 *    다음 메뉴를 선택하세요.
 *    1. 전화번호 등록
 *    2. 전화번호 수정
 *    3. 전화번호 삭제
 *    4. 전화번호 검색
 *    5. 전화번호 전체 출력
 *    0. 프로그램 종료
 *    ----------------------
 *    번호입력 >> 0
 *    
 *    프로그램을 종료합니다..     
 */

import java.util.HashMap;
import java.util.Scanner;


public class PhoneBookTest {
   Phone p;
   Scanner sc = new Scanner(System.in);
   HashMap<String, Phone> info = new HashMap<>();

   public static void main(String[] args) {
      new PhoneBookTest().start();
   }
   
   public void start() {
      while(true) {
      System.out.println("다음 메뉴를 선택하세요.");
      System.out.println("1.전화번호 등록");
      System.out.println("2.전화번호 수정");
      System.out.println("3.전화번호 삭제");
      System.out.println("4.전화번호 검색");
      System.out.println("5.전화번호 전체 출력");
      System.out.println("0.프로그램종료");
      
      int input = Integer.parseInt(sc.nextLine());
      switch(input) {
      case 1:
         register();
         break;
      case 2:   
         update();
         break;
      case 3:
         delete();
         break;
      case 4:   
         select();
         break;
      case 5:
         selectAll();
         break;
      case 0:
         System.exit(0);
      }
   }
}      

   // 등록
   public void register() {
      System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
      String name;
         
         System.out.print("이름 >>");
         name = sc.nextLine();
         for (String key : info.keySet()) {
            if (key.equals(name)) {
               System.out.println("동일한 이름이 존재합니다.");
               return;
            } 
         }
            
      System.out.print("주소>>");
      String addr = sc.nextLine();

      System.out.print("전화번호>>");
      String phoneNum = sc.nextLine();

      info.put(name, new Phone(name, addr, phoneNum));
      System.out.println("등록되었습니다.");
   }





   // 수정
   public void update() {
      System.out.println("정보수정할 이름을 입력하세요.");
      System.out.print("이름>>");
      String name = sc.nextLine();
      for (String key : info.keySet()) {
         if (key.equals(name)) {
            System.out.print("주소>>");
            String addr = sc.nextLine();

            System.out.print("전화번호>>");
            String phoneNum = sc.nextLine();

            info.put(name, new Phone(name, addr, phoneNum));
            System.out.println("수정되었습니다.");
         } 
      }return;

   }

   //삭제
   public void delete() {
      System.out.println("정보삭제할 이름을 입력하세요.");
      System.out.print("이름>>");
      String name = sc.nextLine();
      for (String key : info.keySet()) {
         if (key.equals(name)) {
            info.remove(name);
            System.out.println("삭제되었습니다.");
         } 
      }return;
   }
   
   //조회
   public void select() {
      System.out.println("정보조회할 이름을 입력하세요.");
      System.out.print("이름>>");
      String name = sc.nextLine();
      for (String key : info.keySet()) {
         if (key.equals(name)) {
            System.out.println("--------------------------");
            System.out.println("\t\t이름\t\t전화번호\t\t주소");
            System.out.println("\t" + info.get(name).getName() + info.get(name).getPhoneNum() + info.get(name).getAddr());
         } 
      }return;
   }


   public void selectAll() {
      int count=1;
         System.out.println("번호\t\t이름\t\t전화번호\t\t주소");
         for(String key : info.keySet()) {
            System.out.println(count + "\t\t" + info.get(key).getName() + "\t\t" + info.get(key).getPhoneNum() + "\t\t" + info.get(key).getAddr() );
            count++;
         }
      
   }

}


class Phone{
    private String name;
    private String addr;
    private String phoneNum;
    
      
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   public String getPhoneNum() {
      return phoneNum;
   }

   public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
   }

   public Phone(String name, String addr, String phoneNum) {
      super();
      this.name = name;
      this.addr = addr;
      this.phoneNum = phoneNum;
      
   }

   @Override
   public String toString() {
      return " [" + name + ", addr=" + addr + ", phoneNum=" + phoneNum + "]";
   }
    
   
}

















