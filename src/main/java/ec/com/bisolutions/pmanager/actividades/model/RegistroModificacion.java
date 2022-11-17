package ec.com.bisolutions.pmanager.actividades.model;

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
@Table(name = "ACT_REGISTRO_MODIFICACION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroModificacion implements Serializable {

  private static final long serialVersionUID = 2332423L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COD_REGISTRO_MODIFICACION", nullable = false)
  @EqualsAndHashCode.Include
  private Integer codRegistroModificacion;

  @Column(name = "COD_REGISTRO_MODIFICADO", nullable = false)
  private Integer codRegistroModificado;

  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @Column(name = "TIPO_TABLA", nullable = false, length = 32)
  private String tipoTabla;

  @Column(name = "FECHA_MODIFICACION", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaModificacion;

  @Column(name = "COMENTARIO", length = 512)
  private String comentario;
}
