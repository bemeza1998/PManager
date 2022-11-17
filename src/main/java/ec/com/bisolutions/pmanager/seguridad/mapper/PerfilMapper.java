package ec.com.bisolutions.pmanager.seguridad.mapper;

import ec.com.bisolutions.pmanager.seguridad.dto.PerfilDTO;
import ec.com.bisolutions.pmanager.seguridad.model.Perfil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PerfilMapper {

  public static Perfil buildPerfil(PerfilDTO dto) {
    return Perfil.builder()
        .codPerfil(dto.getCodPerfil())
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .estado(dto.getEstado())
        .build();
  }

  public static PerfilDTO buildPerfilDTO(Perfil perfil) {
    return PerfilDTO.builder()
        .codPerfil(perfil.getCodPerfil())
        .nombre(perfil.getNombre())
        .estado(perfil.getEstado())
        .descripcion(perfil.getDescripcion())
        .build();
  }
}
