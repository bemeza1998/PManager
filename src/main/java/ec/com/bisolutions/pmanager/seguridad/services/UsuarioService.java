package ec.com.bisolutions.pmanager.seguridad.services;

import ec.com.bisolutions.pmanager.seguridad.dao.RegistroSesionRepository;
import ec.com.bisolutions.pmanager.seguridad.dao.UsuarioRepository;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoRegistroSesionEnum;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoUsuarioEnum;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CambioClaveException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CreateException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.LoginException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import ec.com.bisolutions.pmanager.seguridad.model.RegistroSesion;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final RegistroSesionRepository registroSesionRepository;

  public List<Usuario> obtenerUsuarios(String estado) {
    if (estado.equals("ALL")) {
      return this.usuarioRepository.findAll();
    } else {
      return this.usuarioRepository.findByEstadoOrderByNombre(estado);
    }
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
    String claveGenerada = RandomStringUtils.randomAlphabetic(8);
    String claveEncriptada = DigestUtils.sha256Hex(claveGenerada);

    usuario.setClave("1234");
    usuario.setEstado(EstadoUsuarioEnum.ACTIVO.getValue());
    usuario.setNombre(usuario.getNombre().toUpperCase());
    usuario.setApellido(usuario.getApellido().toUpperCase());
    usuario.setFechaCreacion(new Date());
    return this.usuarioRepository.save(usuario);
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

    // String claveEncriptada = DigestUtils.sha256Hex(clave);
    if (!usuario.getClave().equals(clave)) {
      // if (!usuario.getClave().equals(claveEncriptada)) {
      registroSesion.setResultado(EstadoRegistroSesionEnum.FALLIDO.getValor());
      this.registroSesionRepository.save(registroSesion);
      throw new LoginException("La clave ingresada es incorrecta.");
    }

    registroSesion.setResultado(EstadoRegistroSesionEnum.SATISFACTORIO.getValor());
    this.registroSesionRepository.save(registroSesion);
    usuario.setFechaUltimaSesion(new Date());
    return this.usuarioRepository.save(usuario);
  }

  public void cambiarClave(String codUsuario, String claveAntigua, String claveNueva)
      throws CambioClaveException {
    Usuario usuario = this.buscarUsuarioPorCodigo(codUsuario);

    claveAntigua = DigestUtils.sha256Hex(claveAntigua);
    if (!usuario.getClave().equals(claveAntigua)) {
      throw new CambioClaveException("La clave antigua no es correcta.");
    }
    usuario.setClave(DigestUtils.sha256Hex(claveNueva));
    usuario.setFechaCambioClave(new Date());
    this.usuarioRepository.save(usuario);
  }

  private Usuario buscarUsuarioPorCodigo(String codUsuario) throws NotFoundException {
    return this.usuarioRepository
        .findByPkCodUsuario(codUsuario)
        .orElseThrow(() -> new NotFoundException("Error, el usuario no existe"));
  }
}
