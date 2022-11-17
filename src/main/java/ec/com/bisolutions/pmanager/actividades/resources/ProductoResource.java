package ec.com.bisolutions.pmanager.actividades.resources;

import ec.com.bisolutions.pmanager.actividades.dto.ProductoDTO;
import ec.com.bisolutions.pmanager.actividades.mapper.ProductoMapper;
import ec.com.bisolutions.pmanager.actividades.model.Producto;
import ec.com.bisolutions.pmanager.actividades.services.ProductoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @GetMapping
  public ResponseEntity<List<ProductoDTO>> obtenerProductos(@RequestParam String codUsuario) {
    List<Producto> productos = this.service.obtenerProductos(codUsuario);
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @GetMapping(path = "/todos")
  public ResponseEntity<List<ProductoDTO>> obtenerProductosTodos() {
    List<Producto> productos = this.service.obtenerProductosTodos();
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @GetMapping(path = "/filtro")
  public ResponseEntity<List<ProductoDTO>> obtenerProductosPorFiltro(
      @RequestParam(required = false) Integer codProyecto,
      @RequestParam(required = false) String nombreCreador,
      @RequestParam(required = false) BigDecimal porcentaje,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date semana,
      @RequestParam(required = false) String nombreProducto) {
    List<Producto> productos =
        this.service.obtenerPorFiltro(
            codProyecto, nombreCreador, porcentaje, semana, nombreProducto);
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @GetMapping(path = "/estado")
  public ResponseEntity<List<ProductoDTO>> obtenerPorEstadoModificacion() {
    List<Producto> productos = this.service.obtenerPorEstadoModificacion();
    List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
    for (Producto producto : productos) {
      productosDTO.add(ProductoMapper.buildProductoDTO(producto));
    }
    return ResponseEntity.ok(productosDTO);
  }

  @PostMapping
  public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
    Producto producto = this.service.crear(ProductoMapper.buildProducto(dto));
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }

  @PutMapping
  public ResponseEntity<ProductoDTO> modificar(@RequestBody ProductoDTO dto) {
    Producto producto = this.service.modificar(ProductoMapper.buildProducto(dto));
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }

  @PutMapping(path = "/qa")
  public ResponseEntity<ProductoDTO> modificarQA(@RequestBody ProductoDTO dto) {
    Producto producto = this.service.modificarQA(ProductoMapper.buildProducto(dto));
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }

  @PatchMapping
  public ResponseEntity<ProductoDTO> solicitarEstado(@RequestBody ProductoDTO dto) {
    Producto producto = this.service.solicitarEstado(ProductoMapper.buildProducto(dto));
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }

  @PatchMapping(path = "/porcentaje")
  public ResponseEntity<ProductoDTO> modificarPorcentajeCumplimiento(@RequestBody ProductoDTO dto) {
    Producto producto =
        this.service.modificarPorcentajeCumplimiento(ProductoMapper.buildProducto(dto));
    return ResponseEntity.ok(ProductoMapper.buildProductoDTO(producto));
  }
}