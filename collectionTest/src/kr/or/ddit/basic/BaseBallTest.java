package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
  문제) Set과 List 를 이용하여 숫자 야구 게임 프로그램을 작성하시오. 
              컴퓨터의 숫자는 난수를 이용하여 구한다.
       (스트라이크는 s, 볼은 B로 나타난다.)
       
 예시) 컴퓨터의 난수 ==> 9 5 7 
 
         실행예시) 
         숫자입력 => 3 5 8      
     3 5 8 ==> 1S OB
         숫자입력 => 7 8 9
     7 8 9 ==> 0S 2B
         숫자입력 ==> 9 7 5  
     9 7 5 ==> 1S 2B
         숫자입력 ==> 9 5 7         
     9 5 7 ==> 3S 0B
     
         축하합니다.
         당신은 4번째만에 맞췄습니다. 
 */
public class BaseBallTest {

static BaseBallTest bbt = new BaseBallTest();
HashSet<Integer> hashRan = new HashSet<>();
ArrayList<Integer> arrayRan;
ArrayList<Integer> insertNum = new ArrayList<>();

	public static void main(String[] args) {

		// 컴퓨터 숫자 
		bbt.makeNumber();
		// 내 번호 입력
		bbt.insertNumber();
		// 비교
		bbt.equalsNumber();

	}

	private void equalsNumber() {
		int count = 0;

		while (true){
			count++;
			int strike=0;
			int ball=0;

			for (int i= 0; i < insertNum.size(); i++){
				for (int j= 0; j < arrayRan.size(); j++){
					if (insertNum.get(i) == arrayRan.get(j) && i== j){
						strike++;
					} else if (insertNum.get(i) == arrayRan.get(j) && i!= j){
						ball++;
					}
				}
			}
			System.out.println(strike + "S " + ball + "B");
			if (strike == 3) {
				System.out.println("정답입니다. 당신은 " + count + "번 만에 맞췄습니다");

				System.exit(0);
			} else {
				System.out.println("틀렸습니다. 다시 입력해주세요");
				insertNum.clear();
				bbt.insertNumber();
			}
		}
	}

	private void insertNumber(){
		System.out.println("1~9까지 숫자를 3번 입력하세요. 단, 같은숫자 입력은 불가능 합니다.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			int num1 = Integer.parseInt(sc.nextLine());
			int num2 = Integer.parseInt(sc.nextLine());
			int num3 = Integer.parseInt(sc.nextLine());
			if (num1 < 1 || num1 > 9 || num2 < 1 || num2 > 9 || num3 < 1 || num3 > 9) {
				System.out.println("1부터 9까지 숫자만 입력해 주세요");
			} else if (num1 != num2 && num2 != num3 && num1 != num3) {
				insertNum.add(num1);
				insertNum.add(num2);
				insertNum.add(num3);
				break;
			}else{
		        System.out.println("같은 숫자를 입력했습니다. 다시 입력하세요");
			}
		}
	}

	private void makeNumber(){
		while (hashRan.size() < 3){
			hashRan.add((int) (Math.random() * 9 + 1));
		}
		arrayRan = new ArrayList<>(hashRan);
	}

}




