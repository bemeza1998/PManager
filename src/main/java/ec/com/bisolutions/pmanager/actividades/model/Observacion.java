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
@Table(name = "ACT_OBSERVACION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Observacion implements Serializable {

  private static final long serialVersionUID = 122342323L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COD_OBSERVACION", nullable = false)
  @EqualsAndHashCode.Include
  private Integer codObservacion;

  @Column(name = "COD_PRODUCTO", nullable = false)
  private Integer codProducto;

  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @Column(name = "TEXTO", nullable = false, length = 512)
  private String texto;

  @Column(name = "FECHA_CREACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;
}
