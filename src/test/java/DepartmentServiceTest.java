import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Employee.Service.DepartmentService;
import pro.sky.Employee.Service.EmployeeService;
import pro.sky.Employee.Errors.EmployeeNotFoundException;

import static pro.sky.Employee.constants.EmployeeTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static java.util.Collections.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService out;

    @Test
    public void checkFindEmployeeWithMaxSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEE_BOTH);
        assertEquals(EMPLOYEE_MAX_SALARY, out.getMaxSalary(DEPARTMENT));
    }

    @Test
    public void checkFindEmployeeWithMixSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEE_BOTH);
        assertEquals(EMPLOYEE_MIN_SALARY, out.getMinSalary(DEPARTMENT));
    }

    @Test
    public void checkThrowsEmployeeNotFoundExceptionFindEmployeeWithMaxSalaryByDepartmentIsEmptyCollection() {
        when(employeeService.getAllEmployees()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalary(DEPARTMENT));
    }
    // Тоже самое с MinSalary
    @Test
    public void checkThrowsEmployeeNotFoundExceptionFindEmployeeWithMaxSalaryByDepartmentIsWhenDepartmentEmpty() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEE_BOTH);
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalary(DEPARTMENT_NOT_EXIST));
    }
    // Тоже самое с MinSalary
    @Test
    public void checkAllEmployeeByDepWhenExist() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEE_BOTH);
        assertEquals(DEPARTMENT_MAP, out.getEmployeesByDepartment(DEPARTMENT));
    }

    @Test
    public void checkFindEmployeeByDepWhenDepDontExist() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEE_BOTH);
        assertEquals(emptyMap(), out.getEmployeesByDepartment(DEPARTMENT_NOT_EXIST));
    }

}
