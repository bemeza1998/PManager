package ec.com.bisolutions.pmanager.seguridad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ModificarException extends RuntimeException {
  public ModificarException(String message) {
    super(message);
  }
}
