package ec.com.bisolutions.pmanager.seguridad.resources;

import ec.com.bisolutions.pmanager.seguridad.dto.PerfilDTO;
import ec.com.bisolutions.pmanager.seguridad.mapper.PerfilMapper;
import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import ec.com.bisolutions.pmanager.seguridad.services.PerfilService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/perfil")
@RequiredArgsConstructor
public class PerfilResource {

  private final PerfilService service;

  @GetMapping()
  public ResponseEntity<List<PerfilDTO>> obtenerPerfiles() {
    List<Perfil> perfiles = this.service.obtenerPerfiles();
    List<PerfilDTO> perfilesDTO = new ArrayList<PerfilDTO>();
    for (Perfil perfil : perfiles) {
      perfilesDTO.add(PerfilMapper.buildPerfilDTO(perfil));
    }
    return ResponseEntity.ok(perfilesDTO);
  }

  @PostMapping
  public ResponseEntity<PerfilDTO> crear(@RequestBody PerfilDTO dto) {
    Perfil perfil = this.service.crear(PerfilMapper.buildPerfil(dto));
    return ResponseEntity.ok(PerfilMapper.buildPerfilDTO(perfil));
  }

  @PutMapping
  public ResponseEntity<PerfilDTO> modificar(@RequestBody PerfilDTO dto) {
    Perfil perfil = this.service.modificar(PerfilMapper.buildPerfil(dto));
    return ResponseEntity.ok(PerfilMapper.buildPerfilDTO(perfil));
  }

  @PatchMapping
  public ResponseEntity<String> eliminar(@RequestBody PerfilDTO dto) {
    this.service.eliminar(PerfilMapper.buildPerfil(dto));
    return ResponseEntity.ok().build();
  }
}
