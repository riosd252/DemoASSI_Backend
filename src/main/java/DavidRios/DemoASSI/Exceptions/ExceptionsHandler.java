package DavidRios.DemoASSI.Exceptions;

import DavidRios.DemoASSI.DTOs.ErrorListPayload;
import DavidRios.DemoASSI.DTOs.ErrorPayload;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(TableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleTableExceptions(TableException ex) {
            return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(BadRequestException ex) {
        if (ex.getErrorList() != null) {
            List<String> errorList = ex.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            return new ErrorListPayload(ex.getMessage(), LocalDateTime.now(), errorList);
        } else {
            return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorPayload handleUnauthorized(UnauthorizedException ex) {
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorPayload handleAccessDenied(AccessDeniedException ex) {
        return new ErrorPayload("Restricted endpoint.", LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleNotFound(NotFoundException ex) {
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorPayload handleGenericErrors(Exception ex) {
        return new ErrorPayload("Our bad, sorry! Here's a cup of tea for you to taste while we take care of this 🍵", LocalDateTime.now());
    }
}
