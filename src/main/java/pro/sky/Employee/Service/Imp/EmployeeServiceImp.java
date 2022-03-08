package pro.sky.Employee.Service.Imp;

import org.springframework.stereotype.Service;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Errors.EmployeeExistException;
import pro.sky.Employee.Errors.EmployeeNotFoundException;
import pro.sky.Employee.Service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final HashMap<String, Employee> employees;

    public EmployeeServiceImp() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        String key = getFio(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeExistException("Сотрудник уже существует");
        }
        Employee newEmployee = employees.put(key, new Employee(firstName, lastName));
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = getFio(firstName, lastName);
        if (employees.remove(key)==null) {
            throw new EmployeeNotFoundException("Сотрудник не существует");
        }
        Employee employee = new Employee(firstName, lastName);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getFio(firstName, lastName);
        Employee employee = employees.get(key);

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не существует");
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public String getFio(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
