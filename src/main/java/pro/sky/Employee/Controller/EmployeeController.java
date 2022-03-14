package pro.sky.Employee.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee result = employeeService.addEmployee(firstName,lastName);
        return generateMessage(result, "Добавлен");
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee result = employeeService.removeEmployee(firstName, lastName);
        return generateMessage(result, "Удален");
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/all")
    public Collection<Employee> getAll() {
        return employeeService.getAll();
    }

    public String generateMessage(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(),
                employee.getLastName(),
                status
        );
    }
}


