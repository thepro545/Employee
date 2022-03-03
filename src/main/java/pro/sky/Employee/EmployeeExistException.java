package pro.sky.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeExistException extends RuntimeException {
    public EmployeeExistException() {
        super("Сотрудник уже существует");
    }

    public EmployeeExistException(String message) {
        super(message);
    }

    public EmployeeExistException(Throwable cause) {
        super(cause);
    }

    public EmployeeExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

