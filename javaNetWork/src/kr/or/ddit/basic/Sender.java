package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.security.sasl.SaslClient;

//이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당한다.

public class Sender extends Thread{
     private Socket socket;
     private DataOutputStream dos;
     private String name;
     private Scanner scan;
	
	public Sender(Socket socket) {
	    super();
		this.socket = socket;
	   
		scan = new Scanner(System.in);
	    
	    System.out.println("이름 입력:");
	    name = scan.nextLine();
	    
	    try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
     
     
     private void rin() {
		while(dos!=null) {
			try {
				dos.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
}


















