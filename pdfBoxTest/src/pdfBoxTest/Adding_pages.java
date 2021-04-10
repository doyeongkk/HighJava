package pdfBoxTest;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Adding_pages {

	public static void main(String[] args) throws IOException {
		//문서 객체 생성
	PDDocument document = new PDDocument();
	    // 빈페이지 생성
	for (int i = 0; i <10; i ++) {
		PDPage blankPage = new PDPage();
		// 빈페이지 문서에 추가 
		document.addPage(blankPage);
	}

	   document.save("C:/Users/pc-10/Desktop/my_doc.pdf");
	   System.out.println("출력완료");
	   
	   // 문서 닫기 - 닫지 않고 하면 파일 손상의 우려가 있다. 
	   document.close();
	
	}

}
