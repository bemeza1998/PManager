package ec.com.bisolutions.pmanager.actividades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JefaturaDTO {

  private Integer codJefatura;

  private String nombre;

  private String siglas;
}
