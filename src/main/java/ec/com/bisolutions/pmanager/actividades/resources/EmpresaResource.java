package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.EmpresaDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.EmpresaMapper;
import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import ec.com.bisolutions.pmanager.actividades.services.EmpresaService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/empresa")
@RequiredArgsConstructor
public class EmpresaResource {

  private final EmpresaService service;

  @PreAuthorize("hasAnyRole('ADM','ALP')")
  @PostMapping
  public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO dto) {
    Empresa empresa = this.service.crearEmpresa(EmpresaMapper.buildEmpresa(dto));
    return ResponseEntity.ok(EmpresaMapper.buildEmpresaDTO(empresa));
  }

  @PreAuthorize("hasAnyRole('ADM','ALP')")
  @GetMapping(path = "/estado/{clienteActivo}")
  public ResponseEntity<List<EmpresaDTO>> obtenerEmpresas(@PathVariable String clienteActivo) {
    List<Empresa> empresas = this.service.obtenerEmpresas(clienteActivo);
    List<EmpresaDTO> empresasDTO = new ArrayList<EmpresaDTO>();
    for (Empresa empresa : empresas) {
      empresasDTO.add(EmpresaMapper.buildEmpresaDTO(empresa));
    }
    return ResponseEntity.ok(empresasDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','ALP')")
  @PutMapping
  public ResponseEntity<EmpresaDTO> modificarEmpresa(@RequestBody EmpresaDTO dto) {
    Empresa empresa = this.service.modificarEmpresa(EmpresaMapper.buildEmpresa(dto));
    return ResponseEntity.ok(EmpresaMapper.buildEmpresaDTO(empresa));
  }

  @PreAuthorize("hasAnyRole('ADM','ALP')")
  @PatchMapping
  public ResponseEntity<EmpresaDTO> modificarClienteActivoEmpresa(@RequestBody EmpresaDTO dto) {
    Empresa empresa = this.service.modificarClienteActivoEmpresa(EmpresaMapper.buildEmpresa(dto));
    return ResponseEntity.ok(EmpresaMapper.buildEmpresaDTO(empresa));
  }
}
