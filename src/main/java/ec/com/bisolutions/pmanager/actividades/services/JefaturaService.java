package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.JefaturaRepository;
import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CreateException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.ModificarException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JefaturaService {

  private final JefaturaRepository jefaturaRepository;

  public List<Jefatura> obtenerJefaturas() {
    return this.jefaturaRepository.findAll();
  }

  public Jefatura crear(Jefatura jefatura) {
    Optional<Jefatura> jefaturaDB = this.jefaturaRepository.findBySiglas(jefatura.getSiglas());
    if (jefaturaDB.isPresent()) {
      throw new CreateException("Ya existe una jefatura con las siglas " + jefatura.getSiglas());
    }
    return this.jefaturaRepository.save(jefatura);
  }

  public Jefatura modificar(Jefatura jefatura) {
    Jefatura jefaturaDB = buscarPorCodigo(jefatura.getCodJefatura());
    jefaturaDB.setSiglas(jefatura.getSiglas());
    jefaturaDB.setNombre(jefatura.getNombre());
    return this.jefaturaRepository.save(jefaturaDB);
  }

  public void eliminar(Integer codJefatura) {
    Jefatura jefaturaDB = buscarPorCodigo(codJefatura);
    this.jefaturaRepository.delete(jefaturaDB);
  }

  private Jefatura buscarPorCodigo(Integer codJefatura) {
    Optional<Jefatura> jefaturaOPT = this.jefaturaRepository.findById(codJefatura);
    if (!jefaturaOPT.isPresent()) {
      throw new ModificarException("No se encontró la jefatura con id " + codJefatura);
    }
    return jefaturaOPT.get();
  }
}
