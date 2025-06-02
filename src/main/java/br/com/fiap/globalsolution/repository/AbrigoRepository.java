package br.com.fiap.globalsolution.repository;

import br.com.fiap.globalsolution.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
}
