package pro.sky.Employee.Service.Imp;

import org.springframework.stereotype.Service;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Errors.EmployeeExistException;
import pro.sky.Employee.Errors.EmployeeNotFoundException;
import pro.sky.Employee.Service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final HashMap<String, Employee> employees;

    public EmployeeServiceImp() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        String key = getFio(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new EmployeeExistException("Сотрудник уже существует");
        }
        Employee newEmployee = employees.put(key, new Employee(firstName, lastName, salary, department ));
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

//        getAllEmployees; // Задание 8 A
//        float fullSalary = getFullSalary(employeeStorage); // Задание 8 B
//        System.out.println("Сумма затрат на зарплаты в месяц = " + fullSalary + " рублей"); // Задание 8 B
//        getMinSalary(employeeStorage); // Задание 8 C
//        getMaxSalary(employeeStorage); // Задание 8 D
//        getAvgSalary(fullSalary, employeeStorage); // Задание 8 E
//        getFullFio(employeeStorage); // Задание 8 F


    public int getFullSalary(Employee employee) {
        return IntStream.of(employee.getSalary())
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

    // Тут проблемка. Не могу понять
    public List<String> getAvgSalary(Employee employee) {
        List<Integer> numForAvgSalary = getAllEmployees().stream()
                .map(Employee::getSalary)
                .collect(Collectors.toList());
        return IntStream.of(numForAvgSalary)
                .average()
                .getAsDouble();
    }

    public List<String> getFullFio() {
        System.out.println("Список всех сотрудников: ");
        return getAllEmployees().stream()
                .map(Employee::getFio)
                .collect(Collectors.toList());
    }
}
