package kr.or.ddit.member.main;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.AES256Util;

/*
회원을 관리하는 프로그램 작성하기
(DB시스템의 MYMEMBER테이블 이용)

- 처리조건
1. 아래 메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
2. '자료 추가'에서는 입력한 회원 ID가 중복되는지 여부를 검사해서 중복되면 다시 입력 받도록 한다.
3. '자료 삭제'는 회원 ID를 입력 받아 삭제한다.
4. '자료 수정'은 회원 ID를 제외한 전체 자료를 수정한다.
5. '자료 수정2' 메뉴를 선택하면
    1. 회원이름 수정     2.회원전화번호 수정      3. 회원주소 수정    4. 취소  메뉴를 출력하고
    각  부 메뉴에 해당하는 데이터를 수정한다.
6. 회원ID를 추가할 때에는 암호화해서 추가하고, 화면에 보여줄 경우에는 복호화해서 보여준다. 


메뉴예시)
	-- 작업 선택 --
  1. 자료 추가					---> insert (C)
  2. 자료 삭제					---> delete (D)
  3. 자료 수정					---> update (U)
  4. 전체 자료 출력				---> select (R)
  5. 자료 수정2
  0. 작업 끝.
 ----------------- 
 작업 번호 >> 

*/




public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;   // Service객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	public static void main(String[] args) throws Exception{
		new MemberController().memberStart();
	}

	public void memberStart() throws Exception {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 : insert();			// 추가
					break;
				case 2 : deleteMember();			// 삭제
					break;
				case 3 : updateMember();			// 수정
					break;
				case 4 : displayMember();			// 전체 출력
					break;
				case 5 : updateMember2();			// 수정2
					break;
				case 0 :
					System.out.println();
					System.out.println("프로그램을 종료합니다.");
					return;
				default :
					System.out.println("잘못 선택했습니다. 다시 입력하세요.");
					System.out.println();
			}
		}
	}
	
	private int displayMenu() {
		System.out.println();
		System.out.println("  -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println("----------------");
		System.out.print(" 작업 선택 >> ");
		
		return scan.nextInt();
	}
	
	// 회원 정보 수정2
	private void updateMember2() throws Exception {
		AES256Util aes256= new AES256Util();
		System.out.println("삭제 할 아이디를 입력하세요:");	
		String memId = scan.nextLine();
		
		/*System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();*/
		int count = service.getMemberCount(aes256.encrypt(memId));
		
		//int count = service.getMemberCount(memId);
		
		if(count==0) {  // 없는 회원이면...
			System.out.println(memId + "는(은) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
	
	        
		
		System.out.println();
		System.out.println("수정할 항목을 선택하세요.");
		System.out.println("1. 회원이름 수정        2. 전화번호 수정       3. 회원주소 수정     4. 취소");
		System.out.println("--------------------------------------------------------");
		System.out.print("수정 항목 선택 >> ");
		int num = scan.nextInt();
		
		String updateField = null;		// 선택한 컬럼명이 저장될 변수
		String updateStr = null;		// 선택한 컬럼의 제목이 저장될 변수
		
		switch(num) {
			case 1 : updateField = "mem_name";
					 updateStr = "회원이름";
				break;
			case 2 : updateField = "mem_tel";
					updateStr = "전화번호";
				break;
			case 3 : updateField = "mem_addr";
					updateStr = "회원주소";
				break;
			default : return;	
		}
		
		// 수정할 데이터 입력 받기
		System.out.println();
		scan.nextLine();   // 입력버퍼 비우기
		System.out.print("새로운 " + updateStr + " >> ");
		String updateData = scan.nextLine();
		
		// 수정할 정보를 Map에 추가한다.
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memId", memId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0) {
			System.out.println("수정 작업 성공!!!!");
		}else {
			System.out.println("수정 작업 실패~~~~");
		}
		
		
	}
	
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() throws Exception {
		AES256Util aes256 = new AES256Util();
		List<MemberVO> memList = service.getAllMemberList();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" ID    이름       전화번호         주소");
		System.out.println("--------------------------------------");
		
		if(memList==null || memList.size()==0) {
			System.out.println(" 회원 정보가 하나도 없습니다.");
		}else {
			// List에 저장된 데이터 개수만큼 반복해서 자료를 출력한다.
			for(MemberVO memVo : memList) {
				String memId = aes256.decrypt(memVo.getMem_id());
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "\t" + memName + 
						"\t" + memTel + "\t" + memAddr);
			}
		}
			
		System.out.println("--------------------------------------");
	}
	
	// 회원 정보를 수정하는 메서드
	private void updateMember() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		AES256Util aes256 = new AES256Util();
		
		  System.out.print("수정할 아이디를 입력하세요 : ");
	         String memId = scan.nextLine();
	      
	         int count = service.getMemberCount(aes256.encrypt(memId));
	         
	         if (count == 0) {
	            System.out.println("없는 사용자 ID입니다");
	            return;
	         } 
	         
	         System.out.print("수정할 이름을 입력하세요 : ");
	         String memName = scan.nextLine();
	         
	         System.out.print("수정할 전화번호 입력하세요 : ");
	         String memTel = scan.nextLine();
	         
	         System.out.print("수정할 주소를 입력하세요 : ");
	         String memAddr = scan.nextLine();
	      
	         // 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
	         MemberVO memVo = new MemberVO();
	         
	         memVo.setMem_id(aes256.encrypt(memId));
	         
	         memVo.setMem_name(memName);
	         memVo.setMem_tel(memTel);
	         memVo.setMem_addr(memAddr);
	         
	         
	         // Service 객체의 데이터를 수정하는 메서드 호출하기
	         int cnt = service.updateMember(memVo);
	         System.out.println();
	         if(cnt>0) {
	            System.out.println("회원 정보 수정 완료!");
	         }else {
	            System.out.println("회원 정보 수정 실패!");
	         }
	         System.out.println();
	      
	   }

			
		
	
	
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() throws Exception {
		AES256Util aes256= new AES256Util();
		System.out.println("삭제 할 아이디를 입력하세요:");	
		String memId = scan.nextLine();
		
	
		
		  int count = service.getMemberCount(aes256.encrypt(memId));
        
        
        if (count == 0) {
           System.out.println("없는 사용자 ID입니다");
           return;
        } 
        
        int cnt = service.deleteMember(aes256.encrypt(memId));
        
        System.out.println();
        if(cnt>0) {
           System.out.println("회원 삭제 완료!");
        }else {
           System.out.println("회원 삭제 실패!");
        }
        System.out.println();
        
     
     
  }
			
	
	
	
	   private void insert() throws Exception {
	      AES256Util aes256 = new AES256Util();
	      
	         String memId = "";
	         while (true) {
	            System.out.print("아이디를 입력하세요 : ");
	            memId = scan.nextLine();

	            if (memId.equals("")) {
	               System.out.println("빈 내용은 입력할수 없습니다.");
	               System.out.println("다시 입력하세요");
	               continue;
	            }
	            
	            int count = service.getMemberCount(memId);

	            if (count == 1) {
	               System.out.println("이미 등록된 이름입니다.");
	               System.out.println("다시 입력하세요");
	            } else {
	               break;
	            }

	         }
	         
	         System.out.print("이름을 입력하세요 : ");
	         String memName = scan.nextLine();
	         
	         System.out.print("전화번호 입력하세요 : ");
	         String memTel = scan.nextLine();
	         
	         System.out.print("주소를 입력하세요 : ");
	         String memAddr = scan.nextLine();
	         
	         //입력한 회원 정보를 저장할 MemberVO객체 생성
	         MemberVO memVo = new MemberVO();
	         
	         // 입력한 데이터를 MemberVo 객체에 저장한다.
	         String str = aes256.encrypt(memId);
	         memVo.setMem_id(str);
	         memVo.setMem_name(memName);
	         memVo.setMem_tel(memTel);
	         memVo.setMem_addr(memAddr);
	         
	         // Service객체에서 회원 정보를 추가하는 메서드 호출하기
	         int cnt = service.insertMember(memVo);
	         
	         System.out.println();
	         if(cnt>0) {
	            System.out.println("회원 가입 성공!");
	         }else {
	            System.out.println("회원 가입 실패!");
	         }
	         System.out.println();
	         
	      
	      }
	   
	
	
	
}
