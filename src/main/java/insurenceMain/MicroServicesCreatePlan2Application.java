package insurenceMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Search", version="1.0",description="saneer" ))
public class MicroServicesCreatePlan2Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesCreatePlan2Application.class, args);
	}

}
