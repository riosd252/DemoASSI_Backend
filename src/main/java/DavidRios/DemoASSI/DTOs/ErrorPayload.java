package DavidRios.DemoASSI.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorPayload {
    private String message;
    private LocalDateTime timeStamp;
}
