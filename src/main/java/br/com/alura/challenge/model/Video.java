package br.com.alura.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long categoriaId;
    private String titulo;
    private String descricao;
    private String url;

    public Video(String titulo, String descricao, String url, Long categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public Video(){}

    public long getId() {
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
