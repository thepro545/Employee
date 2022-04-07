package pro.sky.Employee.Service;

import pro.sky.Employee.Data.Employee;

import javax.naming.InvalidNameException;
import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Collection <Employee> getAllEmployees();
}
