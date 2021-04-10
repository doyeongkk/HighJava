package pdfBoxTest;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class pdfBoxTest {

	public static void main(String[] args) throws IOException {
		// PDF 문서 객체 생성
		PDDocument document = new PDDocument();
		
		//  문서 저장 ==> 저장 후 출력문으로 확인
		document.save("C:/Users/pc-10/Desktop/my_doc.pdf");
        System.out.println("출력확인");
		
        // 문서 닫기
        document.close();
		
	}

}

