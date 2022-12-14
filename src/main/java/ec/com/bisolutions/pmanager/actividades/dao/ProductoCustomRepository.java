package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Producto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ProductoCustomRepository {
  List<Producto> buscarProductosPorFiltro(
      String codUsuario,
      Integer codProyecto,
      String nombreCreador,
      BigDecimal porcentaje,
      Integer mes,
      Date semana,
      String nombreProducto,
      String estadoQa);
}
