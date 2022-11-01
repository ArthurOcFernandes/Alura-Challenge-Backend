package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.model.Video;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class VideoForm {
    @NotNull
    @NotEmpty @Length(min=20, max = 200)
    private String titulo;
    @NotNull @Length(max = 300)
    private String descricao;
    @NotNull @NotEmpty @Length(min=20, max = 200)
    private String url;
    private Long categoriaId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video converter() {
        return new Video(titulo, descricao, url, categoriaId);
    }
}
