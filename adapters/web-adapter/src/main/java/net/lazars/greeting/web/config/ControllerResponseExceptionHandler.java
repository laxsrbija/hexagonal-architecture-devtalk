package net.lazars.greeting.web.config;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NoSuchElementException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ResponseEntity<Object> handleNoSuchElementException() {
    return ResponseEntity.notFound().build();
  }
}
