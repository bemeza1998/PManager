package ec.com.bisolutions.pmanager.seguridad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

  private String codUsuario;

  private String codPerfil;

  private Number codJefatura;

  private String nombre;

  private String apellido;

  private String mail;

  private String nombrePerfil;

  private String token;
}
