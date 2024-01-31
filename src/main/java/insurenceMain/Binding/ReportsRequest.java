package insurenceMain.Binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportsRequest {
	
	private String planeName;
	
	private String planeStatus;
	
	private LocalDate planstartDate;
	
	private LocalDate planEndDate;

}
