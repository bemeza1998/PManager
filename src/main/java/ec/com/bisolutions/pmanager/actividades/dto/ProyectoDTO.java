package ec.com.bisolutions.pmanager.actividades.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDTO {

  private Integer codProyecto;

  private String identificadorProyecto;

  private String codUsuario;

  private Integer codJefatura;

  private String nombre;

  private String descripcion;

  private String estado;

  private BigDecimal valorEntrega;

  private Integer diasContrato;

  private BigDecimal valorDia;

  private BigDecimal valorHora;

  private Date fechaInicio;

  private Date fechaFinalizacion;

  private String estadoSolicitudModificacion;

  private Date fechaSolicitudModificacion;

  private String comentarioSolicitudModificacion;

  private String nombreUsuarioCompleto;

  private String nombreEmpresa;
}
