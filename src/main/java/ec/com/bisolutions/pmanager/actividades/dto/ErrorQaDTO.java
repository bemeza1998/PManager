package ec.com.bisolutions.pmanager.actividades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorQaDTO {

  private Integer codErrorQa;

  private Integer codProducto;

  private String codUsuario;

  private String errorReportado;

  private String estadoError;
}
