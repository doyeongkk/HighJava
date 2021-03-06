package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

// 서버에 접속되면 'd:/D_Other/극한직업.jpg'파일을 전송한다.
public class TcpFileClient {
    private Socket socket;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    
    private void ClientStart() {
    	File file = new File("d:/D_Other/극한직업.jpg");
    	if(!file.exists()) {
    		System.out.println("전송할 파일이 없습니다.");
    		return;
    	}
    	try {
			socket = new Socket("192.168.42.34", 7878);

			System.out.println("파일 전송 시작...");
			
			// 파일의 내용을 읽어올 스트림 객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));
			
			// 소켓에 출력할 스트림 객체 생성
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			// 파일 내용을 읽어와서 소켓에 출력한다. 
			while ((length= bis.read(temp))>0) {
				bos.write(temp, 0, length);
			}
			
			bos.flush();
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
		System.out.println("파일 전송 실패!!!");
		e.printStackTrace();
		}finally {
			if(bis!=null) try {bis.close();} catch (IOException e) {}
			if(bos!=null) try {bos.close();} catch (IOException e) {}
			if(socket!=null) try {socket.close();} catch (IOException e) {}
		}
    }
    
	public static void main(String[] args) {
	   new TcpFileClient().ClientStart();
	}
}






