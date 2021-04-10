package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;



//문제1) 5명의 사람 이름을 입력 받아서 ArrayList에 저장한 후에 이들 중에서 
//     '김'씨 성을 가진 사람의 이름을 모두 출력하시오.
//      (입력은 Scanner객체를 이용한다.)

//문제2) 5명의 별명을 입력 받아서 ArrayList에저장한 후에 이들 중에서
//      별명의 길이가 제일 긴 별명을 출력하시오.
//        (단, 각 별명의 길이는 모두 다르게 입력한다.)

//문제3) 문제 2에서 별명의 길이가 같은 것이 여러개 있을 경우를 처리하시오.
//      (즉, 제일 긴 별명 모두 출력한다.)
public class ListTest2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names = new ArrayList<>();
		String name;
//		
//		for(int i=0; i<5; i++) {
//			System.out.println("이름을 입력하세요");
//			name = sc.nextLine();
//			names.add(name);
//	
//   
//	}
//		System.out.println(names);
//		for(int i=0; i<names.size(); i++) {
//			if(names.get(i). charAt(0) =='김') {
//				System.out.println(names.get(i));
//			}
//			
//		}	
			

		for(int i=0; i<5; i++) {
			System.out.println("별명을 입력하세요");
			name = sc.nextLine();
			names.add(name);
		}
		System.out.println(names);
		
		
//		for(int i= 0; i <names.size();i++) {
//		  if(names.get(0).length() < names.get(i).length()) {
//			  names.set(0, names.get(i));
//		  }	
//	
//		}
//		System.out.println(names.get(0));

		int max = names.get(0).length();
		
		for(int i= 0; i<names.size();i++) {
			if(names.get(0).length() < names.get(i).length()) {
				max= names.get(i).length();
			}
		}
		
		for(int i=0; i<names.size(); i ++) {
			if(names.get(i).length()==max) {
				System.out.print(names.get(i)+"\t");
			}
		}
		
		
		}
		
	}









