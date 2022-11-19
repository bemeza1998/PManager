package ec.com.bisolutions.pmanager.seguridad.mapper;

import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import ec.com.bisolutions.pmanager.seguridad.utils.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserPrincipalMapper {

  public static UserPrincipal userToPrincipal(Usuario usuario) {

    List<Perfil> perfiles = new ArrayList<Perfil>();
    perfiles.add(usuario.getPerfil());
    List<SimpleGrantedAuthority> authorities =
        perfiles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCodPerfil()))
            .collect(Collectors.toList());
    return UserPrincipal.builder()
        .username(usuario.getPk().getCodUsuario())
        .password(usuario.getClave())
        .enabled(true)
        .authorities(authorities)
        .build();
  }
}
