package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.ObservacionDTO;
import ec.com.bisolutions.pmanager.actividades.model.Observacion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObservacionMapper {
  public static Observacion buildObservacion(ObservacionDTO dto) {
    return Observacion.builder()
        .codObservacion(dto.getCodObservacion())
        .codProducto(dto.getCodProducto())
        .codUsuario(dto.getCodUsuario())
        .texto(dto.getTexto())
        .build();
  }

  public static ObservacionDTO buildObservacionDTO(Observacion observacion) {
    return ObservacionDTO.builder()
        .codObservacion(observacion.getCodObservacion())
        .codProducto(observacion.getCodProducto())
        .codUsuario(observacion.getCodUsuario())
        .texto(observacion.getTexto())
        .build();
  }
}
