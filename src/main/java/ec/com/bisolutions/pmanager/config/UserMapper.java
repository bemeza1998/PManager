package ec.com.bisolutions.pmanager.config;

import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserMapper {
  public static UserPrincipal userToPrincipal(Usuario user) {
    UserPrincipal userp = new UserPrincipal();

    // SimpleGrantedAuthority perfil = new SimpleGrantedAuthority("ROLE_" + user.getPerfil());
    // List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    List<Perfil> perfiles = new ArrayList<Perfil>();
    perfiles.add(user.getPerfil());

    List<SimpleGrantedAuthority> authorities =
        perfiles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCodPerfil()))
            .collect(Collectors.toList());
    // System.out.println(authorities);
    userp.setUsername(user.getPk().getCodUsuario());
    userp.setPassword(user.getClave());
    userp.setEnabled(true);
    userp.setAuthorities(authorities);
    return userp;
  }
}
