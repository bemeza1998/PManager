package ec.com.bisolutions.pmanager.actividades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACT_EMPRESA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COD_EMPRESA", nullable = false)
  @EqualsAndHashCode.Include
  private Integer codEmpresa;

  @Column(name = "NOMBRE_EMPRESA", nullable = false, length = 128)
  private String nombreEmpresa;

  @Column(name = "DIRECCION", length = 512)
  private String direccion;

  @Column(name = "TELEFONO", length = 16)
  private String telefono;

  @Column(name = "MAIL", length = 64)
  private String mail;

  @Column(name = "CLIENTE_ACTIVO", length = 1, nullable = false)
  private String estado;
}
