package ec.com.bisolutions.pmanager.actividades.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoErrorQaEnum {
  CORREGIDO("COR", "Corregido"),
  POR_CORREGIR("PCO", "Por corregir");

  private final String value;
  private final String text;
}
