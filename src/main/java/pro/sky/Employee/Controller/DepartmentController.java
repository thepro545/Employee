package pro.sky.Employee.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employee.Service.DepartmentService;
import pro.sky.Employee.Data.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee getMaxSalary(@RequestParam("department") int department) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee getMinSalary(@RequestParam("department") int department) {
        return departmentService.getMinSalary(department);
    }

    @GetMapping(path = "/all", params = "department")
    public Collection<Employee> getEmployeesByDepartment(@RequestParam("department") int department) {
        return departmentService.getEmployeesByDepartment(department);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesGroupByDepartment() {
        return departmentService.getAllEmployeesGroupByDepartment();
    }
}
