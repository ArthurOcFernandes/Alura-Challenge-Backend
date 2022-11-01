package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.model.Categoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {

    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    @NotEmpty
    private String cor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria converter(){
        return new Categoria(titulo, cor);
    }
}
