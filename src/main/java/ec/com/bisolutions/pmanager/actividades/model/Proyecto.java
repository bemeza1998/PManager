package ec.com.bisolutions.pmanager.actividades.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ACT_PROYECTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proyecto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "COD_PROYECTO", nullable = false)
  private Integer codProyecto;

  @Column(name = "IDENTIFICADOR_PROYECTO", nullable = false, length = 5)
  private String identificadorProyecto;

  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;

  @Column(name = "COD_JEFATURA", nullable = false, length = 16)
  private Integer codJefatura;

  @Column(name = "NOMBRE", nullable = false, length = 64)
  private String nombre;

  @Column(name = "DESCRIPCION", nullable = false, length = 128)
  private String descripcion;

  @Column(name = "ESTADO", nullable = false, length = 3)
  private String estado;

  @Column(name = "VALOR_ENTREGA", nullable = false, precision = 10, scale = 2)
  private BigDecimal valorEntrega;

  @Column(name = "DIAS_CONTRATO", nullable = false)
  private Integer diasContrato;

  @Column(name = "VALOR_DIA", precision = 6, scale = 2)
  private BigDecimal valorDia;

  @Column(name = "VALOR_HORA", precision = 6, scale = 2)
  private BigDecimal valorHora;

  @Column(name = "FECHA_CREACION", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;

  @Column(name = "FECHA_INICIO")
  @Temporal(TemporalType.DATE)
  private Date fechaInicio;

  @Column(name = "FECHA_FINALIZACION")
  @Temporal(TemporalType.DATE)
  private Date fechaFinalizacion;

  @Column(name = "ESTADO_SOLICITUD_MODIFICACION", length = 3)
  private String estadoSolicitudModificacion;

  @Column(name = "FECHA_SOLICITUD_MODIFICACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaSolicitudModificacion;

  @Column(name = "COMENTARIO_SOLICITUD_MODIFICACION", length = 512)
  private String comentarioSolicitudModificacion;

  @Column(name = "NOMBRE_USUARIO_COMPLETO", nullable = false, length = 256)
  private String nombreUsuarioCompleto;

  @Column(name = "NOMBRE_EMPRESA", length = 128)
  private String nombreEmpresa;

  /*@JoinColumn(
      name = "COD_EMPRESA",
      referencedColumnName = "COD_EMPRESA",
      insertable = false,
      updatable = false)
  @ManyToOne
  private Empresa empresa;*/
}
