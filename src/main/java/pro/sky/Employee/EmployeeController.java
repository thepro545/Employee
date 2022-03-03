package pro.sky.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee result = employeeService.add(firstName,lastName);
        return generateMessage(result, "Добавлен");
    }
    @GetMapping(path = "/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee result = employeeService.remove(firstName,lastName);
        return generateMessage(result, "Удален");
    }
    @GetMapping(path = "/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName,lastName);
    }
    @GetMapping(path = "/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    public String generateMessage(Employee employee, String status){
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(),
                employee.getLastName(),
                status
        );
    }
}


