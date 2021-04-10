package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;

/*
 
  'd:/D_Other/'폴더에 있는 '극한직업.jpg'파일을
  'd:/D_Other/연습용' 폴더에 '극한직업_복사본.jpg'이름으로 복사하는 프로그램을 작성하시오.
  
 
 */

public class FileCopyTest {

	public static void main(String[] args) {
		try {
			File file = new File("d:/D_Other/연습용/극한직업.jpg");
		
		
			
			
			
			
			//복사할 파일 스트링 객체 생성(원본파일용)
			FileInputStream fis = new FileInputStream("d:/D_Other/극한직업.jpg"); //읽을 파일
			//복사될 파일 스트림 객체 생성(저장될 파일용)
			FileOutputStream fos = new FileOutputStream(file); //복사할 파일
			
			
			System.out.println("복사 시작....");
			
			int c;
			
			while((c = fis.read()) != -1) {  //더이상 읽을게 없으면 -1을 반환한다.
				fos.write(c);
			}
			
			// 사용했던 스트림 닫기 
			fis.close();
			fos.close();
			System.out.println("복사 완료....");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	 
	 
	  }
	}

	