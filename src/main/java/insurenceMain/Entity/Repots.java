package insurenceMain.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SEARCH_REPORTS")
public class Repots {
	
	@Id
	private Integer reportsId;
	
	private String planeName;
	
	private String planeStatus;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	private String userName;
	
	private String userEmail;
	
	private String mobileNo;
	
	private String gender;
	
	private String ssn;
	
	private String createdBy;
	
	private String updateBy;
	
	private LocalDate createdDate;
	
	private LocalDate updatedDate;
	
	

}
