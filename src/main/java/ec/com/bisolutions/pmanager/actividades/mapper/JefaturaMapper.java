package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.JefaturaDTO;
import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JefaturaMapper {

  public static Jefatura buildJefatura(JefaturaDTO dto) {
    return Jefatura.builder()
        .codJefatura(dto.getCodJefatura())
        .nombre(dto.getNombre())
        .siglas(dto.getSiglas())
        .build();
  }

  public static JefaturaDTO buildJefaturaDTO(Jefatura jefatura) {
    return JefaturaDTO.builder()
        .codJefatura(jefatura.getCodJefatura())
        .nombre(jefatura.getNombre())
        .siglas(jefatura.getSiglas())
        .build();
  }
}
