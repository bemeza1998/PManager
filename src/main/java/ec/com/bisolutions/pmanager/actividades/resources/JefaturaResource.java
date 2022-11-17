package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import ec.com.bisolutions.pmanager.actividades.services.JefaturaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<List<Jefatura>> obtenerJefaturas() {
    return ResponseEntity.ok(this.service.obtenerJefaturas());
  }

  @PostMapping
  public ResponseEntity<Jefatura> crear(@RequestBody Jefatura jefatura) {
    return ResponseEntity.ok(this.service.crear(jefatura));
  }

  @PutMapping
  public ResponseEntity<Jefatura> modificar(@RequestBody Jefatura jefatura) {
    return ResponseEntity.ok(this.service.modificar(jefatura));
  }

  @DeleteMapping
  public ResponseEntity<Void> eliminar(@RequestBody String siglas) {
    this.service.eliminar(siglas);
    return ResponseEntity.ok().build();
  }
}
