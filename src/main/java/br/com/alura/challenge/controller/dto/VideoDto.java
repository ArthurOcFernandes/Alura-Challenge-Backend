package br.com.alura.challenge.controller.dto;

import br.com.alura.challenge.model.Video;

import java.util.List;

public class VideoDto {

    private Long id;
    private Long categoriaId;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoriaId();
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public static List<VideoDto> parseList(List<Video> videos){
        return videos.stream().map(VideoDto::new).toList();
    }
    public static VideoDto parseVideo(Video video){
        return new VideoDto(video);
    }


}
