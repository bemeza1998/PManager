package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.JefaturaDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.JefaturaMapper;
import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import ec.com.bisolutions.pmanager.actividades.services.JefaturaService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/jefatura")
@RequiredArgsConstructor
public class JefaturaResource {
  private final JefaturaService service;

  @PreAuthorize("hasRole('ADM')")
  @GetMapping
  public ResponseEntity<List<JefaturaDTO>> obtenerJefaturas() {
    List<Jefatura> jefaturas = this.service.obtenerJefaturas();
    List<JefaturaDTO> jefaturasDTO = new ArrayList<>();
    for (Jefatura jefatura : jefaturas) {
      jefaturasDTO.add(JefaturaMapper.buildJefaturaDTO(jefatura));
    }
    return ResponseEntity.ok(jefaturasDTO);
  }

  @PreAuthorize("hasRole('ADM')")
  @PostMapping
  public ResponseEntity<JefaturaDTO> crear(@RequestBody JefaturaDTO dto) {
    Jefatura jefatura = this.service.crear(JefaturaMapper.buildJefatura(dto));
    return ResponseEntity.ok(JefaturaMapper.buildJefaturaDTO(jefatura));
  }

  @PreAuthorize("hasRole('ADM')")
  @PutMapping
  public ResponseEntity<JefaturaDTO> modificar(@RequestBody JefaturaDTO dto) {
    Jefatura jefatura = this.service.modificar(JefaturaMapper.buildJefatura(dto));
    return ResponseEntity.ok(JefaturaMapper.buildJefaturaDTO(jefatura));
  }

  @PreAuthorize("hasRole('ADM')")
  @DeleteMapping(path = "/eliminar/{codJefatura}")
  public ResponseEntity<Void> eliminar(@PathVariable Integer codJefatura) {
    this.service.eliminar(codJefatura);
    return ResponseEntity.ok().build();
  }
}
