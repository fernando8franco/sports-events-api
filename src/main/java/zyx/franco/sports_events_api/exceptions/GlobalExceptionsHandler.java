package zyx.franco.sports_events_api.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (!(ex.getCause() instanceof InvalidFormatException ifx))
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));

        if (!(ifx.getTargetType() != null && ifx.getTargetType().isEnum()))
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));

        return ResponseEntity.badRequest().body(Collections.singletonMap(
                "category",
                String.format("Invalid enum value: '%s'. The value must be one of: %s.",
                        ifx.getValue(), Arrays.toString(ifx.getTargetType().getEnumConstants()))
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(InvalidTeamSizeException.class)
    public ResponseEntity<?> handleInvalidTeamSizeException(InvalidTeamSizeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String exMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        Map<String,String> duplicated = new HashMap<>();
        String key = "Unknown key";
        String val = "Unknown value";

        if (exMessage != null) {
            Pattern pattern = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\)");
            Matcher matcher = pattern.matcher(exMessage);
            if (matcher.find()) {
                key = matcher.group(1);
                val = matcher.group(2);
            }
        }

        duplicated.put("key", key);
        duplicated.put("value", val);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("duplicated", duplicated));
    }
}
