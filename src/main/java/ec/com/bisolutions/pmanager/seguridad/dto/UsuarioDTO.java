package ec.com.bisolutions.pmanager.seguridad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  private String codUsuario;

  private int codJefatura;

  private String codPerfil;

  private String nombre;

  private String apellido;

  private String mail;

  private String estado;

  private String nombreJefatura;

  private String nombrePerfil;
}
