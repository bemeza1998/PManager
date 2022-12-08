package ec.com.bisolutions.pmanager.actividades.services;

import ec.com.bisolutions.pmanager.actividades.dao.EmpresaRepository;
import ec.com.bisolutions.pmanager.actividades.dao.ProyectoRepository;
import ec.com.bisolutions.pmanager.actividades.dao.RegistroModificacionRepository;
import ec.com.bisolutions.pmanager.actividades.enums.EstadoSolicitudModificar;
import ec.com.bisolutions.pmanager.actividades.enums.TipoTablaEnum;
import ec.com.bisolutions.pmanager.actividades.model.Empresa;
import ec.com.bisolutions.pmanager.actividades.model.Proyecto;
import ec.com.bisolutions.pmanager.actividades.model.RegistroModificacion;
import ec.com.bisolutions.pmanager.seguridad.enums.EstadoGeneralEnum;
import ec.com.bisolutions.pmanager.seguridad.exceptions.NotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProyectoService {

  private final ProyectoRepository proyectoRepository;
  private final EmpresaRepository empresaRepository;
  private final RegistroModificacionRepository registroModificacionRepository;

  public Proyecto crear(Proyecto proyecto) {
    BigDecimal valorDia =
        proyecto
            .getValorEntrega()
            .divide(new BigDecimal(proyecto.getDiasContrato()), 2, RoundingMode.HALF_UP);
    BigDecimal valorHora = valorDia.divide(new BigDecimal(8), 2, RoundingMode.HALF_UP);
    proyecto.setFechaCreacion(new Date());
    proyecto.setEstado(EstadoGeneralEnum.ACTIVO.getValue());
    proyecto.setValorDia(valorDia);
    proyecto.setValorHora(valorHora);
    proyecto.setEmpresa(this.buscarEmpresaPorCodigo(proyecto.getCodEmpresa()));
    return this.proyectoRepository.save(proyecto);
  }

  public Proyecto modificar(Proyecto proyecto) {
    Proyecto proyectoDB = this.buscarProyectoPorCodigo(proyecto.getCodProyecto());
    BigDecimal valorDia =
        proyecto
            .getValorEntrega()
            .divide(new BigDecimal(proyecto.getDiasContrato()), 2, RoundingMode.HALF_UP);
    BigDecimal valorHora = valorDia.divide(new BigDecimal(8), 2, RoundingMode.HALF_UP);

    proyectoDB.setEstado(proyecto.getEstado());
    proyectoDB.setValorEntrega(proyecto.getValorEntrega());
    proyectoDB.setDiasContrato(proyecto.getDiasContrato());
    proyectoDB.setValorDia(valorDia);
    proyectoDB.setValorHora(valorHora);
    proyectoDB.setFechaInicio(proyecto.getFechaInicio());
    proyectoDB.setFechaFinalizacion(proyecto.getFechaFinalizacion());
    proyectoDB.setEstadoSolicitudModificacion(EstadoSolicitudModificar.NO_SOLICITADO.getValue());

    RegistroModificacion registroModificacion = crearRegistroModificacion(proyectoDB);
    this.registroModificacionRepository.save(registroModificacion);

    return this.proyectoRepository.save(proyectoDB);
  }

  public List<Proyecto> obtenerPorJefatura(Integer codJefatura) {
    String[] noEliminados = {EstadoSolicitudModificar.ELIMINADO.getValue()};
    return this.proyectoRepository
        .findByCodJefaturaAndEstadoSolicitudModificacionNotInAndEstadoOrderByNombre(
            codJefatura, noEliminados, EstadoGeneralEnum.ACTIVO.getValue());
  }

  public List<Proyecto> obtenerPorEstadoModificacion() {
    String[] estados = {
      EstadoSolicitudModificar.SOLICITADO.getValue(),
      EstadoSolicitudModificar.SOLICITADO_ELIMINAR.getValue()
    };
    return this.proyectoRepository
        .findByEstadoSolicitudModificacionInOrderByFechaSolicitudModificacion(estados);
  }

  public Proyecto modificarEstadoSolicitud(Proyecto proyecto) {
    Proyecto proyectoDB = this.buscarProyectoPorCodigo(proyecto.getCodProyecto());
    proyectoDB.setEstadoSolicitudModificacion(proyecto.getEstadoSolicitudModificacion());
    proyectoDB.setFechaSolicitudModificacion(new Date());
    proyectoDB.setComentarioSolicitudModificacion(proyecto.getComentarioSolicitudModificacion());
    return this.proyectoRepository.save(proyectoDB);
  }

  private Proyecto buscarProyectoPorCodigo(Integer codProyecto) throws NotFoundException {
    return this.proyectoRepository
        .findById(codProyecto)
        .orElseThrow(() -> new NotFoundException("Error, el proyecto no existe"));
  }

  private RegistroModificacion crearRegistroModificacion(Proyecto proyecto) {
    return RegistroModificacion.builder()
        .codRegistroModificado(proyecto.getCodProyecto())
        .codUsuario(proyecto.getCodUsuario())
        .tipoTabla(TipoTablaEnum.PROYECTO.getValue())
        .fechaModificacion(new Date())
        .comentario(proyecto.getComentarioSolicitudModificacion())
        .build();
  }

  private Empresa buscarEmpresaPorCodigo(Integer codEmpresa) {
    return this.empresaRepository
        .findById(codEmpresa)
        .orElseThrow(() -> new NotFoundException("Error, la empresa no existe"));
  }
}
