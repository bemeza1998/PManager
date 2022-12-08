package ec.com.bisolutions.pmanager.actividades.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

  @JsonFormat(pattern = "dd-MM-yyyy")
  private String semana;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private String fechaEstimadaEntrega;

  private Integer horasEstimadas;

  private Date fechaRealEntrega;

  private BigDecimal porcentajeCumplimiento;

  private Boolean cronograma;

  private Integer entregadoQa;

  private Integer qaErroresReportados;

  private Integer qaErroesCorregidos;

  private Integer qaProductosAprobados;

  private Integer qaProductosRechazados;

  private String qaObservaciones;

  private String qaEstado;

  private String estadoSolicitudModificacion;

  private Date fechaSolicitudModificacion;

  private String comentarioSolicitudModificacion;

  private String nombreUsuarioCompleto;
}
