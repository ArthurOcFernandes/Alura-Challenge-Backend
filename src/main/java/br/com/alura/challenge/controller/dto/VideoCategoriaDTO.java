package br.com.alura.challenge.controller.dto;

import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.model.Video;

public class VideoCategoriaDTO {

    private Long id;
    private String titulo;
    private String categoria;
    private String descricao;
    private String url;

    public VideoCategoriaDTO(Video video, Categoria categoria){
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = categoria.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }
}
