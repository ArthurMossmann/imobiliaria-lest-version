package Imobiliaria.Model;

public class Locatario {
    private String nome, cpf;
    private double rendaComprovada;

    public Locatario(String nome, String cpf, String rg, String end, double renda, String tel, String email, String prof) {
        this.nome = nome; this.cpf = cpf; this.rendaComprovada = renda;
    }
    public String getNome() { return nome; }
    public double getRendaComprovada() { return rendaComprovada; }
}