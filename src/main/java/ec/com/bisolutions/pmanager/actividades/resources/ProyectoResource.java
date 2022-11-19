package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.ProyectoDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.ProyectoMapper;
import ec.com.bisolutions.pmanager.actividades.model.Proyecto;
import ec.com.bisolutions.pmanager.actividades.services.ProyectoService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/proyecto")
@RequiredArgsConstructor
public class ProyectoResource {
  private final ProyectoService service;

  @GetMapping
  public ResponseEntity<List<ProyectoDTO>> obtenerPorJefatura(@RequestParam Integer codJefatura) {
    List<Proyecto> proyectos = this.service.obtenerPorJefatura(codJefatura);
    List<ProyectoDTO> proyectosDTO = new ArrayList<ProyectoDTO>();
    for (Proyecto proyecto : proyectos) {
      proyectosDTO.add(ProyectoMapper.buildProyectoDTO(proyecto));
    }
    return ResponseEntity.ok(proyectosDTO);
  }

  @PreAuthorize("hasRole('ADM','JEF')")
  @GetMapping(path = "/estado")
  public ResponseEntity<List<ProyectoDTO>> obtenerPorEstadoModificacion() {
    List<Proyecto> proyectos = this.service.obtenerPorEstadoModificacion();
    List<ProyectoDTO> productosDTO = new ArrayList<ProyectoDTO>();
    for (Proyecto proyecto : proyectos) {
      productosDTO.add(ProyectoMapper.buildProyectoDTO(proyecto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PreAuthorize("hasRole('ADM','ALP')")
  @PostMapping
  public ResponseEntity<ProyectoDTO> crear(@RequestBody ProyectoDTO dto) {
    Proyecto proyecto = this.service.crear(ProyectoMapper.buildProyecto(dto));
    return ResponseEntity.ok(ProyectoMapper.buildProyectoDTO(proyecto));
  }

  @PreAuthorize("hasRole('ADM','ALP')")
  @PutMapping
  public ResponseEntity<ProyectoDTO> modificar(@RequestBody ProyectoDTO dto) {
    Proyecto proyecto = this.service.modificar(ProyectoMapper.buildProyecto(dto));
    return ResponseEntity.ok(ProyectoMapper.buildProyectoDTO(proyecto));
  }

  @PreAuthorize("hasRole('ADM','ALP')")
  @PatchMapping
  public ResponseEntity<ProyectoDTO> modificarEstadoSolicitud(@RequestBody ProyectoDTO dto) {
    Proyecto proyecto = this.service.modificarEstadoSolicitud(ProyectoMapper.buildProyecto(dto));
    return ResponseEntity.ok(ProyectoMapper.buildProyectoDTO(proyecto));
  }
}
