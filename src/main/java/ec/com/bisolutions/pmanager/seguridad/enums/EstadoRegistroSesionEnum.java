package ec.com.bisolutions.pmanager.seguridad.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoRegistroSesionEnum {
  SATISFACTORIO("SAT", "Satisfactorio"),
  FALLIDO("FAL", "Fallido");

  private final String valor;
  private final String texto;
}
