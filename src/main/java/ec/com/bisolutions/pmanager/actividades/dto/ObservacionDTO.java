package ec.com.bisolutions.pmanager.actividades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObservacionDTO {

  private Integer codObservacion;

  private Integer codProducto;

  private String codUsuario;

  private String texto;
}
