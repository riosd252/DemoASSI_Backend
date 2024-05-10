package DavidRios.DemoASSI.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class TableException extends RuntimeException{

    private List<ObjectError> errors;
    public TableException (String message) {
        super(message);
    }

    public TableException (List<ObjectError> errors) {
        super("The following errors where encountered:");
        this.errors = errors;
    }
}
