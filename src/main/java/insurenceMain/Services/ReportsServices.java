package insurenceMain.Services;

import java.util.List;

import insurenceMain.Binding.ReportsRequest;
import insurenceMain.Binding.ReportsResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportsServices {
	
	public List<ReportsResponse>dynamicSearch(ReportsRequest req);
	
	 List<String> findDistinctPlaneNames();
	 
	 List<String> findDistinctPlanStatuses();
	 
	 public void downlordExcel(HttpServletResponse excel);
	 
	 public void downlordpdf(HttpServletResponse pdf);

}
