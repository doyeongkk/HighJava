package web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// uri_mapping.properties파일에 설정된 
// URI에 맞는 Action객체를 구해와서 인스턴스를 생성해서 반환하는
// 역할을 수행하는 클래스
public class URIActionMapper {
	// uri_mapping.properties 파일의 내용을 읽어와
	// URI요청은 키값으로 Action객체명은 value값으로 저장될 Map객체 생성
	private static Map<String, String> actionMap = new HashMap<String, String>();
	
	// static 초기화 블록
	static {
		// properties파일을 읽어와 Bundle객체를 생성한다.
		ResourceBundle rb = ResourceBundle.getBundle("web.uri_mapping");
		
		// Bundle객체의 key값 가져오기
		Enumeration<String> en = rb.getKeys();	
		while(en.hasMoreElements()) {			// 키 값 개수만큼 반복처리
			String key = en.nextElement();		// 키 값 구하기(URI요청값)
			String value = rb.getString(key);	// value 값(실행항 Action클래스명)
			
			actionMap.put(key, value);			// key값과 value값을 Map에 저장
		}
		
	}	// static 초기화 블록 끝..

	// 매개값으로 주어진 uri에 맞는 Action객체를 
	public static IAction getAction(String uri) {
		
		IAction action = null;
		
		if(actionMap.containsKey(uri)) {
			try {
				
				// 문자열로 된 Action클래스의 이름을 이용하여 해당 클래스를 메모리에 로딩한다.
				Class cls = Class.forName(actionMap.get(uri));
				
				// 메모리에 로딩된 Action클래스를 '인스턴스화'한다. ==> 즉, 객체를 생성한다.
				action = (IAction) cls.newInstance();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return action;
	}
}
