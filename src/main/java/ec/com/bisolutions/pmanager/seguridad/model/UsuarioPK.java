package ec.com.bisolutions.pmanager.seguridad.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioPK implements Serializable {

  @EqualsAndHashCode.Include
  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @EqualsAndHashCode.Include
  @Column(name = "COD_JEFATURA", nullable = false)
  private int codJefatura;
}
