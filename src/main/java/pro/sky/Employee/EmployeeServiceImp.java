package pro.sky.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final Employee[] employees;
    private int size;

    public EmployeeServiceImp() {
        employees = new Employee[3];
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (size == employees.length) {
            throw new EmployeeBookIsFullException();
        }
        int index = indexOf(newEmployee);

        if (index != -1) {
            throw new EmployeeExistException();
        }
        employees[size++] = newEmployee;
        return newEmployee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        int index = indexOf(newEmployee);
        if (index != -1) {
            Employee result = employees[index];
            System.arraycopy(employees, index + 1, employees, index, size - index - 1);
            employees[--size] = null;
            return result;
        } else {
            throw new EmployeeNotFoundException();
        }
    }


    @Override
    public Employee find(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        int index = indexOf(newEmployee);

        if (index != -1) {
            return employees[index];
        } else {
            throw new EmployeeNotFoundException();
        }
    }


    public int indexOf(Employee employee) {
        for (int i = 0; i < size; i++) {
            if (employees[i].equals(employee)) {
                return i;
            }
        }
        return -1;
    }
}
