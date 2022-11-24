package ec.com.bisolutions.pmanager.actividades.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoQaEnum {
    POR_REVISAR("PRQ", "Por revisar"),
  APROBADO_QA("APQ", "Aprobado QA"),
  RECAHAZADO_QA("REQ", "Rechazado QA");

  private final String value;
  private final String text;
}
