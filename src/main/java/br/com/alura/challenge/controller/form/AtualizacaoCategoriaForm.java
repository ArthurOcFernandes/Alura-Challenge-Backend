package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoCategoriaForm {

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

    public Categoria atualiza(Long id, CategoriaRepository cr){

        Categoria c = cr.getReferenceById(id);
        c.setTitulo(titulo);
        c.setCor(cor);

        return c;

    }

}
