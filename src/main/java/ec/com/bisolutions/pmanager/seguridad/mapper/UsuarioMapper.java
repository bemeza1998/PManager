package ec.com.bisolutions.pmanager.seguridad.mapper;

import ec.com.bisolutions.pmanager.seguridad.dto.UsuarioDTO;
import ec.com.bisolutions.pmanager.seguridad.model.Usuario;
import ec.com.bisolutions.pmanager.seguridad.model.UsuarioPK;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

  public static Usuario buildUser(UsuarioDTO dto) {
    return Usuario.builder()
        .pk(
            UsuarioPK.builder()
                .codUsuario(dto.getCodUsuario())
                .codJefatura(dto.getCodJefatura())
                .build())
        .codPerfil(dto.getCodPerfil())
        .nombre(dto.getNombre())
        .apellido(dto.getApellido())
        .mail(dto.getMail())
        .estado(dto.getEstado())
        .build();
  }

  public static UsuarioDTO buildUserDTO(Usuario usuario) {
    return UsuarioDTO.builder()
        .codUsuario(usuario.getPk().getCodUsuario())
        .codJefatura(usuario.getPk().getCodJefatura())
        .codPerfil(usuario.getCodPerfil())
        .nombre(usuario.getNombre())
        .apellido(usuario.getApellido())
        .mail(usuario.getMail())
        .estado(usuario.getEstado())
        .nombreJefatura(usuario.getJefatura().getNombre())
        .nombrePerfil(usuario.getPerfil().getNombre())
        .build();
  }
}
