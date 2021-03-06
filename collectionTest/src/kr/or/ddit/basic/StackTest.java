package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {

	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1. 네이버");
		b.goURL("2. 야후");
		b.goURL("3. 구글");
		b.goURL("3. 다음");
 
		b.history();
		
		System.out.println("뒤로 가기 후 .....");
		b.goBack();
		b.history();
		
		System.out.println("뒤로 가기 후 .....");
		b.goBack();
		b.history();
		
		System.out.println("앞으로 가기 후.....");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트 방문 후 ....");
		b.goURL("5.네이트");
		b.history();
				
	}

}
// 웹브라우저의 앞으로가기, 뒤로가기 기능 구현(Stack이용)
class Browser{
	private LinkedList<String> back; //이전 방문 내역의 저장될 스택
	private LinkedList<String> forward; //다음 방문 내역이 저장될 스택
	private String currentURL; //현재 페이지
	 
	//생성자
	 public Browser() {
		 back = new LinkedList<>();
		 forward = new LinkedList<>();
		 currentURL = "";
	 }
	 
	 //사이트를 방문하는 메서드 ==> 매개변수ㅜ에 방문할 URL이 저장된다.
	 public void goURL(String url) {
		 System.out.println(url +"사이트에 접속했습니다.");
		 if(currentURL !=null && !currentURL.equals("")); { //현재 페이지가 있으면
			 back.push(currentURL); //현재 페이지를 back스택에 추가한다.
			
		 }
	    currentURL = url;   //입력한 URL로 현재 페이지를 변경한다.    
	 }
	  //뒤로 가기 기능
	 public void goBack(){
		 //isEmpty() ==> List가 비어 있으면 true, 비어있지 않으면 false 
		 if(!back.isEmpty()) {//비어있지 않으면 
			 forward.push(currentURL); //현재 페이지를 forward스택에 추가 
			 currentURL = back.pop(); //back스택에서 1개의 요소를 꺼내와 현재 페이지로 한다. 
			 
		 }
	 }
	 
      // 앞으로 가기 기능
	 public void goForward() {
		 if(!forward.isEmpty()) {
			 back.push(currentURL);    //현재 페이지를 back스택 추가 
			 currentURL = forward.pop(); //forward스택에서 1개의 요소를 꺼내와 현재 페이지로 한다. 
		 }
	 }


//방문 기록 확인하기
	 public void history() {
			  System.out.println();
			  System.out.println("---------------------------------");
			  System.out.println("  방   문  기  록");
			  System.out.println("----------------------------------");
			  System.out.println("back   ==> " + back);
			  System.out.println("현재   ==> " + currentURL);
			  System.out.println("forward   ==> " + forward);
			  System.out.println("--------------------------------- ");
			  System.out.println();
}

}








