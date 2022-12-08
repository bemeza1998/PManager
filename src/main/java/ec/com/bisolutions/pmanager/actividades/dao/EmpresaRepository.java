package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
  List<Empresa> findByClienteActivoOrderByFechaIngreso(String clienteActivo);
}
