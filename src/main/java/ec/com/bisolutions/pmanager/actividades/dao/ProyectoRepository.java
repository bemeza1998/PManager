package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
  List<Proyecto> findByCodJefaturaAndEstadoSolicitudModificacionNotInAndEstadoOrderByNombre(
      Integer codJefatura, String[] estados, String estadoProyecto);

  List<Proyecto> findByEstadoSolicitudModificacionInOrderByFechaSolicitudModificacion(
      String[] estados);
}
