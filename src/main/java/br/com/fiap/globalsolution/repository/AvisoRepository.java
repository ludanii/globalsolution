package br.com.fiap.globalsolution.repository;

import br.com.fiap.globalsolution.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {
}
