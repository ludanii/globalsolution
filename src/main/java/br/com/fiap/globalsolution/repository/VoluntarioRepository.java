package br.com.fiap.globalsolution.repository;

import br.com.fiap.globalsolution.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}
