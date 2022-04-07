package pro.sky.Employee.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameException extends IllegalArgumentException{
    public InvalidNameException() {
        super("Ошибка имени: ");
    }

    public InvalidNameException(String name) {
        super("Ошибка имени: " + name);
    }
}
