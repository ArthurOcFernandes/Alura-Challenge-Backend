package br.com.alura.challenge.repository;

import br.com.alura.challenge.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findById(long parseLong);
}
