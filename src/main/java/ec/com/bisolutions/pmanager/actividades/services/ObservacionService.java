package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.ObservacionRepository;
import ec.com.bisolutions.pmanager.actividades.model.Observacion;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObservacionService {

  private final ObservacionRepository observacionRepository;

  public List<Observacion> obtenerObservacionesProducto(Integer codProducto) {
    return this.observacionRepository.findByCodProductoOrderByFechaCreacion(codProducto);
  }

  public Observacion crear(Observacion observacion) {
    observacion.setFechaCreacion(new Date());
    return this.observacionRepository.save(observacion);
  }

  public Observacion modificar(Observacion observacion) {
    Observacion observacionDB = this.buscarPorCodigo(observacion.getCodObservacion());
    observacionDB.setTexto(observacion.getTexto());
    return this.observacionRepository.save(observacionDB);
  }

  public void eliminar(Integer codObservacion) {
    Observacion observacionDB = this.buscarPorCodigo(codObservacion);
    this.observacionRepository.delete(observacionDB);
  }

  private Observacion buscarPorCodigo(Integer codObservacion) {
    return this.observacionRepository
        .findById(codObservacion)
        .orElseThrow(() -> new NotFoundException("Error, la observación no existe"));
  }
}
