package insurenceMain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import insurenceMain.Binding.ReportsRequest;
import insurenceMain.Binding.ReportsResponse;
import insurenceMain.Services.ReportsServices;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportsController {
	
	@Autowired
	private ReportsServices reportsServices;
	
	@GetMapping("/planeNames")
	public ResponseEntity<List<String> >findDistinctPlaneNames(){
		
		List<String> planeNames = reportsServices.findDistinctPlaneNames();
		
		return new ResponseEntity<>(planeNames,HttpStatus.OK);
	}
	
	@GetMapping("/planStatus")
	public ResponseEntity<List<String> >findDistinctPlanStatuses(){
		
		List<String> planStatuses = reportsServices.findDistinctPlanStatuses();
		return new ResponseEntity<>(planStatuses,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<ReportsResponse>>search(@RequestBody ReportsRequest request){
		
		List<ReportsResponse> search = reportsServices.dynamicSearch(request);
		
		return new ResponseEntity<>(search,HttpStatus.OK);
		
	}
	
	@GetMapping("/excel")
	public void downlordExcel(HttpServletResponse response){
		
		response.setContentType("application/actet-stream");
		
		String headerKey ="Content-Disposition";
		String headerValue = "attachment;filename=data.xlsx";
		response.setHeader(headerKey, headerValue);
		
		reportsServices.downlordExcel(response);
	}
	
	@GetMapping("/pdf")
	public void downlordPdf(HttpServletResponse response) {

		response.setContentType("application/pdf");
		
		String headerKey ="Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		
		
		
		reportsServices.downlordpdf(response);
		
	}
	
	

}
