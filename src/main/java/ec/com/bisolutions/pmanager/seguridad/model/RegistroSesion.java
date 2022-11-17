package ec.com.bisolutions.pmanager.seguridad.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEG_REGISTRO_SESION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroSesion implements Serializable {

  private static final long serialVersionUID = 132423432L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "COD_REGISTRO_SESION", nullable = false)
  private Integer codRegistroSesion;

  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @Column(name = "FECHA_CONEXION", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaConexion;

  @Column(name = "IP_CONEXION", length = 30)
  private String ipConexion;

  @Column(name = "RESULTADO", nullable = false, length = 3)
  private String resultado;
}
