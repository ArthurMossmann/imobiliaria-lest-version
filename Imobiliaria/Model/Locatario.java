package Imobiliaria.Model;

public class Locatario extends Pessoa {
    private double rendaComprovada;
    private String profissao;
    private String estadoCivil;
    private boolean possuiRestricaoNome;

    public Locatario(String nome, String cpf, String tel, String email, double renda, String prof, String estCivil) {
        super(nome, cpf, tel, email);
        this.rendaComprovada = renda;
        this.profissao = prof;
        this.estadoCivil = estCivil;
        this.possuiRestricaoNome = false;
    }

    public double getRendaComprovada() { return rendaComprovada; }

    public void atualizarRenda(double novaRenda) { this.rendaComprovada = novaRenda; }

    public boolean verificarAprovacaoCredito() { return !possuiRestricaoNome && rendaComprovada > 1500; }

    public void registrarRestricao() { this.possuiRestricaoNome = true; }
}