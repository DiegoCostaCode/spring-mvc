package br.com.fiap.spring_mvc.model;


public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    TERROR("Terror"),
    DRAMA("Drama"),
    ACAO("Ação");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
