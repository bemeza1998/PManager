package ec.com.bisolutions.pmanager.seguridad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO {

  private String codPerfil;

  private String nombre;

  private String descripcion;

  private String estado;
}
