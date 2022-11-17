package ec.com.bisolutions.pmanager.seguridad.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEG_PERFIL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Perfil implements Serializable {

  private static final long serialVersionUID = 121312312L;

  @Id
  @EqualsAndHashCode.Include
  @Column(name = "COD_PERFIL", nullable = false, length = 3)
  private String codPerfil;

  @Column(name = "NOMBRE", nullable = false, length = 32)
  private String nombre;

  @Column(name = "DESCRIPCION", nullable = false, length = 128)
  private String descripcion;

  @Column(name = "ESTADO", nullable = false, length = 3)
  private String estado;

  // @OneToMany(mappedBy = "codPerfil")
  // private List<Usuario> segUsuarioList;
}
