package ec.com.bisolutions.pmanager.config;

import ec.com.bisolutions.pmanager.seguridad.dao.UsuarioRepository;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired private UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user =
        userRepository
            .findByPkCodUsuario(username)
            .orElseThrow(() -> new UsernameNotFoundException("User NOT Found"));
    return UserMapper.userToPrincipal(user);
  }
}
