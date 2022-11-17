package ec.com.bisolutions.pmanager.seguridad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CreateException extends RuntimeException {
  public CreateException(String message) {
    super(message);
  }
}
