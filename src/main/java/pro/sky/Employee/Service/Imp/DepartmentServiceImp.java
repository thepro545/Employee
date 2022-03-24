package pro.sky.Employee.Service.Imp;

import org.springframework.stereotype.Service;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Errors.EmployeeNotFoundException;
import pro.sky.Employee.Service.DepartmentService;
import pro.sky.Employee.Service.EmployeeService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMinSalary(int department){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee getMaxSalary(int department){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Collection<Employee> getEmployeesByDepartment(int department){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupByDepartment(){
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
