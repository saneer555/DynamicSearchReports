package insurenceMain.Services;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import insurenceMain.Binding.ReportsRequest;
import insurenceMain.Binding.ReportsResponse;
import insurenceMain.Entity.Repots;
import insurenceMain.Repository.ReportsRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServicesImpl implements ReportsServices{

	@Autowired
	private ReportsRepository reportsRepo;

	@Override
	public List<ReportsResponse> dynamicSearch(ReportsRequest req) {

		Repots dynamicSearch = new Repots();
		String planeName = req.getPlaneName();
		
		if(planeName !=null && !planeName.isEmpty()) {
			dynamicSearch.setPlaneName(planeName);
		}

		String planeStatus = req.getPlaneStatus();

		if(planeStatus != null && !planeStatus.isEmpty()) {
			dynamicSearch.setPlaneStatus(planeStatus);

		}

		LocalDate planstartDate = req.getPlanstartDate();

		if(planstartDate!=null) {
			dynamicSearch.setPlanStartDate(planstartDate);
		}
		LocalDate planEndDate = req.getPlanEndDate();


		if(planEndDate !=null) {
			dynamicSearch.setPlanEndDate(planEndDate);

		}
		Example<Repots> of = Example.of(dynamicSearch);

		List<Repots> list = reportsRepo.findAll(of);
		List<ReportsResponse> reportsResponse = new ArrayList<>();
		for(Repots reports : list) {

			ReportsResponse response = new ReportsResponse();
			BeanUtils.copyProperties(reports, response);
			reportsResponse.add(response);
		}

		return reportsResponse;
	}

	@Override
	public List<String> findDistinctPlaneNames() {
		List<String> names = reportsRepo.findDistinctPlaneNames();
		
		return names;
	}

	@Override
	public List<String> findDistinctPlanStatuses() {
		List<String> planStatuses = reportsRepo.findDistinctPlanStatuses();
		return planStatuses;
	}

	@Override
	public void downlordExcel(HttpServletResponse excel) {
		List<Repots> all = reportsRepo.findAll();
		 XSSFWorkbook workbook = new XSSFWorkbook();
         XSSFSheet sheet = workbook.createSheet("Reports");
         XSSFRow headerRow = sheet.createRow(0);
         
         headerRow.createCell(0).setCellValue("S.NO");
         headerRow.createCell(1).setCellValue("Name");
         headerRow.createCell(2).setCellValue("Mobile");
         headerRow.createCell(3).setCellValue("Gender");
         headerRow.createCell(4).setCellValue("SSN");
         
         int i = 1;
         for (Repots c : all) {
             XSSFRow dataRow = sheet.createRow(i);
             dataRow.createCell(0).setCellValue(c.getReportsId());
             dataRow.createCell(1).setCellValue(c.getUserName());
             dataRow.createCell(2).setCellValue(c.getMobileNo());
             dataRow.createCell(3).setCellValue(c.getGender());
             dataRow.createCell(4).setCellValue(c.getSsn());

             i++;
         }
         ServletOutputStream outputStream;
		try {
			outputStream = excel.getOutputStream();
			 workbook.write(outputStream);
			 workbook.close();
			 outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 

	@Override
	public void downlordpdf(HttpServletResponse pdf) {
		
		List<Repots> list = reportsRepo.findAll();
		
		 Document document = new Document(PageSize.A4);
	        try {
				PdfWriter.getInstance(document, pdf.getOutputStream());
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Users", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p); 
	        
	        
	        PdfPTable table = new PdfPTable(5);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	        table.setSpacingBefore(10);
	        
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Name", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("E-mail", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("phoneNumber", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Gender", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("SSN", font));
	        table.addCell(cell); 
	        
	        
	        for(Repots  entity : list) {
	        	
	        	table.addCell(entity.getUserName());
	       
	            table.addCell(entity.getUserEmail());
	            
	            table.addCell(entity.getMobileNo());
	            table.addCell(entity.getGender());
	            table.addCell(entity.getSsn());
	        	
	        }
	        document.add(table);
	        document.close();
		
	}




}
