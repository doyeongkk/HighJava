package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
  문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
      Student클래스를 만든다. 
           이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다. 
      
      -이 Student객체는 List에 저장하여 관리한다. 
      
      1.-List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고, 
      2.총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작서하여 정렬된 결과를 출력하시오. 
  
 3.  (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.) -안해도됨
  */

public class ListSortTest3 {

	public static void main(String[] args) {
		ArrayList<Student> memList1 = new ArrayList<>();

		memList1.add(new Student(1, "김도영", 90, 90, 80));
		memList1.add(new Student(2, "유승종", 90, 80, 100));
		memList1.add(new Student(3, "전영헌", 100, 70, 70));
		memList1.add(new Student(4, "최명수", 100, 90, 100));
		memList1.add(new Student(5, "김보연", 90, 100, 70));

		System.out.println("학번의 오름차순.......");
		for (Student mem : memList1) {
			System.out.println(mem);
		}
		System.out.println("------------------");

		Collections.sort(memList1, new SortNumDesc1()); //총점의 역순으로 정렬 - 외부 정렬 기준으로 정렬한다. 

		for (Student mem : memList1) {
			System.out.println(mem);
		}
	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum = kor + eng + math;

	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;

	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum = kor + eng + math;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ getSum() + "]";
	}

	@Override
	public int compareTo(Student mem) {
		return Integer.compare(getNum(), mem.getNum());
	}
}

  //2. 외부 정렬 기준 클래스 작성 
	class SortNumDesc1 implements Comparator<Student> {
		@Override
		public int compare(Student mem1, Student mem2) {
			if (mem1.getSum() > mem2.getSum()) {
				return -1;
			} else if (mem1.getSum() == mem2.getSum()) {
				if (mem1.getName().compareTo(mem2.getName()) < 0) {
					return -1;
				} else if (mem1.getName().compareTo(mem2.getName()) == 0) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}

	}

	

   








