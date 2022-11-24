package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.ErrorQaDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.ErrorQaMapper;
import ec.com.bisolutions.pmanager.actividades.model.ErrorQa;
import ec.com.bisolutions.pmanager.actividades.services.ErrorQaService;
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
@RequestMapping(path = "/ap1/v1/errorqa")
@RequiredArgsConstructor
public class ErrorQaResource {

  private final ErrorQaService service;

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @GetMapping(path = "/{codProducto}")
  public ResponseEntity<List<ErrorQaDTO>> obtenerErroresProducto(
      @PathVariable Integer codProducto) {
    List<ErrorQa> erroresQa = this.service.obtenerErroresProducto(codProducto);
    List<ErrorQaDTO> erroresQaDTO = new ArrayList<ErrorQaDTO>();
    for (ErrorQa errorQa : erroresQa) {
      erroresQaDTO.add(ErrorQaMapper.buildErrorQaDTO(errorQa));
    }
    return ResponseEntity.ok(erroresQaDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @PostMapping
  public ResponseEntity<ErrorQaDTO> crear(@RequestBody ErrorQaDTO dto) {
    ErrorQa errorQa = this.service.crearError(ErrorQaMapper.buildErrorQa(dto));
    return ResponseEntity.ok(ErrorQaMapper.buildErrorQaDTO(errorQa));
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @PatchMapping(path = "/estado")
  public ResponseEntity<ErrorQaDTO> cambiarEstado(@RequestBody ErrorQaDTO dto) {
    ErrorQa errorQa = this.service.cambiarEstado(ErrorQaMapper.buildErrorQa(dto));
    return ResponseEntity.ok(ErrorQaMapper.buildErrorQaDTO(errorQa));
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @DeleteMapping(path = "/eliminar/{codError}")
  public ResponseEntity<?> eliminarError(@PathVariable Integer codError) {
    this.service.eliminarError(codError);
    return ResponseEntity.ok().build();
  }
}
