package pro.sky.Employee.Service;
import pro.sky.Employee.Data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getMinSalary(int department);
    Employee getMaxSalary(int department);
    Collection<Employee> getEmployeesByDepartment(int department);
    Map<Integer, List<Employee>> getAllEmployeesGroupByDepartment();
}
