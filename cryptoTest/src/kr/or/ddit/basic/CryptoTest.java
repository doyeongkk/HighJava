package kr.or.ddit.basic;

import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.AES256Util;
import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		CryptoUtil crypto = new CryptoUtil();
		
		// String plainText = "Hello, World";
		String plainText = "안녕하세요, 반갑습니다.";
		
		System.out.println("MDS : " + crypto.md5(plainText));
		System.out.println("SHA-256 : " + crypto.sha256(plainText));
		System.out.println("SHA-512 : " + crypto.sha512(plainText));
		System.out.println("---------------------------------");
		
		AES256Util aes256 = new AES256Util();
		String str = aes256.encrypt(plainText);
		
		System.out.println("원래의 데이터 =>" + plainText);
		System.out.println("AES256 암호화 => " + str);
		System.out.println("암호화 문자열 길이:" + str.length());
		
		System.out.println("AES256 복호화 =>" + aes256.decrypt(str));
		
		System.out.println("-----------------------------------");
        String tmp = "";
        for(int i=0; i<=0; i++) {
        	for(int j=0; j<=9; j++) {
        		tmp += j;
        		str = aes256.encrypt(tmp);
        		System.out.println(i + " - tmp =>" + tmp);
        		System.out.println("암호화 =>" + str);
        		System.out.println("암호화길이 =>" + str.length());
        		String deStr = aes256.decrypt(str);
        		System.out.println("복호화 =>" + deStr );
        		System.out.println();
        	}
        }
	}

}
