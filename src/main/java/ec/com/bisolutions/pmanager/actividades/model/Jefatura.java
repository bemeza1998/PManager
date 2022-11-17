package ec.com.bisolutions.pmanager.actividades.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACT_JEFATURA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jefatura implements Serializable {

  private static final long serialVersionUID = 12213123L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COD_JEFATURA", nullable = false)
  @EqualsAndHashCode.Include
  private Integer codJefatura;

  @Column(name = "NOMBRE", nullable = false, length = 32)
  private String nombre;

  @Column(name = "SIGLAS", nullable = false, length = 4)
  private String siglas;

  // @OneToMany(cascade = CascadeType.ALL, mappedBy = "actJefatura")
  // private List<Usuario> segUsuarioList;
}
