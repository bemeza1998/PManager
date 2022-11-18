package ec.com.bisolutions.pmanager.seguridad.resources;

import ec.com.bisolutions.pmanager.seguridad.dao.PerfilRepository;
import ec.com.bisolutions.pmanager.seguridad.dto.LoginDTO;
import ec.com.bisolutions.pmanager.seguridad.dto.UsuarioDTO;
import ec.com.bisolutions.pmanager.seguridad.mapper.UsuarioMapper;
import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import ec.com.bisolutions.pmanager.seguridad.services.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/usuario")
@RequiredArgsConstructor
public class UsuarioResource {
  private final UsuarioService service;
  private final PerfilRepository rep;

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @GetMapping(path = "/estado/{estado}")
  public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(@PathVariable String estado) {
    List<Usuario> usuarios = this.service.obtenerUsuarios(estado);
    List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
    for (Usuario usuario : usuarios) {
      usuariosDTO.add(UsuarioMapper.buildUserDTO(usuario));
    }
    return ResponseEntity.ok(usuariosDTO);
  }

  @PostMapping
  public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioDTO dto) {
    Usuario usuario = this.service.crear(UsuarioMapper.buildUser(dto));
    return ResponseEntity.ok(UsuarioMapper.buildUserDTO(usuario));
  }

  @PostMapping(path = "/perfil")
  public ResponseEntity<Perfil> crearPerfil(@RequestBody Perfil dto) {
    return ResponseEntity.ok(this.rep.save(dto));
  }

  @PutMapping
  public ResponseEntity<UsuarioDTO> modificar(@RequestBody UsuarioDTO dto) {
    Usuario usuario = this.service.modificar(UsuarioMapper.buildUser(dto));
    return ResponseEntity.ok(UsuarioMapper.buildUserDTO(usuario));
  }

  @PatchMapping(path = "/estado")
  public ResponseEntity<UsuarioDTO> modificarEstadoUsuario(@RequestBody UsuarioDTO dto) {
    Usuario usuario = this.service.modificarEstado(UsuarioMapper.buildUser(dto));
    return ResponseEntity.ok(UsuarioMapper.buildUserDTO(usuario));
  }

  @PutMapping(path = "/login")
  public ResponseEntity<UsuarioDTO> iniciarSesion(@RequestBody LoginDTO loginDto) {
    Usuario usuario = this.service.iniciarSesion(loginDto.getCodUsuario(), loginDto.getClave());
    return ResponseEntity.ok(UsuarioMapper.buildUserDTO(usuario));
  }

  @PatchMapping(path = "/clave")
  public ResponseEntity<Usuario> cambiarClave(
      @RequestParam String codUsuario,
      @RequestParam String claveAntigua,
      @RequestParam String claveNueva) {
    this.service.cambiarClave(codUsuario, claveAntigua, claveNueva);
    return ResponseEntity.ok().build();
  }
}
