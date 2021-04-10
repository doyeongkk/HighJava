package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Hotel2 {
   
   static Hotel2 hotel2 = new Hotel2();
   Map<Integer, Room1> roomMap;
   private Scanner scan;
   boolean changeKey = false;
   public Hotel2() {
      
      roomMap = new HashMap<>();
      for(int i = 201; i < 210; i++) {
         roomMap.put(i, new Room1(i, "싱글룸", "-"));
      }
      
      for(int i = 301; i < 310; i++) {
         roomMap.put(i, new Room1(i, "더블룸", "-"));
      }
      
      for(int i = 401; i < 410; i++) {
         roomMap.put(i, new Room1(i, "스위트룸", "-"));
      }
      
      
      scan = new Scanner(System.in);
      
   }

   public static void main(String[] args) {
      hotel2.start();
   }
   
   private void start() {
      loadNum();
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
            saveNum();
            break;   
         case 5:
            checkNum();
            System.out.println("****************************************");
            System.out.println("          호텔문을 닫았습니다.    ");
            System.out.println("****************************************");
            return;
         default:
            System.out.println("번호가 잘못되었습니다. 다시입력하세요.");
         }
      }
   }
   
   private void checkNum() {
      if(changeKey==false) {
         System.out.println("변경된 내용이 있음으로 데이터를 저장합니다.");
         saveNum();
      }
   }
   
   private void loadNum() {
      try {
         
         FileInputStream fin = new FileInputStream("d:/D_Other/hotel.dat");
         BufferedInputStream bfin = new BufferedInputStream(fin);
         ObjectInputStream ois = new ObjectInputStream(bfin);
         
         //ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/phoneData.dat")));
         
         Object obj;
         
         try {
            System.out.println("저장된 방번호 불러오는 중");
            
            while ((obj = ois.readObject())!= null) {
               
               roomMap = (HashMap)obj;
               
               //roomMap.put(room.getRoomNum(), new Room1(room.getRoomNum(), room.getRoomType(), room.getName()));
               
               
            }
            
         } catch (EOFException e) {
            System.out.println("방번호 읽어오기 완료");
         } catch (ClassNotFoundException e) {
            return;
         } finally {
            ois.close();  // 스트림 닫기
         }
         
         
         
      } catch (IOException e) {
         // TODO: handle exception
      }
   }
   
   private void saveNum() {
      try {
         
         FileOutputStream fout = new FileOutputStream("d:/D_Other/hotel.dat");
         BufferedOutputStream bout = new BufferedOutputStream(fout);
         ObjectOutputStream oos = new ObjectOutputStream(bout);
         
         System.out.println("방번호 저장 시작...");
         oos.writeObject(roomMap);
         System.out.println("방번호 저장 끝...");
         oos.close();
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println(e);
      }
      changeKey = false;
   }
   
   private void checkOut() {
      System.out.println("------------------------");
      System.out.println("체크아웃 작업");
      System.out.println("------------------------");
      System.out.println("체크아웃 할 방 번호를 입력하세요.");
      System.out.print("방번호 입력 >> ");
      int roomNum = Integer.parseInt(scan.nextLine());
      Room1 r = roomMap.get(roomNum);
      if(roomMap.get(roomNum) == null) {
         System.out.println(roomNum + "호실은 존재하지 않습니다.");
      }else {
         if(r.getName().equals("-")) {
            System.out.println(roomNum + "호 객체에는 체크인 한 사람이 없습니다.");
         }
         String name = r.getName();
         roomMap.put(roomNum, new Room1(roomNum, r.roomType, "-"));
         System.out.println(roomNum + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");
      }
   }
   
   private void checkIn() {
      System.out.println("------------------------");
      System.out.println("체크인 작업");
      System.out.println("------------------------");
      System.out.println("*201~209 : 싱글룸");
      System.out.println("*301~309 : 더블룸");
      System.out.println("*401~409 : 스위트룸");
      System.out.println("-------------------------");
      System.out.print("방 번호 입력 >> ");
      int roomNum = Integer.parseInt(scan.nextLine());
      Room1 r = roomMap.get(roomNum);
      if(roomMap.get(roomNum) == null) {
         System.out.println(roomNum + "호실은 존재하지 않습니다.");
      }else {
         if(!r.getName().equals("-")) {
            System.out.println(roomNum + "방이 차있습니다.");
            return;
         }
         System.out.print("이름 입력");
         String name = scan.nextLine();
         roomMap.put(roomNum, new Room1(roomNum, r.roomType, name));
         System.out.println("체크인이 완료되었습니다.");
      }
   }
   
   private int displayMenu() {
      System.out.println("어떤 업무를 하시겠습니까?");
      System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.방번호 저장\t5.업무종료");
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
         Room1 r = roomMap.get(number);
         System.out.println(r.getRoomNum() + "\t" + r.getRoomType() + "\t" + r.getName());
         
      }
         
   }
   }
class Room1 implements Serializable{
   private static final long serialVersionUID = 1L;
   int roomNum;
   String roomType;
   String name;
   
   public Room1(int roomNum, String roomType, String name) {
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