package ec.com.bisolutions.pmanager.actividades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

  private Integer codEmpresa;

  private String nombreEmpresa;

  private String direccion;

  private String telefono;

  private String mail;

  private String clienteActivo;
}
