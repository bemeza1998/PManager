package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Observacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {

  List<Observacion> findByCodProductoOrderByFechaCreacion(Integer codProducto);
}
