package pro.sky.Employee.constants;

import pro.sky.Employee.Data.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeTestData {
    public static final String FIRST_NAME = "Ivan";
    public static final String FIRST_NAME2 = "Oleg";
    public static final String LAST_NAME = "Ivanov";
    public static final String LAST_NAME2 = "Petrov";
    public static final int SALARY = 500;
    public static final int SALARY2 = 1000;
    public static final int DEPARTMENT = 1;
    public static final int DEPARTMENT2 = 2;
    public static final int DEPARTMENT_NOT_EXIST = 5;
    public static final Employee EMPLOYEE_MIN_SALARY = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
    public static final Employee EMPLOYEE_MAX_SALARY = new Employee(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
    public static final Set<Employee> EMPLOYEE_FIRST = Set.of(EMPLOYEE_MIN_SALARY);
    public static final Set<Employee> EMPLOYEE_SECOND = Set.of(EMPLOYEE_MAX_SALARY);
    public static final Set<Employee> EMPLOYEE_BOTH = Set.of(EMPLOYEE_MAX_SALARY, EMPLOYEE_MIN_SALARY);

    public static final Map<Integer, List<Employee>> DEPARTMENT_MAP = EMPLOYEE_BOTH.stream()
            .collect(groupingBy(Employee::getDepartment));

}
