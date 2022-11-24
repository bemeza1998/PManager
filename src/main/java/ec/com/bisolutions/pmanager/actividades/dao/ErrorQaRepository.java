package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.ErrorQa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorQaRepository extends JpaRepository<ErrorQa, Integer> {

  List<ErrorQa> findByCodProducto(Integer codProducto);
}
