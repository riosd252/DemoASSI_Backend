package DavidRios.DemoASSI.DTOs;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorListPayload extends ErrorPayload {
    private final List<String> errorList;

    public ErrorListPayload (String message, LocalDateTime timeStamp, List<String> errorList) {
        super(message, timeStamp);
        this.errorList = errorList;
    }
}