package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Hotel {
   private Map<Integer, Room> roomMap;
   private Scanner scan;
  
   public Hotel() {
      roomMap = new LinkedHashMap<>();
      for(int i = 201; i < 210; i++) {
         roomMap.put(i, new Room(i, "싱글룸", "-"));
      }
      
      for(int i = 301; i < 310; i++) {
         roomMap.put(i, new Room(i, "더블룸", "-"));
      }
      
      for(int i = 401; i < 410; i++) {
         roomMap.put(i, new Room(i, "스위트룸", "-"));
      }
      
      
      scan = new Scanner(System.in);
   }

   public static void main(String[] args) {
      new Hotel().start();
   }
   
   private void start() {
      System.out.println("********************************");
      System.out.println("     호텔문을 열었습니다. 어서오십시오. ");
      System.out.println("********************************");
      while(true) {
         int input = displayMenu();
         switch(input){
         case 1:
            checkIn();
            break;
         case 2:
            checkOut();
            break;
         case 3:
            displayAll();
            break;
         case 4:
            System.out.println("프로그램을 종료합니다");
            return;
         default:
            System.out.println("번호가 잘못되었습니다. 다시입력하세요.");
         }
      }
   }
   
 

 private void checkOut() {
      System.out.println("------------------------");
      System.out.println("체크아웃 작업");
      System.out.println("------------------------");
      System.out.println("체크아웃 할 방 번호를 입력하세요.");
      System.out.print("방번호 입력 >> ");
      int roomNum = Integer.parseInt(scan.nextLine());
      Room r = roomMap.get(roomNum);
      if(roomMap.get(roomNum) == null) {
         System.out.println(roomNum + "호실은 존재하지 않습니다.");
      }else {
         if(r.getName().equals("-")) {
            System.out.println(roomNum + "호 객체에는 체크인 한 사람이 없습니다.");
         }
         String name = r.getName();
         roomMap.put(roomNum, new Room(roomNum, r.roomType, "-"));
         System.out.println(roomNum + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");
      }
   }
   
   private void checkIn() {
      System.out.println("------------------------");
      System.out.println("체크인 작업");
      System.out.println("------------------------");
      System.out.println("*201~209 : 싱글룸");
      System.out.println("*301~209 : 더블룸");
      System.out.println("*401~209 : 스위트룸");
      System.out.println("-------------------------");
      System.out.print("방 번호 입력 >> ");
      int roomNum = Integer.parseInt(scan.nextLine());
      Room r = roomMap.get(roomNum);
      if(roomMap.get(roomNum) == null) {
         System.out.println(roomNum + "호실은 존재하지 않습니다.");
      }else {
         if(!r.getName().equals("-")) {
            System.out.println(roomNum + "방이 차있습니다.");
            return;
         }
         System.out.print("이름 입력");
         String name = scan.nextLine();
         roomMap.put(roomNum, new Room(roomNum, r.roomType, name));
         System.out.println("체크인이 완료되었습니다.");
      }
   }
   
   private int displayMenu() {
      System.out.println("어떤 업무를 하시겠습니까?");
      System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
      System.out.print("선택>> ");
      int num = Integer.parseInt(scan.nextLine());
      return num;
   }
   
   private void displayAll() {
      System.out.println("         현재 객실  상태           ");
      System.out.println("방 번호     방 종류     투숙객 이름");
      Iterator<Integer> roomIt = roomMap.keySet().iterator();
      while(roomIt.hasNext()) {
         int number = roomIt.next();
         Room r = roomMap.get(number);
         System.out.println(r.getRoomNum() + "\t" + r.getRoomType() + "\t" + r.getName());
         
      }
         
   }

   class Room {
      int roomNum;
      String roomType;
      String name;

      public Room(int roomNum, String roomType, String name) {
         super();
         this.roomNum = roomNum;
         this.roomType = roomType;
         this.name = name;
      }

      public int getRoomNum() {
         return roomNum;
      }

      public void setRoomNum(int roomNum) {
         this.roomNum = roomNum;
      }

      public String getRoomType() {
         return roomType;
      }

      public void setRoomType(String roomType) {
         this.roomType = roomType;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      @Override
      public String toString() {
         return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", name=" + name + "]";
      }
      
      

   }
}