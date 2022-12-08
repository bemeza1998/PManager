package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Producto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductoCustomRepositoryImpl implements ProductoCustomRepository {

  @PersistenceContext private final EntityManager em;

  @Override
  public List<Producto> buscarProductosPorFiltro(
      Integer codProyecto,
      String nombreCreador,
      BigDecimal porcentaje,
      Integer mes,
      Date semana,
      String nombreProducto,
      String estadoQa) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);

    Root<Producto> producto = cq.from(Producto.class);
    List<Predicate> predicates = new ArrayList<>();

    if (codProyecto != null) {
      predicates.add(cb.equal(producto.get("codProyecto"), codProyecto));
    }
    if (nombreCreador != null) {
      predicates.add(cb.like(producto.get("nombreUsuarioCompleto"), "%" + nombreCreador + "%"));
    }
    if (porcentaje != null) {
      predicates.add(cb.equal(producto.get("porcentajeCumplimiento"), porcentaje));
    }
    if (mes != null) {
      predicates.add(cb.equal(producto.get("mes"), mes));
    }
    if (semana != null) {
      predicates.add(cb.equal(producto.get("semana"), semana));
    }
    if (nombreProducto != null) {
      predicates.add(cb.like(producto.get("nombre"), "%" + nombreProducto + "%"));
    }
    if (estadoQa != null && !estadoQa.isEmpty()) {
      predicates.add(cb.equal(producto.get("qaEstado"), estadoQa));
    }
    cq.where(predicates.toArray(new Predicate[0]));

    return em.createQuery(cq).getResultList();
  }
}
