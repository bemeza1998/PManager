package ec.com.bisolutions.pmanager.seguridad.dao;

import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import ec.com.bisolutions.pmanager.seguridad.model.UsuarioPK;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioPK> {

  Optional<Usuario> findByMailOrPkCodUsuario(String mail, String codUsuario);

  Optional<Usuario> findByPkCodUsuario(String codUsuario);

  List<Usuario> findByEstadoOrderByNombre(String estado);
}
