package co.edu.unibague.agenda2.shared.infrastructure.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> exception(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
