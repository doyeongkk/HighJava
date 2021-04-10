package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");

		
		//URLConnection 구하기
		URLConnection urlCon = url.openConnection();
		
		//Header 정보 가져오기 
	    Map<String, List<String>> headerMap = urlCon.getHeaderFields();
	    
	    // headerMap의 key값과 value값 출력하기
	    for(String headerkey: headerMap.keySet()) {
	    	System.out.println(headerkey + ":"+ headerMap.get(headerkey));
	    }
	    System.out.println("-------------------------------");
	
      // 해당 문서의 내용을 가져오기 
	
	/*
	  // 방법1 ==> URLConnection객체를 이용하는 방법
	
	  // 서버에 있는 파일의 내용을 가져오기 위한 입력용 스트림 객체 생성
	  InputStream is = urlCon.getInputStream();
	  InputStreamReader isr = new InputStreamReader(is, "urf-8");
	  BufferedReader br = new BufferedReader(isr);
	  
	  //자료를 읽어와 출력하기
	  while(true) {
		  String str = br.readLine(); //한 줄씩 일겅온다.
		  if(str==null) break;  //읽어온 값이 null이면 반복문 탈출
		  System.out.println(str);
	  }
	  br.close();*/

	//방법 2 ==> URL 객체 open
		InputStream is2 = url.openStream();
	
		  
		  InputStreamReader isr = new InputStreamReader(is2, "utf-8");
		  BufferedReader br = new BufferedReader(isr);
		  
		  //자료를 읽어와 출력하기
		  while(true) {
			  String str = br.readLine(); //한 줄씩 일겅온다.
			  if(str==null) break;  //읽어온 값이 null이면 반복문 탈출
			  System.out.println(str);
		  }
		  br.close();
		  
	
	
	
	}
	
	}







