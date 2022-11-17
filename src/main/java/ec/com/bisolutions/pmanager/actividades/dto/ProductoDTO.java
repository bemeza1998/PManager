package ec.com.bisolutions.pmanager.actividades.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

  private Integer codProducto;

  private String codUsuario;

  private Integer codJefatura;

  private Integer codProyecto;

  private ProyectoDTO proyecto;

  private String nombre;

  private Integer mes;

  private Date semana;

  private Date fechaEstimadaEntrega;

  private Integer horasEstimadas;

  private Date fechaRealEntrega;

  private BigDecimal porcentajeCumplimiento;

  private String cronograma;

  private String observaciones;

  private Integer entregadoQa;

  private Integer qaErroresReportados;

  private Integer qaErroesCorregidos;

  private Integer qaProductosAprobados;

  private Integer qaProductosRechazados;

  private String qaObservaciones;

  private String estadoSolicitudModificacion;

  private Date fechaSolicitudModificacion;

  private String comentarioSolicitudModificacion;

  private String nombreUsuarioCompleto;
}
