package br.com.alura.challenge.controller;

import br.com.alura.challenge.controller.dto.CategoriaDto;
import br.com.alura.challenge.controller.dto.VideoCategoriaDTO;
import br.com.alura.challenge.controller.form.AtualizacaoCategoriaForm;
import br.com.alura.challenge.controller.form.CategoriaForm;
import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.repository.CategoriaRepository;
import br.com.alura.challenge.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private VideosRepository videosRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> exibeTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return ResponseEntity.ok(CategoriaDto.parseList(categorias));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> exibeUma(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(c -> ResponseEntity.ok(new CategoriaDto(c))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cria(@RequestBody @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriB) {

        Categoria c = categoriaForm.converter();
        categoriaRepository.save(c);

        URI uri = uriB.path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(c));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualiza(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm aCF) {
        Optional<Categoria> c = categoriaRepository.findById(id);

        if (c.isEmpty()) return ResponseEntity.notFound().build();

        Categoria categoria = aCF.atualiza(id, categoriaRepository);
        return ResponseEntity.ok(new CategoriaDto(categoria));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");

        categoriaRepository.delete(categoria.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoCategoriaDTO>> mostraFiltrado(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        List<VideoCategoriaDTO> videos = videosRepository.findByCategoriaId(id).
                stream().map(video -> new VideoCategoriaDTO(video, categoria.get())).
                toList();

        return ResponseEntity.ok(videos);
    }

}
