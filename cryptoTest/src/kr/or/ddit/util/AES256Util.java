package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.synth.SynthScrollBarUI;

// 양방향 암호화 알고리즘인 AES256 암호화 방식을 지원하는 클래스 
public class AES256Util {
	// 초기화 벡터(Initial Vector, IV) ==> IV는 암호문이 패턴화 되지 않도록 사용하는 데이터를 말한다.
	//    ==> 첫 블럭을 암호화할 때 사용되는 값 ==> CBC모드에서 사용된다. 
	//    ==> 암호화를 할 때 다른 랜덤 비트열을 이용하는 것이 보통이다. 
	
	//  초기화 벡터값이 저장될 변수
	private String iv;
	 
	 private Key keySpec;
	 
	 private static final String key = "a1b2c3d4e5f6g7h8"; // 암호화 키값 (16글자 이상)
	 
	 // 생성자
	 // 16자리의 키값을 이용하여 비밀키 객체를 생성한다. 
	 public AES256Util() throws UnsupportedEncodingException {
		 this.iv = key.substring(0, 16);
		 
		 byte[] keyBytes = new byte[16];
		 byte[] b = key.getBytes("utf-8");
		 int len = b.length;
		 if(len>keyBytes.length) {
			 len = keyBytes.length;
		 }
		 
		 System.arraycopy(b, 0, keyBytes, 0, len);
		 
		 // 비밀키 생성
		 SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		 this.keySpec = keySpec;
	 }
	 
	 // AES256방식으로 암호화하는 메서드
	 // str  ==> 암호화할 문자열
	 // 반환값 ==> 암호화된 문자열 
	 public String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		 // - Cipher 생성 및 초기화하기 
		 // "AES/CBC/PKCS5Padding" ==> "알고리즘/모드/패딩"
		 // CBC: Cipher Block Chaining Mode ==> 동일한 평문 블럭과 암호문 블럭 쌍이 발생하지
		 //                                     않도록 이전 단계의 암복화한 결과가 현 단계에 영향을 주는 운영 모드를 말한다. 
		 //										블록 암호화 운영모드 중 보안성이 가장 높은 암호화 방법으로 가장 많이 사용한다. 
		 //										암호화가 병렬적으로 처리되는 것이 아니라 순차적으로 수행되어야 한다는 단점이 있다. 
		 
		 // 패딩: Padding ==> 마지막 블럭이 블럭의 길이와 항상 딱 맞아 떨어지지 않을 수 있기 때문에
		 //					 부족한 길이만큼을 '0'으로 채우거나 임의의 비트들로 채워넣는것을 의미한다. 
		 
		 Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 
		 byte[] ivBytes = new byte[16];
         System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
         
         // 옵션 종류: ENCRYPT_MODE(암호화모드), DECRYPT_MODE(복호화모드)
         //          WRAP_MODE(암호키를 캡슐화), UNWRAP_MODE(암호키 캡슐화 해제)
         
         // 암호를 옵션 종류에 맞게 초기화 한다.
         c.init(Cipher.ENCRYPT_MODE,  keySpec, new IvParameterSpec(ivBytes));
         byte[] encrypted = c.doFinal(str.getBytes("utf-8")); // 암호문 생성 
         
         String enStr = Base64.getEncoder().encodeToString(encrypted);
         
         return enStr;
         
	 }
	 
	 // AES256방식으로 암호화된 문자열을 복호화하는 메서드
	 // str ==> 복호화할 암호화된 문자열
	 // 반환값 ==> 복호화된 문자열 
	 public String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		 Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 
		 byte[] ivBytes = new byte[16];
      System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
      
      c.init(Cipher.DECRYPT_MODE,keySpec, new IvParameterSpec(ivBytes));
      
      byte[] byteStr = Base64.getDecoder().decode(str);// 암호화 된 데이터를 원래
      
      return new String(c.doFinal(byteStr), "utf-8");
	 }
}















