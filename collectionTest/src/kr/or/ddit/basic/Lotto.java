package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {

	static Scanner sc = new Scanner(System.in);
	static HashSet<Integer> hashNum = new HashSet<>();
	static ArrayList<Integer> arrayNum;

	public static void main(String[] args) {
		start();
	}

	private static void start() {
		while (true) {
			System.out.println("");
			System.out.println("======================================");
			System.out.println("Lotto프로그램");
			System.out.println("--------------------------------------");
			System.out.println("1. Lotto구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("======================================");
			System.out.print("메뉴선택 :");
			int selectNum = 0;

			selectNum = Integer.parseInt(sc.nextLine());
			switch (selectNum) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(0);
				break;

			default:
				System.out.println("\n1,2번만 선택하세요");
				break;
			}

		}
	}

	private static void buyLotto() {
		System.out.println("\nLotto 구입 시작\n");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력: ");
		int money = Integer.parseInt(sc.nextLine());
		if (money < 1000) {
			System.out.println("\n입력 금액이 너무 적습니다. 로또번호 구입 실패!");
		} else if (money >= 101000) {
			System.out.println("\n입력 금액이 너무 많습니다. 로또번호 구입 실패!");
		} else {
			int change = viewLotto(money);
			System.out.println("\n받은 금액은 " + money + "원이고 거스롬돈은 " + change + "원 입니다.");
			System.out.println("아무키나 누르면 처음화면으로 돌아갑니다.");
			String a = sc.nextLine();
		}

	}

	private static int viewLotto(int money) {
		System.out.println("\n행운의 로또번호는 아래와 같습니다.");
		int count = 0;
		while (money >= 1000) {
			money -= 1000;
			count++;
			while (hashNum.size() < 6) {
				hashNum.add((int) (Math.random() * 45 + 1));
			}
			arrayNum = new ArrayList<>(hashNum);
			Collections.sort(arrayNum);

			System.out.println("로또번호" + count + " : " + arrayNum.get(0) + "," + arrayNum.get(1) + "," + arrayNum.get(2)
					+ "," + arrayNum.get(3) + "," + arrayNum.get(4) + "," + arrayNum.get(5));

			hashNum.clear();

		}
		return money;
	}

}
