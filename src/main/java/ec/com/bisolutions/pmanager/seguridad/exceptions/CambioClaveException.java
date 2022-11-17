package ec.com.bisolutions.pmanager.seguridad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CambioClaveException extends RuntimeException {
  public CambioClaveException(String message) {
    super(message);
  }
}
