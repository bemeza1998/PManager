package ec.com.bisolutions.pmanager.actividades.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoTablaEnum {
  PRODUCTO("PROD", "Producto"),
  PROYECTO("PROY", "Proyecto");

  private final String value;
  private final String text;
}
