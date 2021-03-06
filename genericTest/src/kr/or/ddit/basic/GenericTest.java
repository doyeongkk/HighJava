package kr.or.ddit.basic;

/*
    - 제네릭을 사용하는 클래스를 만드는 방법 
    형식)
    class 클랫명<제네릭타입글자>{
              제네릭타입글자 변수명;     //변수 선언에 제네릭을 사용할 경우 
       ...
       
       제네릭타입글자 메서드명(매개변수들....){ //메서드의 반환값으로 제네릭을 사용할 경우 
      	실행문들.....
        
        return 값;
      } 
      .....
      
      반환값타입 메서드명(제네릭타입글자 변수명){ //메서드의 매개변수에 제네릭을 사용할 경우 
            실행문들 ....
        
      return 값;
        
      }
      
      
    }
    //제네릭타입글자로 사용하는 것들 
      T ==> Type
      K ==> key
      V ==> value
      E ==> Element
    
 */

// 제네릭을 사용하는 class
class GenericClass<T>{
	private T val;
	
	public void setVal(T val) {
		this.val = val;
		
	}
	public T getVal() {
		return val;
	}
}



//제네릭을 사용하지 않는 class
class NoneGenericClass{
	private Object val;
	
	public void setVal(Object val) {
		this.val = val;
	}
	
	public Object getVal() {
		return val;
		
	}
}





public class GenericTest {

	public static void main(String[] args) {
		NoneGenericClass ng1 = new NoneGenericClass();
		ng1.setVal("가나다라");
		
		NoneGenericClass ng2 = new NoneGenericClass();
        ng2.setVal(100);
        
        
        
        String str1 = (String)ng1.getVal(); // 형변환을 반드시 해주어야 한다. 
        System.out.println("문자열 반환값 str = " + str1);
        
        Integer num1 = (Integer)ng2.getVal();
        System.out.println("정수형 반환값 num1 = " + num1);
        System.out.println("-------------------------");
        System.out.println();
        
        GenericClass<String> gc1 = new GenericClass();
        GenericClass<Integer> gc2 = new GenericClass();
        
        gc1.setVal("우리나라");
        //gc1.setVal(100);  - 오류   //제네릭과 다른 데이터를 사용하면 오류가 난다.
        gc2.setVal(200);
        //gc2.setVal("가나다");   - 오류  //제네릭과 다른 데이터를 사용하면 오류가 난다.
        
        String str2 = gc1.getVal();
        int num2 = gc2.getVal();
        
        System.out.println("제네릭 문자열 반환값 str2 = " + str2);
        System.out.println("제네릭 정수형 반환값 num2 = " + num2);
	}

}


















