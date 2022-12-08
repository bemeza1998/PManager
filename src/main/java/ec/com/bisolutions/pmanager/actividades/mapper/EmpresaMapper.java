package ec.com.bisolutions.pmanager.actividades.mapper;

import ec.com.bisolutions.pmanager.actividades.dto.EmpresaDTO;
import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmpresaMapper {

  public static Empresa buildEmpresa(EmpresaDTO dto) {
    return Empresa.builder()
        .codEmpresa(dto.getCodEmpresa())
        .nombreEmpresa(dto.getNombreEmpresa())
        .direccion(dto.getDireccion())
        .telefono(dto.getTelefono())
        .mail(dto.getMail())
        .clienteActivo(dto.getClienteActivo())
        .build();
  }

  public static EmpresaDTO buildEmpresaDTO(Empresa error) {
    return EmpresaDTO.builder()
        .codEmpresa(error.getCodEmpresa())
        .nombreEmpresa(error.getNombreEmpresa())
        .direccion(error.getDireccion())
        .telefono(error.getTelefono())
        .mail(error.getMail())
        .clienteActivo(error.getClienteActivo())
        .build();
  }
}
