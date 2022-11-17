package ec.com.bisolutions.pmanager.actividades.dao;

import ec.com.bisolutions.pmanager.actividades.model.Jefatura;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JefaturaRepository extends JpaRepository<Jefatura, Integer> {
  Optional<Jefatura> findBySiglas(String siglas);
}
