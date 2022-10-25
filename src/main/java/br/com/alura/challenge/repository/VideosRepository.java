package br.com.alura.challenge.repository;

import br.com.alura.challenge.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Video, Long> {
    Video findById(long parseLong);
}
