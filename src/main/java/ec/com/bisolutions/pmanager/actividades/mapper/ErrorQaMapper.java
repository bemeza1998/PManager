package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.ErrorQaDTO;
import ec.com.bisolutions.pmanager.actividades.model.ErrorQa;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorQaMapper {

  public static ErrorQa buildErrorQa(ErrorQaDTO dto) {
    return ErrorQa.builder()
        .codErrorQa(dto.getCodErrorQa())
        .codProducto(dto.getCodProducto())
        .codUsuario(dto.getCodUsuario())
        .errorReportado(dto.getErrorReportado())
        .estadoError(dto.getEstadoError())
        .build();
  }

  public static ErrorQaDTO buildErrorQaDTO(ErrorQa error) {
    return ErrorQaDTO.builder()
        .codErrorQa(error.getCodErrorQa())
        .codProducto(error.getCodProducto())
        .codUsuario(error.getCodUsuario())
        .errorReportado(error.getErrorReportado())
        .estadoError(error.getEstadoError())
        .build();
  }
}
