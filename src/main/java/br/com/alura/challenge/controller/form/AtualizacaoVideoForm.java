package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.VideosRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class AtualizacaoVideoForm {

    @NotNull
    @NotEmpty
    @Length(min = 20, max = 200)
    private String titulo;
    @NotNull
    @Length(max = 300)
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Video atualizar(Long id, VideosRepository videosRepository) {
        Video video = videosRepository.getReferenceById(id);
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);

        return video;
    }
}
