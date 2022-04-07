import org.junit.jupiter.api.Test;
import pro.sky.Employee.Data.Employee;
import pro.sky.Employee.Service.Imp.EmployeeServiceImp;

import static java.util.Collections.emptyList;
import static pro.sky.Employee.constants.EmployeeTestData.*;
import static org.junit.jupiter.api.Assertions.*;

import pro.sky.Employee.Errors.EmployeeExistException;
import pro.sky.Employee.Errors.EmployeeNotFoundException;

import java.util.Collection;
import java.util.List;

public class EmployeeServiceTest {

    private final EmployeeServiceImp out = new EmployeeServiceImp();

    @Test
    public void checkThrowsEmployeeExistExceptionWithAddEmployee() {
        Employee exist = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        assertTrue(out.getAllEmployees().contains(exist));
        assertThrows(EmployeeExistException.class, () -> out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT));
    }

    @Test
    public void checkEmployeeServiceAddEmployeeIfDontExist() {
        Employee exist = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        assertEquals(0, out.getAllEmployees().size());
        assertFalse(out.getAllEmployees().contains(exist));
        Employee actual = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        assertEquals(exist, actual);
        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().contains(exist));
    }

    @Test
    public void checkEmployeeServiceFindEmployeeWhenExist() {
        Employee exist = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        assertEquals(exist, out.findEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void checkThrowsEmployeeNotFoundExceptionFindEmployeeWhenDontExist() {
        assertEquals(0, out.getAllEmployees().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void checkThrowsEmployeeNotFoundExceptionRemoveEmployeeWhenDontExist() {
        assertTrue(out.getAllEmployees().isEmpty());
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void checkEmployeeServiceRemoveEmployeeWhenExist() {
        Employee exist = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().contains(exist));
        Employee actual = out.removeEmployee(FIRST_NAME, LAST_NAME);
        assertEquals(exist,actual);
        assertTrue(out.getAllEmployees().isEmpty());
        assertFalse(out.getAllEmployees().contains(exist));
    }

    @Test
    public void checkReturnEmptyCollectionWhenDontExist() {
        assertIterableEquals(emptyList(), out.getAllEmployees());
    }

    @Test
    public void checkReturnCollectionWhenExist() {
        Employee employee1 = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee employee2 = out.addEmployee(FIRST_NAME2, LAST_NAME2, SALARY2, DEPARTMENT2);
        Collection<Employee> exist = List.of(employee1, employee2);
        Collection<Employee> actual = out.getAllEmployees();
        assertIterableEquals(exist,actual);
    }
}
