package br.com.alura.challenge.controller;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.AtualizacaoVideoForm;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosRepository videosRepository;


    @GetMapping
    public ResponseEntity<List<VideoDto>> videos(@RequestParam Optional<String> search) {

        if(search.isEmpty()){
            return ResponseEntity.ok(VideoDto.parseList(videosRepository.findAll()));
        }

        List<Video> videos = videosRepository.findByTituloContainingIgnoreCase(search.get());
        if(videos.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(VideoDto.parseList(videos));

    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> video(@PathVariable Long id) {
        Optional<Video> video = videosRepository.findById(id);
        return video.map(value -> ResponseEntity.ok(new VideoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
        Video video = form.converter();
        if(video.getCategoriaId() == null) video.setCategoriaId(1L);
        videosRepository.save(video);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(VideoDto.parseVideo(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoVideoForm form) {
        Optional<Video> optional = videosRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Video video = form.atualizar(id, videosRepository);
        return ResponseEntity.ok(new VideoDto(video));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Video> video = videosRepository.findById(id);

        if (video.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
        }
        videosRepository.delete(video.get());
        return ResponseEntity.status(204).build();
    }


}
