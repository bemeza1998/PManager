package ec.com.bisolutions.pmanager.seguridad.services;

import ec.com.bisolutions.pmanager.seguridad.dao.PerfilRepository;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoGeneralEnum;
import ec.com.bisolutions.pmanager.seguridad.exceptions.CreateException;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {

  private final PerfilRepository perfilRepository;

  public List<Perfil> obtenerPerfiles() {
    return this.perfilRepository.findAll();
  }

  public Perfil crear(Perfil perfil) {
    this.perfilRepository
        .findByCodPerfil(perfil.getCodPerfil())
        .ifPresent(
            perfilDB -> {
              throw new CreateException("El código de perfil ya existe.");
            });
    perfil.setEstado(EstadoGeneralEnum.ACTIVO.getValue());
    return this.perfilRepository.save(perfil);
  }

  public Perfil modificar(Perfil perfil) {
    Perfil perfilDB = this.buscarPorCodigo(perfil.getCodPerfil());
    perfilDB.setDescripcion(perfil.getDescripcion());
    perfilDB.setNombre(perfil.getNombre());
    perfilDB.setEstado(perfil.getEstado());
    return this.perfilRepository.save(perfilDB);
  }

  public void eliminar(Perfil perfil) {
    Perfil perfilDB = this.buscarPorCodigo(perfil.getCodPerfil());
    perfilDB.setEstado(EstadoGeneralEnum.INACTIVO.getValue());
    this.perfilRepository.save(perfilDB);
  }

  private Perfil buscarPorCodigo(String codPerfil) {
    return this.perfilRepository
        .findById(codPerfil)
        .orElseThrow(() -> new NotFoundException("Error, el perfil no existe"));
  }
}
