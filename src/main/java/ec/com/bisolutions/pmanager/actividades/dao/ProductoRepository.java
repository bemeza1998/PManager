package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.model.ProductoPK;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository
    extends JpaRepository<Producto, ProductoPK>, ProductoCustomRepository {

  List<Producto> findByUsuarioEstadoOrderByFechaCreacionDesc(String estado);

  List<Producto>
      findByPkCodUsuarioAndEstadoSolicitudModificacionNotInAndUsuarioEstadoOrderBySemanaDesc(
          String codUsuario, String[] estado, String estadoUsuario);

  List<Producto>
      findByEstadoSolicitudModificacionInAndUsuarioEstadoOrderByFechaSolicitudModificacion(
          String[] estado, String estadoUsuario);

  List<Producto> findBySemana(Date semana);
}
