package ec.com.bisolutions.pmanager.seguridad.dao;

import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, String> {
  Optional<Perfil> findByCodPerfil(String codPerfil);
}
