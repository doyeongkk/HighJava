package kr.or.ddit.basic;

import kr.or.ddit.basic.ThreadA;

/*
 
  wait(), notify()메서드를 이용한 예제
  (두 쓰레드가 번갈아 한번씩 실행하는 예제)
  
  - wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다. 
  
 
 */

public class ThreadTest19 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA tha= new ThreadA(workObj);
		ThreadB thb= new ThreadB(workObj);

		thb.start();
		tha.start();
		
	}

}

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethod1() {
		System.out.println("testMethod1()메서드 실행중....");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
	}
		public synchronized void testMethod2() {
			System.out.println("testMethod2()메서드에서 작업 실행중....");
			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
	
	}
}


//WorkObjcet의 testMethod1()메서드만 실행하는 쓰레드
class ThreadA extends Thread{
	private WorkObject WorkObj;

	public ThreadA(WorkObject workObj) {
		super();
		WorkObj = workObj;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			WorkObj.testMethod1();
		}
		// 마지막에 wait상태를 깨워준다.
		synchronized (WorkObj) {
			WorkObj.notify();
		}
		// 마지막에 wait상태를 깨워준다.
		synchronized (WorkObj) {
			WorkObj.notify();
	}
}

}

//WorkObjcet의 testMethod2()메서드만 실행하는 쓰레드
class ThreadB extends Thread{
	private WorkObject WorkObj;

	public ThreadB(WorkObject workObj) {
		super();
		WorkObj = workObj;
	}
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			WorkObj.testMethod2();
		}
	}
}






