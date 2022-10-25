package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.model.Video;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


public class VideoForm {
    @NotNull @NotEmpty @Length(min=20, max = 200)
    private String titulo;
    @NotNull @Length(max = 300)
    private String descricao;
    @NotNull @NotEmpty @Length(min=20, max = 200)
    private String url;

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

    public Video converter() {
        return new Video(titulo, descricao, url);
    }
}
