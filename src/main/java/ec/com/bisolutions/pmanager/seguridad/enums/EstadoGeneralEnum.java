package ec.com.bisolutions.pmanager.seguridad.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoGeneralEnum {
  ACTIVO("ACT", "Activo"),
  INACTIVO("INA", "Inactivo");

  private final String value;
  private final String text;
}
