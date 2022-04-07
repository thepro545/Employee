package pro.sky.Employee.Service.Imp;

import org.springframework.stereotype.Service;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Errors.EmployeeExistException;
import pro.sky.Employee.Errors.EmployeeNotFoundException;
import pro.sky.Employee.Service.EmployeeService;

import pro.sky.Employee.Errors.InvalidNameException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final HashMap<String, Employee> employees;

    public EmployeeServiceImp() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        String key = getFio(firstName, lastName);
        checkNames(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeExistException("Сотрудник уже существует");
        }
        Employee newEmployee = employees.put(key, new Employee(
                capitalize(firstName),
                capitalize(lastName),
                salary, department ));
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = getFio(firstName, lastName);
        if (employees.remove(key)==null) {
            throw new EmployeeNotFoundException("Сотрудник не существует");
        }
        Employee employee = new Employee(firstName, lastName, 0,0);
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
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public String getFio(String firstName, String lastName) {
        return firstName + " " + lastName;
    }


    public int getFullSalary() {
        return getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getMinSalary() {
        return getAllEmployees().stream()
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee getMaxSalary() {
        return getAllEmployees().stream()
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public OptionalDouble getAvgSalary(Employee employee) {
//        return getAllEmployees().stream()
//                .mapToInt(Employee::getSalary)
//                .sum()/ employees.size();
        return IntStream.of(employee.getSalary())
                .average();
    }

    public List<String> getFullFio() {
        System.out.println("Список всех сотрудников: ");
        return getAllEmployees().stream()
                .map(Employee::getFio)
                .collect(Collectors.toList());
    }

    private void checkNames(String... names){
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new InvalidNameException(name);
            }
        }
    }
}
