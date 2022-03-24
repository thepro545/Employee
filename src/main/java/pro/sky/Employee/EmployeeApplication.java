package pro.sky.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Service.DepartmentService;
import pro.sky.Employee.Service.Imp.DepartmentServiceImp;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}

