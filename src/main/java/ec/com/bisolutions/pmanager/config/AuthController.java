package ec.com.bisolutions.pmanager.config;

import ec.com.bisolutions.pmanager.seguridad.dao.UsuarioRepository;
import ec.com.bisolutions.pmanager.seguridad.dto.LoginDTO;
import ec.com.bisolutions.pmanager.seguridad.dto.UsuarioDTO;
import ec.com.bisolutions.pmanager.seguridad.mapper.UsuarioMapper;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired AuthenticationManager authenticationManager;
  @Autowired JwtProvider tokenProvider;
  @Autowired PasswordEncoder passwordEncoder;
  @Autowired UsuarioRepository usuarioRepository;

  @PostMapping("/signin")
  public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO login) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getCodUsuario(), login.getClave()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(jwt);
  }

  @PostMapping("/crear")
  public ResponseEntity<?> authenticateUser(@RequestBody UsuarioDTO dto) {
    Usuario usuario = UsuarioMapper.buildUser(dto);
    // String claveEncriptada = DigestUtils.sha256Hex(claveGenerada);
    String claveEncriptada = "1234";

    usuario.setClave(passwordEncoder.encode(claveEncriptada));
    usuario.setEstado("ACT");
    usuario.setNombre(usuario.getNombre().toUpperCase());
    usuario.setApellido(usuario.getApellido().toUpperCase());
    usuario.setFechaCreacion(new Date());
    this.usuarioRepository.save(usuario);
    return ResponseEntity.ok().build();
  }
}
