import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class itext {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
          
		//pdf 작성자 생성자 생성 및 경로 설정
		String dest = "C:/Users/pc-10/Desktop/my_doc2.pdf";
		PdfWriter writer = new PdfWriter(dest);
		
		// 문서 생성
		PdfDocument pdfDoc = new PdfDocument(writer);
		
		// 페이지 추가
		pdfDoc.addNewPage();
		
		// 문서 생성
		Document document = new Document(pdfDoc);
		
		// 문서 닫기
		document.close();
		System.out.println("출력 완료");
		
	}

}
