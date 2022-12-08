package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.ProductoDTO;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.model.ProductoPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductoMapper {

  public static Producto buildProducto(ProductoDTO dto) throws ParseException {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    Date semanaFormato = formatoFecha.parse(dto.getSemana());
    Date fechaEstimadaEntregaFormato = formatoFecha.parse(dto.getFechaEstimadaEntrega());
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
        .semana(semanaFormato)
        .fechaEstimadaEntrega(fechaEstimadaEntregaFormato)
        .horasEstimadas(dto.getHorasEstimadas())
        .fechaRealEntrega(dto.getFechaRealEntrega())
        .porcentajeCumplimiento(dto.getPorcentajeCumplimiento())
        .cronograma(dto.getCronograma() == true ? 1 : 0)
        .entregadoQa(dto.getEntregadoQa())
        .qaErroresReportados(dto.getQaErroresReportados())
        .qaErroesCorregidos(dto.getQaErroesCorregidos())
        .qaProductosAprobados(dto.getQaProductosAprobados())
        .qaProductosRechazados(dto.getQaProductosRechazados())
        .qaObservaciones(dto.getQaObservaciones())
        .qaEstado(dto.getQaEstado())
        .estadoSolicitudModificacion(dto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(dto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(dto.getComentarioSolicitudModificacion())
        .proyecto(ProyectoMapper.buildProyecto(dto.getProyecto()))
        .nombreUsuarioCompleto(dto.getNombreUsuarioCompleto())
        .build();
  }

  public static ProductoDTO buildProductoDTO(Producto producto) {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    String semanaFormato = formatoFecha.format(producto.getSemana());
    String fechaEstimadaEntregaFormato = formatoFecha.format(producto.getFechaEstimadaEntrega());
    return ProductoDTO.builder()
        .codProducto(producto.getPk().getCodProducto())
        .codUsuario(producto.getPk().getCodUsuario())
        .codJefatura(producto.getCodJefatura())
        .codProyecto(producto.getCodProyecto())
        .proyecto(ProyectoMapper.buildProyectoDTO(producto.getProyecto()))
        .nombre(producto.getNombre())
        .mes(producto.getMes())
        .semana(semanaFormato)
        .fechaEstimadaEntrega(fechaEstimadaEntregaFormato)
        .horasEstimadas(producto.getHorasEstimadas())
        .fechaRealEntrega(producto.getFechaRealEntrega())
        .porcentajeCumplimiento(producto.getPorcentajeCumplimiento())
        .cronograma(producto.getCronograma() == 1 ? true : false)
        .entregadoQa(producto.getEntregadoQa())
        .qaErroresReportados(producto.getQaErroresReportados())
        .qaErroesCorregidos(producto.getQaErroesCorregidos())
        .qaProductosAprobados(producto.getQaProductosAprobados())
        .qaProductosRechazados(producto.getQaProductosRechazados())
        .qaObservaciones(producto.getQaObservaciones())
        .qaEstado(producto.getQaEstado())
        .estadoSolicitudModificacion(producto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(producto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(producto.getComentarioSolicitudModificacion())
        .nombreUsuarioCompleto(producto.getNombreUsuarioCompleto())
        .build();
  }
}
