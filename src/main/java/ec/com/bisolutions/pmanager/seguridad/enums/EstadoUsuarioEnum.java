package ec.com.bisolutions.pmanager.seguridad.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoUsuarioEnum {
  ACTIVO("ACT", "Activo"),
  INACTIVO("INA", "Inactivo"),
  BLOQUEADO("BLO", "Bloqueado");

  private final String value;
  private final String text;
}
