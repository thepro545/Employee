package pro.sky.Employee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final List<Employee> employees;

    public EmployeeServiceImp() {
        employees = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeExistException();
        }
        employees.add(newEmployee);

        return newEmployee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (!employees.remove(newEmployee)) {
            throw new EmployeeNotFoundException();
        }
        return newEmployee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!employees.contains(newEmployee)) {
            throw new EmployeeNotFoundException();
        }
        return newEmployee;
    }

    @Override
    public List<Employee> getAll(){
        return List.copyOf(employees);
    }
}
