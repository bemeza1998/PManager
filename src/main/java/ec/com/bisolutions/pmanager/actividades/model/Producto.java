package ec.com.bisolutions.pmanager.actividades.model;

import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACT_PRODUCTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {

  private static final long serialVersionUID = 11231231L;
  @EmbeddedId @EqualsAndHashCode.Include protected ProductoPK pk;

  @Column(name = "NOMBRE", nullable = false, length = 256)
  private String nombre;

  @Column(name = "COD_JEFATURA", nullable = false)
  private Integer codJefatura;

  @Column(name = "COD_PROYECTO", nullable = false)
  private Integer codProyecto;

  @Column(name = "NOMBRE_USUARIO_COMPLETO", nullable = false, length = 256)
  private String nombreUsuarioCompleto;

  @Column(name = "MES", nullable = false)
  private Integer mes;

  @Column(name = "SEMANA", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date semana;

  @Column(name = "FECHA_ESTIMADA_ENTREGA")
  @Temporal(TemporalType.DATE)
  private Date fechaEstimadaEntrega;

  @Column(name = "HORAS_ESTIMADAS")
  private Integer horasEstimadas;

  @Column(name = "FECHA_REAL_ENTREGA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaRealEntrega;

  @Column(name = "PORCENTAJE_CUMPLIMIENTO", precision = 5, scale = 2)
  private BigDecimal porcentajeCumplimiento;

  @Column(name = "CRONOGRAMA", length = 64)
  private String cronograma;

  @Column(name = "OBSERVACIONES", length = 516)
  private String observaciones;

  @Column(name = "ENTREGADO_QA")
  private Integer entregadoQa;

  @Column(name = "QA_ERRORES_REPORTADOS")
  private Integer qaErroresReportados;

  @Column(name = "QA_ERROES_CORREGIDOS")
  private Integer qaErroesCorregidos;

  @Column(name = "QA_PRODUCTOS_APROBADOS")
  private Integer qaProductosAprobados;

  @Column(name = "QA_PRODUCTOS_RECHAZADOS")
  private Integer qaProductosRechazados;

  @Column(name = "QA_OBSERVACIONES", length = 516)
  private String qaObservaciones;

  @Column(name = "FECHA_CREACION", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;

  @Column(name = "ESTADO_SOLICITUD_MODIFICACION", length = 3)
  private String estadoSolicitudModificacion;

  @Column(name = "FECHA_SOLICITUD_MODIFICACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaSolicitudModificacion;

  @Column(name = "COMENTARIO_SOLICITUD_MODIFICACION", length = 512)
  private String comentarioSolicitudModificacion;

  @Column(name = "APLAZADO", length = 2)
  private String aplazado;

  @JoinColumn(
      name = "COD_PROYECTO",
      referencedColumnName = "COD_PROYECTO",
      insertable = false,
      updatable = false)
  @ManyToOne
  private Proyecto proyecto;

  @JoinColumns({
    @JoinColumn(
        name = "COD_USUARIO",
        referencedColumnName = "COD_USUARIO",
        nullable = false,
        insertable = false,
        updatable = false),
    @JoinColumn(
        name = "COD_JEFATURA",
        referencedColumnName = "COD_JEFATURA",
        insertable = false,
        updatable = false)
  })
  @ManyToOne(optional = false)
  private Usuario usuario;
}
