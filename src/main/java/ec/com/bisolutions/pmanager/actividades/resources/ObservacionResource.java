package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.ObservacionDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.ObservacionMapper;
import ec.com.bisolutions.pmanager.actividades.model.Observacion;
import ec.com.bisolutions.pmanager.actividades.services.ObservacionService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/observacion")
@RequiredArgsConstructor
public class ObservacionResource {

  private final ObservacionService service;

  @PreAuthorize("hasAnyRole('ADM','CAL','REC')")
  @GetMapping(path = "/{codProducto}")
  public ResponseEntity<List<ObservacionDTO>> obtenerObservacionesProducto(
      @PathVariable Integer codProducto) {
    List<Observacion> observaciones = this.service.obtenerObservacionesProducto(codProducto);
    List<ObservacionDTO> observacionesDTO = new ArrayList<ObservacionDTO>();
    for (Observacion observacion : observaciones) {
      observacionesDTO.add(ObservacionMapper.buildObservacionDTO(observacion));
    }
    return ResponseEntity.ok(observacionesDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @PostMapping
  public ResponseEntity<ObservacionDTO> crear(@RequestBody ObservacionDTO dto) {
    Observacion observacion = this.service.crear(ObservacionMapper.buildObservacion(dto));
    return ResponseEntity.ok(ObservacionMapper.buildObservacionDTO(observacion));
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @PatchMapping
  public ResponseEntity<ObservacionDTO> modificar(@RequestBody ObservacionDTO dto) {
    Observacion observacion = this.service.modificar(ObservacionMapper.buildObservacion(dto));
    return ResponseEntity.ok(ObservacionMapper.buildObservacionDTO(observacion));
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @DeleteMapping(path = "/eliminar/{codObservacion}")
  public ResponseEntity<?> eliminarObservacion(@PathVariable Integer codObservacion) {
    this.service.eliminar(codObservacion);
    return ResponseEntity.ok().build();
  }
}
