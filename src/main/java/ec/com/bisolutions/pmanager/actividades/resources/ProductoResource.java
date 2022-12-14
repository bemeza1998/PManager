package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.ProductoDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.ProductoMapper;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.services.ProductoService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/producto")
@RequiredArgsConstructor
public class ProductoResource {

  private final ProductoService service;

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @GetMapping
  public ResponseEntity<List<ProductoDTO>> obtenerProductos(@RequestParam String codUsuario) {
    List<Producto> productos = this.service.obtenerProductos(codUsuario);
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @GetMapping(path = "/productoqa/{codUsuario}/{codProducto}")
  public ResponseEntity<ProductoDTO> obtenerProductoQA(
      @PathVariable String codUsuario, @PathVariable Integer codProducto) {
    Producto producto = this.service.obtenerProductoQA(codUsuario, codProducto);
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @GetMapping(path = "/todos")
  public ResponseEntity<List<ProductoDTO>> obtenerProductosTodos(
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date semana) {
    List<Producto> productos = this.service.obtenerProductosTodos(semana);
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','REC','CAL')")
  @GetMapping(path = "/filtro")
  public ResponseEntity<List<ProductoDTO>> obtenerProductosPorFiltro(
      @RequestParam(required = false) String codUsuario,
      @RequestParam(required = false) Integer codProyecto,
      @RequestParam(required = false) String nombreCreador,
      @RequestParam(required = false) BigDecimal porcentaje,
      @RequestParam(required = false) Integer mes,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date semana,
      @RequestParam(required = false) String nombreProducto,
      @RequestParam(required = false) String estadoQa) {
    List<Producto> productos =
        this.service.obtenerPorFiltro(
            codUsuario,
            codProyecto,
            nombreCreador,
            porcentaje,
            mes,
            semana,
            nombreProducto,
            estadoQa);
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','JEF','ALP')")
  @GetMapping(path = "/estado")
  public ResponseEntity<List<ProductoDTO>> obtenerPorEstadoModificacion() {
    List<Producto> productos = this.service.obtenerPorEstadoModificacion();
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @PostMapping
  public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.crear(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @PutMapping
  public ResponseEntity<ProductoDTO> modificar(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificar(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @PutMapping(path = "/qa")
  public ResponseEntity<ProductoDTO> modificarQA(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificarQA(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','REC','JEF','ALP')")
  @PatchMapping
  public ResponseEntity<ProductoDTO> solicitarEstado(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.solicitarEstado(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','REC')")
  @PatchMapping(path = "/cronograma")
  public ResponseEntity<ProductoDTO> modificarCronograma(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificarCronograma(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  /*@PreAuthorize("hasAnyRole('ADM','REC')")
  @PatchMapping(path = "/observaciones")
  public ResponseEntity<ProductoDTO> modificarObservaciones(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificarObservaciones(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }*/

  @PreAuthorize("hasAnyRole('ADM','REC','CAL')")
  @PatchMapping(path = "/porcentaje/{codUsuario}")
  public ResponseEntity<ProductoDTO> modificarPorcentajeCumplimiento(
      @RequestBody ProductoDTO dto, @PathVariable String codUsuario) {
    try {
      Producto producto =
          this.service.modificarPorcentajeCumplimiento(
              ProductoMapper.buildProducto(dto), codUsuario);
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','CAL','REC')")
  @PatchMapping(path = "/estadoqa")
  public ResponseEntity<ProductoDTO> modificarEstadoQA(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificarEstadoQA(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PreAuthorize("hasAnyRole('ADM','CAL')")
  @PatchMapping(path = "/observacionqa")
  public ResponseEntity<ProductoDTO> modificarObservacionQA(@RequestBody ProductoDTO dto) {
    try {
      Producto producto = this.service.modificarObservacionQA(ProductoMapper.buildProducto(dto));
      return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
    } catch (ParseException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
