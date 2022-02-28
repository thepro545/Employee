package pro.sky.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeBookIsFullException extends RuntimeException{

    public EmployeeBookIsFullException(){
        super("Список переполнен");
    }
    public EmployeeBookIsFullException(String message){
        super(message);
    }
    public EmployeeBookIsFullException(Throwable cause){
        super(cause);
    }
    public EmployeeBookIsFullException(String message, Throwable cause){
        super(message, cause);
    }
}
