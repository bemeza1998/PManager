package ec.com.bisolutions.pmanager.seguridad.model;

import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "SEG_USUARIO",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"MAIL"})})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include @EmbeddedId protected UsuarioPK pk;

  @Column(name = "COD_PERFIL", nullable = false, length = 3)
  private String codPerfil;

  @Column(name = "NOMBRE", nullable = false, length = 32)
  private String nombre;

  @Column(name = "APELLIDO", nullable = false, length = 32)
  private String apellido;

  @Column(name = "MAIL", nullable = false, length = 64)
  private String mail;

  @Column(name = "CLAVE", nullable = false, length = 128)
  private String clave;

  @Column(name = "ESTADO", nullable = false, length = 3)
  private String estado;

  @Column(name = "FECHA_CREACION", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;

  @Column(name = "FECHA_CAMBIO_CLAVE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCambioClave;

  @Column(name = "FECHA_ULTIMA_SESION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaUltimaSesion;

  // @OneToMany(mappedBy = "segUsuario")
  // private List<Proyecto> actProyectoList;

  // @OneToMany(cascade = CascadeType.ALL, mappedBy = "segUsuario")
  // private List<Producto> actProductoList;

  @JoinColumn(
      name = "COD_JEFATURA",
      referencedColumnName = "COD_JEFATURA",
      insertable = false,
      updatable = false)
  @ManyToOne(optional = false)
  private Jefatura jefatura;

  @JoinColumn(
      name = "COD_PERFIL",
      referencedColumnName = "COD_PERFIL",
      insertable = false,
      updatable = false)
  @ManyToOne
  private Perfil perfil;
}
