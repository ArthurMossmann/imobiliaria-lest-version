package Imobiliaria.Model;

public class Inquilino extends Pessoa {
    private String dataNascimento;
    private String ocupacao;
    private String celular;
    private boolean possuiPets;

    public Inquilino(String nome, String cpf, String rg, String enderecoTexto, String dataNascimento, String ocupacao, String celular, boolean possuiPets) {
        super(nome, cpf, rg, enderecoTexto);
        this.dataNascimento = dataNascimento;
        this.ocupacao = ocupacao;
        this.celular = celular;
        this.possuiPets = possuiPets;
    }
}