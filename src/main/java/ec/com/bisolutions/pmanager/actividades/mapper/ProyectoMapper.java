package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.ProyectoDTO;
import ec.com.bisolutions.pmanager.actividades.model.Proyecto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProyectoMapper {

  public static Proyecto buildProyecto(ProyectoDTO dto) {
    return Proyecto.builder()
        .codProyecto(dto.getCodProyecto())
        .identificadorProyecto(dto.getIdentificadorProyecto())
        .codUsuario(dto.getCodUsuario())
        .codJefatura(dto.getCodJefatura())
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .estado(dto.getEstado())
        .valorEntrega(dto.getValorEntrega())
        .diasContrato(dto.getDiasContrato())
        .valorDia(dto.getValorDia())
        .valorHora(dto.getValorHora())
        .fechaInicio(dto.getFechaInicio())
        .fechaFinalizacion(dto.getFechaFinalizacion())
        .estadoSolicitudModificacion(dto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(dto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(dto.getComentarioSolicitudModificacion())
        .nombreUsuarioCompleto(dto.getNombreUsuarioCompleto())
        .codEmpresa(dto.getCodEmpresa())
        .build();
  }

  public static ProyectoDTO buildProyectoDTO(Proyecto proyecto) {
    return ProyectoDTO.builder()
        .codProyecto(proyecto.getCodProyecto())
        .identificadorProyecto(proyecto.getIdentificadorProyecto())
        .codUsuario(proyecto.getCodUsuario())
        .codJefatura(proyecto.getCodJefatura())
        .nombre(proyecto.getNombre())
        .descripcion(proyecto.getDescripcion())
        .estado(proyecto.getEstado())
        .valorEntrega(proyecto.getValorEntrega())
        .diasContrato(proyecto.getDiasContrato())
        .valorDia(proyecto.getValorDia())
        .valorHora(proyecto.getValorHora())
        .fechaInicio(proyecto.getFechaInicio())
        .fechaFinalizacion(proyecto.getFechaFinalizacion())
        .estadoSolicitudModificacion(proyecto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(proyecto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(proyecto.getComentarioSolicitudModificacion())
        .nombreUsuarioCompleto(proyecto.getNombreUsuarioCompleto())
        .nombreEmpresa(proyecto.getEmpresa().getNombreEmpresa())
        .codEmpresa(proyecto.getEmpresa().getCodEmpresa())
        .build();
  }
}
