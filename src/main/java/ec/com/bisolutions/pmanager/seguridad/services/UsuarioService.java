package ec.com.bisolutions.pmanager.seguridad.services;

import ec.com.bisolutions.pmanager.actividades.dao.JefaturaRepository;
import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import ec.com.bisolutions.pmanager.config.JwtProvider;
import ec.com.bisolutions.pmanager.seguridad.dao.PerfilRepository;
import ec.com.bisolutions.pmanager.seguridad.dao.RegistroSesionRepository;
import ec.com.bisolutions.pmanager.seguridad.dao.UsuarioRepository;
import ec.com.bisolutions.pmanager.seguridad.dto.UserAuthDTO;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoRegistroSesionEnum;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoUsuarioEnum;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CambioClaveException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CreateException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.LoginException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import ec.com.bisolutions.pmanager.seguridad.mapper.UsuarioMapper;
import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import ec.com.bisolutions.pmanager.seguridad.model.RegistroSesion;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final JefaturaRepository jefaturaRepository;
  private final RegistroSesionRepository registroSesionRepository;
  private final EmailService emailService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider tokenProvider;

  public List<Usuario> obtenerUsuarios(String estado) {
    if (estado.equals("ALL")) {
      return this.usuarioRepository.findAll();
    } else {
      return this.usuarioRepository.findByEstadoOrderByNombre(estado);
    }
  }

  public List<Usuario> obtenerUsuariosPerfil(String perfil) {
    return this.usuarioRepository.findByCodPerfilAndEstadoOrderByNombre(
        perfil, EstadoUsuarioEnum.ACTIVO.getValue());
  }

  public Usuario crear(Usuario usuario) {
    this.usuarioRepository
        .findByMailOrPkCodUsuario(usuario.getMail(), usuario.getPk().getCodUsuario())
        .ifPresent(
            usuarioDB -> {
              Boolean correoTomando = usuarioDB.getMail().equals(usuario.getMail());
              Boolean codUsuarioTomado =
                  usuarioDB.getPk().getCodUsuario().equals(usuario.getPk().getCodUsuario());

              if (correoTomando && codUsuarioTomado) {
                throw new CreateException("Error, usuario y correo no disponibles");
              }

              if (correoTomando) throw new CreateException("Error, email no disponible");
              if (codUsuarioTomado) throw new CreateException("Error, usuario no disponible");
            });
    // String claveGenerada = RandomStringUtils.randomAlphabetic(8);
    String claveGenerada = "1234";

    usuario.setClave(passwordEncoder.encode(claveGenerada));
    usuario.setEstado(EstadoUsuarioEnum.ACTIVO.getValue());
    usuario.setNombre(usuario.getNombre().toUpperCase());
    usuario.setApellido(usuario.getApellido().toUpperCase());
    usuario.setFechaCreacion(new Date());
    usuario.setPerfil(this.buscarPerfilPorCodigo(usuario.getCodPerfil()));
    usuario.setJefatura(this.buscarJefaturaPorCodigo(usuario.getPk().getCodJefatura()));
    /*try {
      this.emailService.enviarClaveGenerada(
          usuario.getMail(),
          usuario.getNombre() + " " + usuario.getApellido(),
          claveGenerada,
          usuario.getPk().getCodUsuario());
      return this.usuarioRepository.save(usuario);
    } catch (MessagingException e) {
      System.out.println("Error al enviar correo.");
      return this.usuarioRepository.save(usuario);
    }*/
    return this.usuarioRepository.save(usuario);
  }

  public Usuario modificar(Usuario usuario) {
    Usuario usuarioDB = this.buscarUsuarioPorCodigo(usuario.getPk().getCodUsuario());
    usuarioDB.getPk().setCodJefatura(usuario.getPk().getCodJefatura());
    usuarioDB.setCodPerfil(usuario.getCodPerfil());
    usuarioDB.setNombre(usuario.getNombre());
    usuarioDB.setApellido(usuario.getApellido());
    usuarioDB.setMail(usuario.getMail());
    usuarioDB.setEstado(usuario.getEstado());
    return this.usuarioRepository.save(usuarioDB);
  }

  public Usuario modificarEstado(Usuario usuario) {
    Usuario usuarioDB = this.buscarUsuarioPorCodigo(usuario.getPk().getCodUsuario());
    usuarioDB.setEstado(usuario.getEstado());
    return this.usuarioRepository.save(usuarioDB);
  }

  public Usuario iniciarSesion(String codUsuario, String clave) throws LoginException {
    Usuario usuario = this.buscarUsuarioPorCodigo(codUsuario);
    RegistroSesion registroSesion =
        RegistroSesion.builder()
            .codUsuario(usuario.getPk().getCodUsuario())
            .fechaConexion(new Date())
            .ipConexion("")
            .resultado("")
            .build();
    Boolean usuarioInactivo = usuario.getEstado().equals(EstadoUsuarioEnum.INACTIVO.getValue());
    Boolean usuarioBloqueado = usuario.getEstado().equals(EstadoUsuarioEnum.BLOQUEADO.getValue());

    if (usuarioInactivo || usuarioBloqueado) {
      registroSesion.setResultado(EstadoRegistroSesionEnum.FALLIDO.getValor());
      this.registroSesionRepository.save(registroSesion);
      throw new LoginException("El usuario indicado no esta habilitado para ingresar al sistema.");
    }

    Boolean claveCorrecta = passwordEncoder.matches(clave, usuario.getClave());
    if (!claveCorrecta) {
      registroSesion.setResultado(EstadoRegistroSesionEnum.FALLIDO.getValor());
      this.registroSesionRepository.save(registroSesion);
      throw new LoginException("La clave ingresada es incorrecta.");
    }

    registroSesion.setResultado(EstadoRegistroSesionEnum.SATISFACTORIO.getValor());
    this.registroSesionRepository.save(registroSesion);
    usuario.setFechaUltimaSesion(new Date());
    return this.usuarioRepository.save(usuario);
  }

  public UserAuthDTO renovarToken() {
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    Usuario usuarioDB = this.buscarUsuarioPorCodigo(username);
    UserAuthDTO userAuthDTO = UsuarioMapper.buildUserAuthDTO(usuarioDB);
    userAuthDTO.setToken(
        this.tokenProvider.generateTokenOnlyUser(usuarioDB.getPk().getCodUsuario()));
    return userAuthDTO;
  }

  public String generarJWT(String codUsuario, String clave) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(codUsuario, clave));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.generateToken(authentication);
  }

  public void cambiarClave(String codUsuario, String claveAntigua, String claveNueva)
      throws CambioClaveException {
    Usuario usuario = this.buscarUsuarioPorCodigo(codUsuario);

    Boolean claveCorrecta = passwordEncoder.matches(claveAntigua, usuario.getClave());
    if (!claveCorrecta) {
      throw new CambioClaveException("La clave antigua no es correcta.");
    }
    usuario.setClave(passwordEncoder.encode(claveNueva));
    usuario.setFechaCambioClave(new Date());
    this.usuarioRepository.save(usuario);
  }

  private Usuario buscarUsuarioPorCodigo(String codUsuario) throws NotFoundException {
    return this.usuarioRepository
        .findByPkCodUsuario(codUsuario)
        .orElseThrow(() -> new NotFoundException("Error, el usuario no existe"));
  }

  private Perfil buscarPerfilPorCodigo(String codPerfil) {
    return this.perfilRepository
        .findById(codPerfil)
        .orElseThrow(() -> new NotFoundException("Error, el perfil no existe"));
  }

  private Jefatura buscarJefaturaPorCodigo(Integer codJefatura) {
    return this.jefaturaRepository
        .findById(codJefatura)
        .orElseThrow(() -> new NotFoundException("Error, la jefatura no existe"));
  }
}
