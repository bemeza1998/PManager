package ec.com.bisolutions.pmanager.actividades.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoSolicitudModificar {
  SOLICITADO("SOL", "Solicitado para editar"),
  SOLICITADO_ELIMINAR("SEL", "Solicitado para eliminar"),
  APROBADO("APR", "Aprobado"),
  NEGADO("NEG", "Negado"),
  NO_SOLICITADO("NOS", "No solicitado"),
  ELIMINADO("ELI", "Eliminado");

  private final String value;
  private final String text;
}
