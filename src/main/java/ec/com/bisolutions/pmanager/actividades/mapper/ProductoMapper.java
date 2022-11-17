package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.ProductoDTO;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.model.ProductoPK;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductoMapper {

  public static Producto buildProducto(ProductoDTO dto) {
    return Producto.builder()
        .pk(
            ProductoPK.builder()
                .codUsuario(dto.getCodUsuario())
                .codProducto(dto.getCodProducto())
                .build())
        .codJefatura(dto.getCodJefatura())
        .codProyecto(dto.getCodProyecto())
        .nombre(dto.getNombre())
        .mes(dto.getMes())
        .semana(dto.getSemana())
        .fechaEstimadaEntrega(dto.getFechaEstimadaEntrega())
        .horasEstimadas(dto.getHorasEstimadas())
        .fechaRealEntrega(dto.getFechaRealEntrega())
        .porcentajeCumplimiento(dto.getPorcentajeCumplimiento())
        .cronograma(dto.getCronograma())
        .observaciones(dto.getObservaciones())
        .entregadoQa(dto.getEntregadoQa())
        .qaErroresReportados(dto.getQaErroresReportados())
        .qaErroesCorregidos(dto.getQaErroesCorregidos())
        .qaProductosAprobados(dto.getQaProductosAprobados())
        .qaProductosRechazados(dto.getQaProductosRechazados())
        .qaObservaciones(dto.getQaObservaciones())
        .estadoSolicitudModificacion(dto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(dto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(dto.getComentarioSolicitudModificacion())
        .proyecto(ProyectoMapper.buildProyecto(dto.getProyecto()))
        .nombreUsuarioCompleto(dto.getNombreUsuarioCompleto())
        .build();
  }

  public static ProductoDTO buildProductoDTO(Producto producto) {
    return ProductoDTO.builder()
        .codProducto(producto.getPk().getCodProducto())
        .codUsuario(producto.getPk().getCodUsuario())
        .codJefatura(producto.getCodJefatura())
        .codProyecto(producto.getCodProyecto())
        .proyecto(ProyectoMapper.buildProyectoDTO(producto.getProyecto()))
        .nombre(producto.getNombre())
        .mes(producto.getMes())
        .semana(producto.getSemana())
        .fechaEstimadaEntrega(producto.getFechaEstimadaEntrega())
        .horasEstimadas(producto.getHorasEstimadas())
        .fechaRealEntrega(producto.getFechaRealEntrega())
        .porcentajeCumplimiento(producto.getPorcentajeCumplimiento())
        .cronograma(producto.getCronograma())
        .observaciones(producto.getObservaciones())
        .entregadoQa(producto.getEntregadoQa())
        .qaErroresReportados(producto.getQaErroresReportados())
        .qaErroesCorregidos(producto.getQaErroesCorregidos())
        .qaProductosAprobados(producto.getQaProductosAprobados())
        .qaProductosRechazados(producto.getQaProductosRechazados())
        .qaObservaciones(producto.getQaObservaciones())
        .estadoSolicitudModificacion(producto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(producto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(producto.getComentarioSolicitudModificacion())
        .nombreUsuarioCompleto(producto.getNombreUsuarioCompleto())
        .build();
  }
}
