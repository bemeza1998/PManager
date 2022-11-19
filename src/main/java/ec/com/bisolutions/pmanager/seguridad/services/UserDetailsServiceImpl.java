package ec.com.bisolutions.pmanager.seguridad.services;

import ec.com.bisolutions.pmanager.seguridad.dao.UsuarioRepository;
import ec.com.bisolutions.pmanager.seguridad.mapper.UserPrincipalMapper;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user =
        userRepository
            .findByPkCodUsuario(username)
            .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario."));
    return UserPrincipalMapper.userToPrincipal(user);
  }
}
