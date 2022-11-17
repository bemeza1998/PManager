package ec.com.bisolutions.pmanager.seguridad.dao;

import ec.com.bisolutions.pmanager.seguridad.model.RegistroSesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroSesionRepository extends JpaRepository<RegistroSesion, Integer> {}
