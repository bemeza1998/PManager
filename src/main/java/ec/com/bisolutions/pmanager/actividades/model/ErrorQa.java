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
@Table(name = "ACT_ERROR_QA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ErrorQa implements Serializable {

  private static final long serialVersionUID = 12222123L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COD_ERROR_QA", nullable = false)
  @EqualsAndHashCode.Include
  private Integer codErrorQa;

  @Column(name = "COD_PRODUCTO", nullable = false)
  private Integer codProducto;

  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @Column(name = "ERROR_REPORTADO", nullable = false, length = 512)
  private String errorReportado;

  @Column(name = "ESTADO_ERROR", nullable = false, length = 3)
  private String estadoError;

  @Column(name = "FECHA_ERROR")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaError;
}
