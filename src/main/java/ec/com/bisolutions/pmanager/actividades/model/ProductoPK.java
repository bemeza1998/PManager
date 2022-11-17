package ec.com.bisolutions.pmanager.actividades.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductoPK implements Serializable {

  @EqualsAndHashCode.Include
  @Column(name = "COD_PRODUCTO", nullable = false)
  private Integer codProducto;

  @EqualsAndHashCode.Include
  @Column(name = "COD_USUARIO", nullable = false, length = 64)
  private String codUsuario;
}
