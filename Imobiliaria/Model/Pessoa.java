package Imobiliaria.Model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String rg;
    private String enderecoTexto;

    public Pessoa(String nome, String cpf, String rg, String enderecoTexto) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.enderecoTexto = enderecoTexto;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getRg() { return rg; }
    public String getEnderecoTexto() { return enderecoTexto; }
}