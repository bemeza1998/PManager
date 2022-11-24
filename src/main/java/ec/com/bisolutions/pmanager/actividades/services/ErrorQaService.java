package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.ErrorQaRepository;
import ec.com.bisolutions.pmanager.actividades.dao.ProductoRepository;
import ec.com.bisolutions.pmanager.actividades.enums.EstadoErrorQaEnum;
import ec.com.bisolutions.pmanager.actividades.enums.EstadoQaEnum;
import ec.com.bisolutions.pmanager.actividades.model.ErrorQa;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.model.ProductoPK;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorQaService {

  private final ErrorQaRepository errorQaRepository;
  private final ProductoRepository productoRepository;

  public List<ErrorQa> obtenerErroresProducto(Integer codProducto) {
    return this.errorQaRepository.findByCodProducto(codProducto);
  }

  public ErrorQa crearError(ErrorQa error) {
    error.setFechaError(new Date());
    error.setEstadoError(EstadoErrorQaEnum.POR_CORREGIR.getValue());
    Producto productoDB = this.construirProductoPk(error);
    productoDB.setQaErroresReportados(productoDB.getQaErroresReportados() + 1);
    productoDB.setQaEstado(EstadoQaEnum.RECAHAZADO_QA.getValue());
    this.productoRepository.save(productoDB);
    return this.errorQaRepository.save(error);
  }

  public ErrorQa cambiarEstado(ErrorQa error) {
    ErrorQa errorQaDB = this.buscarPorCodigo(error.getCodErrorQa());
    Producto productoDB = construirProductoPk(errorQaDB);
    productoDB.setQaErroesCorregidos(
        error.getEstadoError().equals(EstadoErrorQaEnum.CORREGIDO.getValue())
            ? productoDB.getQaErroesCorregidos() + 1
            : productoDB.getQaErroesCorregidos() - 1);
    productoDB.setQaEstado(
        error.getEstadoError().equals(EstadoErrorQaEnum.POR_CORREGIR.getValue())
            ? EstadoQaEnum.RECAHAZADO_QA.getValue()
            : productoDB.getQaEstado());
    errorQaDB.setEstadoError(error.getEstadoError());
    return this.errorQaRepository.save(errorQaDB);
  }

  public void eliminarError(Integer codError) {
    ErrorQa errorQaDB = this.buscarPorCodigo(codError);
    Producto productoDB = construirProductoPk(errorQaDB);
    productoDB.setQaErroresReportados(productoDB.getQaErroresReportados() - 1);
    productoDB.setQaErroesCorregidos(
        errorQaDB.getEstadoError().equals(EstadoErrorQaEnum.CORREGIDO.getValue())
            ? productoDB.getQaErroesCorregidos() - 1
            : productoDB.getQaErroesCorregidos());
    this.errorQaRepository.delete(errorQaDB);
  }

  private Producto construirProductoPk(ErrorQa errorQa) {
    return this.buscarProductoPorCodigo(
        ProductoPK.builder()
            .codProducto(errorQa.getCodProducto())
            .codUsuario(errorQa.getCodUsuario())
            .build());
  }

  private ErrorQa buscarPorCodigo(Integer codError) {
    return this.errorQaRepository
        .findById(codError)
        .orElseThrow(() -> new NotFoundException("Error, el error reportado no existe"));
  }

  private Producto buscarProductoPorCodigo(ProductoPK productoPK) {
    return this.productoRepository
        .findById(productoPK)
        .orElseThrow(() -> new NotFoundException("Error, el producto no existe"));
  }
}
