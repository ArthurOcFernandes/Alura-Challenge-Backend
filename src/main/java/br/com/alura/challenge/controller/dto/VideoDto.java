package br.com.alura.challenge.controller.dto;

import br.com.alura.challenge.model.Video;

import java.util.List;

public class VideoDto {

    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Video v) {
        this.titulo = v.getTitulo();
        this.descricao = v.getDescricao();
        this.url = v.getUrl();
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
