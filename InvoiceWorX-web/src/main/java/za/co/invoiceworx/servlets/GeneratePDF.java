/*package za.co.invoiceworx.servlets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class GeneratePDF {

	public static void main(String[] args) {
		

		try {

            String k = "<html><body> This is my Project </body></html>";

            OutputStream file = new FileOutputStream(new File("E:\\Test.pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();

            document.add(new Paragraph(k));

            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

	}

}
*/