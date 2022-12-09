package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.EmpresaRepository;
import ec.com.bisolutions.pmanager.actividades.dao.ProductoRepository;
import ec.com.bisolutions.pmanager.actividades.dao.RegistroModificacionRepository;
import ec.com.bisolutions.pmanager.actividades.enums.EstadoQaEnum;
import ec.com.bisolutions.pmanager.actividades.enums.EstadoSolicitudModificar;
import ec.com.bisolutions.pmanager.actividades.enums.TipoTablaEnum;
import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.model.ProductoPK;
import ec.com.bisolutions.pmanager.actividades.model.RegistroModificacion;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoUsuarioEnum;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {

  private final ProductoRepository productoRepository;
  private final EmpresaRepository empresaRepository;
  private final RegistroModificacionRepository registroModificacionRepository;

  public List<Producto> obtenerProductos(String codUsuario) {
    String[] noEliminado = {EstadoSolicitudModificar.ELIMINADO.getValue()};
    return this.productoRepository
        .findByPkCodUsuarioAndEstadoSolicitudModificacionNotInAndUsuarioEstadoOrderBySemanaDesc(
            codUsuario, noEliminado, EstadoUsuarioEnum.ACTIVO.getValue());
  }

  public List<Producto> obtenerProductosTodos(Date semana) {
    if (semana == null) {
      return this.productoRepository.findByUsuarioEstadoOrderByFechaCreacionDesc(
          EstadoUsuarioEnum.ACTIVO.getValue());
    } else {
      return this.productoRepository.findByUsuarioEstadoAndSemanaOrderByFechaCreacionDesc(
          EstadoUsuarioEnum.ACTIVO.getValue(), semana);
    }
  }

  public Producto obtenerProductoQA(String codUsuario, Integer codProducto) {
    return this.buscarPorCodigo(
        ProductoPK.builder().codUsuario(codUsuario).codProducto(codProducto).build());
  }

  public List<Producto> obtenerPorEstadoModificacion() {
    String[] estados = {
      EstadoSolicitudModificar.SOLICITADO.getValue(),
      EstadoSolicitudModificar.SOLICITADO_ELIMINAR.getValue()
    };
    return this.productoRepository
        .findByEstadoSolicitudModificacionInAndUsuarioEstadoOrderByFechaSolicitudModificacion(
            estados, EstadoUsuarioEnum.ACTIVO.getValue());
  }

  public List<Producto> obtenerPorFiltro(
      String codUsuario,
      Integer codProyecto,
      String nombreCreador,
      BigDecimal porcentaje,
      Integer mes,
      Date semana,
      String nombreProducto,
      String estadoQa) {
    return this.productoRepository.buscarProductosPorFiltro(
        codUsuario, codProyecto, nombreCreador, porcentaje, mes, semana, nombreProducto, estadoQa);
  }

  public Producto crear(Producto producto) {
    producto.setFechaCreacion(new Date());
    producto.setQaErroesCorregidos(0);
    producto.setQaErroresReportados(0);
    producto.setQaProductosAprobados(0);
    producto.setQaProductosRechazados(0);
    producto.setEntregadoQa(0);
    producto.setAplazado("NO");
    producto.setFechaRealEntrega(
        producto.getPorcentajeCumplimiento().equals(new BigDecimal(100)) ? new Date() : null);
    producto.setQaEstado(EstadoQaEnum.POR_REVISAR.getValue());
    return this.productoRepository.save(producto);
  }

  public void crearVariosBatch(List<Producto> productos) {
    List<Producto> nuevosProductos = new ArrayList<>();
    Date nuevaSemana = new Date();
    for (Producto producto : productos) {
      nuevosProductos.add(this.crearProductoAplazado(producto, nuevaSemana));
    }
    this.productoRepository.saveAll(nuevosProductos);
  }

  public void modificarAplazado(List<Producto> productos) {
    List<RegistroModificacion> registrosModificados = new ArrayList<RegistroModificacion>();
    for (Producto producto : productos) {
      producto.setAplazado("SI");
      registrosModificados.add(
          this.crearRegistroModificacion(producto, "Modiciación por Batch aplazamiento de semana"));
    }
    this.productoRepository.saveAll(productos);
    this.registroModificacionRepository.saveAll(registrosModificados);
  }

  public Producto modificar(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    Date fechaReal =
        producto.getPorcentajeCumplimiento().equals(new BigDecimal(100))
                && productoDB.getPorcentajeCumplimiento().equals(new BigDecimal(100))
            ? null
            : (producto.getPorcentajeCumplimiento().equals(new BigDecimal(100))
                ? new Date()
                : null);
    productoDB.setCodProyecto(producto.getCodProyecto());
    productoDB.setNombre(producto.getNombre());
    productoDB.setSemana(producto.getSemana());
    productoDB.setFechaEstimadaEntrega(producto.getFechaEstimadaEntrega());
    productoDB.setHorasEstimadas(producto.getHorasEstimadas());
    productoDB.setPorcentajeCumplimiento(producto.getPorcentajeCumplimiento());
    productoDB.setCronograma(producto.getCronograma());
    // productoDB.setObservaciones(producto.getObservaciones());
    productoDB.setProyecto(producto.getProyecto());
    productoDB.setEstadoSolicitudModificacion(EstadoSolicitudModificar.NO_SOLICITADO.getValue());
    productoDB.setFechaRealEntrega(fechaReal);
    productoDB
        .getProyecto()
        .setEmpresa(this.buscarEmpresa(productoDB.getProyecto().getCodEmpresa()));
    RegistroModificacion registroModificacion = crearRegistroModificacion(producto, "");
    this.registroModificacionRepository.save(registroModificacion);

    return this.productoRepository.save(productoDB);
  }

  public Producto modificarQA(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    productoDB.setEntregadoQa(producto.getEntregadoQa());
    productoDB.setQaErroesCorregidos(producto.getQaErroesCorregidos());
    productoDB.setQaErroresReportados(producto.getQaErroresReportados());
    productoDB.setQaProductosAprobados(producto.getQaProductosAprobados());
    productoDB.setQaProductosRechazados(producto.getQaProductosRechazados());
    productoDB.setQaObservaciones(producto.getQaObservaciones());

    RegistroModificacion registroModificacion = crearRegistroModificacion(producto, "");
    this.registroModificacionRepository.save(registroModificacion);

    return this.productoRepository.save(productoDB);
  }

  public Producto modificarPorcentajeCumplimiento(Producto producto, String usuario) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    String comentario =
        "El usuario "
            + usuario
            + " modificó el porcentaje de cumplimiento del producto "
            + producto.getNombre()
            + ", cambiado de "
            + productoDB.getPorcentajeCumplimiento()
            + " a "
            + producto.getPorcentajeCumplimiento();
    productoDB.setPorcentajeCumplimiento(producto.getPorcentajeCumplimiento());

    if (producto.getPorcentajeCumplimiento().equals(new BigDecimal(100))) {
      productoDB.setFechaRealEntrega(new Date());
    } else if (productoDB.getFechaEstimadaEntrega() != null) {
      productoDB.setFechaRealEntrega(null);
      if (producto.getQaEstado().equals(EstadoQaEnum.APROBADO_QA.getValue())) {
        productoDB.setQaEstado(EstadoQaEnum.POR_REVISAR.getValue());
      }
    }

    RegistroModificacion registroModificacion = crearRegistroModificacion(producto, comentario);
    this.registroModificacionRepository.save(registroModificacion);

    return this.productoRepository.save(productoDB);
  }

  public Producto modificarCronograma(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    String comentario =
        "Modificación en el cronograma del producto "
            + producto.getNombre()
            + ", cambiado de "
            + productoDB.getCronograma()
            + " a "
            + producto.getCronograma();
    productoDB.setCronograma(producto.getCronograma());

    RegistroModificacion registroModificacion = crearRegistroModificacion(producto, comentario);
    this.registroModificacionRepository.save(registroModificacion);

    return this.productoRepository.save(productoDB);
  }

  /*public Producto modificarObservaciones(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    String comentario =
        "Modificación en las observaciones del producto "
            + producto.getNombre()
            + ", cambiado de "
            + productoDB.getObservaciones()
            + " a "
            + producto.getObservaciones();
    productoDB.setObservaciones(producto.getObservaciones());

    RegistroModificacion registroModificacion = crearRegistroModificacion(producto, comentario);
    this.registroModificacionRepository.save(registroModificacion);

    return this.productoRepository.save(productoDB);
  }*/

  public Producto solicitarEstado(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    productoDB.setEstadoSolicitudModificacion(producto.getEstadoSolicitudModificacion());
    productoDB.setFechaSolicitudModificacion(new Date());
    productoDB.setComentarioSolicitudModificacion(producto.getComentarioSolicitudModificacion());
    return this.productoRepository.save(productoDB);
  }

  public Producto modificarEstadoQA(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    productoDB.setQaEstado(producto.getQaEstado());
    return this.productoRepository.save(productoDB);
  }

  public Producto modificarObservacionQA(Producto producto) {
    Producto productoDB = this.buscarPorCodigo(producto.getPk());
    productoDB.setQaObservaciones(producto.getQaObservaciones());
    return this.productoRepository.save(productoDB);
  }

  public void eliminar(Integer codProducto, String codUsuario) {
    Producto productoDB =
        this.buscarPorCodigo(
            ProductoPK.builder().codProducto(codProducto).codUsuario(codUsuario).build());
    this.productoRepository.delete(productoDB);
  }

  public List<Producto> buscarPorSemana(Date semana) {
    return this.productoRepository.findBySemana(semana);
  }

  private Producto buscarPorCodigo(ProductoPK productoPK) {
    return this.productoRepository
        .findById(productoPK)
        .orElseThrow(() -> new NotFoundException("Error, el producto no existe"));
  }

  private Empresa buscarEmpresa(Integer codEmpresa) {
    return this.empresaRepository
        .findById(codEmpresa)
        .orElseThrow(() -> new NotFoundException("Error, la empresa no existe"));
  }

  private RegistroModificacion crearRegistroModificacion(Producto producto, String comentario) {
    return RegistroModificacion.builder()
        .codRegistroModificado(producto.getPk().getCodProducto())
        .codUsuario(producto.getPk().getCodUsuario())
        .tipoTabla(TipoTablaEnum.PRODUCTO.getValue())
        .fechaModificacion(new Date())
        .comentario(
            comentario.isEmpty() ? producto.getComentarioSolicitudModificacion() : comentario)
        .build();
  }

  private Producto crearProductoAplazado(Producto producto, Date nuevaSemana) {
    return Producto.builder()
        .pk(
            ProductoPK.builder()
                .codProducto(null)
                .codUsuario(producto.getPk().getCodUsuario())
                .build())
        .nombre(producto.getNombre())
        .codJefatura(producto.getCodJefatura())
        .codProyecto(producto.getCodProyecto())
        .nombreUsuarioCompleto(producto.getNombreUsuarioCompleto())
        .mes(producto.getMes())
        .semana(nuevaSemana)
        .fechaEstimadaEntrega(producto.getFechaEstimadaEntrega())
        .horasEstimadas(producto.getHorasEstimadas())
        .fechaRealEntrega(producto.getFechaRealEntrega())
        .porcentajeCumplimiento(producto.getPorcentajeCumplimiento())
        .cronograma(producto.getCronograma())
        .entregadoQa(producto.getEntregadoQa())
        .qaErroresReportados(producto.getQaErroresReportados())
        .qaErroesCorregidos(producto.getQaErroesCorregidos())
        .qaProductosAprobados(producto.getQaProductosAprobados())
        .qaProductosRechazados(producto.getQaProductosRechazados())
        .qaObservaciones(producto.getQaObservaciones())
        .fechaCreacion(new Date())
        .estadoSolicitudModificacion(producto.getEstadoSolicitudModificacion())
        .fechaSolicitudModificacion(producto.getFechaSolicitudModificacion())
        .comentarioSolicitudModificacion(producto.getComentarioSolicitudModificacion())
        .aplazado("NO")
        .proyecto(producto.getProyecto())
        .build();
  }
}
