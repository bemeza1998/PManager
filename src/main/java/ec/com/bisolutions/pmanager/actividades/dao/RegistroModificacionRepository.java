package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.RegistroModificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroModificacionRepository
    extends JpaRepository<RegistroModificacion, Integer> {}
