package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.EmpresaRepository;
import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpresaService {
  private final EmpresaRepository empresaRepository;

  public Empresa crearEmpresa(Empresa empresa) {
    empresa.setFechaIngreso(new Date());
    return this.empresaRepository.save(empresa);
  }

  public List<Empresa> obtenerEmpresas(String clienteActivo) {
    if (clienteActivo.equals("ALL")) {
      return this.empresaRepository.findAll();
    } else {
      return this.empresaRepository.findByClienteActivoOrderByFechaIngreso(clienteActivo);
    }
  }

  public Empresa modificarClienteActivoEmpresa(Empresa empresa) {
    Empresa empresaDB = this.buscarPorCodigo(empresa.getCodEmpresa());
    empresaDB.setClienteActivo(empresa.getClienteActivo());
    return this.empresaRepository.save(empresaDB);
  }

  public Empresa modificarEmpresa(Empresa empresa) {
    Empresa empresaDB = this.buscarPorCodigo(empresa.getCodEmpresa());
    empresaDB.setNombreEmpresa(empresa.getNombreEmpresa());
    empresaDB.setTelefono(empresa.getTelefono());
    empresaDB.setDireccion(empresa.getDireccion());
    empresaDB.setMail(empresa.getMail());
    empresaDB.setClienteActivo(empresa.getClienteActivo());
    return this.empresaRepository.save(empresaDB);
  }

  private Empresa buscarPorCodigo(Integer codEmpresa) {
    return this.empresaRepository
        .findById(codEmpresa)
        .orElseThrow(() -> new NotFoundException("Error, la empresa no existe"));
  }
}
