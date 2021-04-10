package kr.or.ddit.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	
	// 송신용 패킷변수와 수신용 패킷변수 선언
	DatagramPacket outpacket, inpacket;
	
	// 수신 받은 데이터가 저장될 byte형 배열 변수 선언
	byte[] bMsg = new byte[512];
	
	try {
		
		DatagramSocket socket = new DatagramSocket();
		
		
		// 접속할 곳의 주소 객체 생성
	    //	InetAddress address = InetAddress.getByName("localhost");
		InetAddress address = InetAddress.getByName("127.0.0.1");
		
		while(true) {
			// 전송할 메시지 입력
			System.out.println("보낼 메시지 입력: ");
			String msg = scan.nextLine();
			
			if("/end".equals(msg)) { // 메시지 중지 여부 검사 
				break;
			}
			
			// 전송할 패킷객체 생성
			outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
					address, 8888);
			
			// 데이터 전송
			socket.send(outpacket);
			//------------
			//
			// 수신용 패킷 객체 생성
			inpacket = new DatagramPacket(bMsg, bMsg.length);
			
			//수신
			socket.receive(inpacket);
			
			System.out.println("서버에서 온 응답 데이터 : " + 
			new String(bMsg, 0, inpacket.getLength()));
			System.out.println();
			
		}
		
		System.out.println("통신 끝...");
		socket.close();
		{
			
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	

	}

}










