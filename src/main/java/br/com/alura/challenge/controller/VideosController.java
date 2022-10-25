package br.com.alura.challenge.controller;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.AtualizacaoVideoForm;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VideoDto> videos() {
        return VideoDto.parseList(videosRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> video(@PathVariable Long id) {
        Optional<Video> video = videosRepository.findById(id);
        if(!video.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new VideoDto(video.get()));

    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
        Video video = form.converter();
        videosRepository.save(video);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(VideoDto.parseVideo(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoVideoForm form) {
        Optional<Video> optional = videosRepository.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Video video = form.atualizar(id, videosRepository);
        return ResponseEntity.ok(new VideoDto(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Video> video = videosRepository.findById(id);
        if(!video.isPresent()){
            return ResponseEntity.notFound().build();
        }
        videosRepository.delete(video.get());
        return ResponseEntity.ok().build();
    }

}
