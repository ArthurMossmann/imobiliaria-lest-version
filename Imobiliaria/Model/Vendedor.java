package Imobiliaria.Model;

public class Vendedor extends Pessoa {
    private String banco;
    private String agencia;
    private String conta;
    private double comissaoAcumulada;

    public Vendedor(String nome, String cpf, String tel, String email, String banco, String ag, String conta) {
        super(nome, cpf, tel, email);
        this.banco = banco; this.agencia = ag; this.conta = conta;
        this.comissaoAcumulada = 0.0;
    }

    public void adicionarComissao(double valor) { this.comissaoAcumulada += valor; }
    public void zerarComissao() { this.comissaoAcumulada = 0.0; }
    public String obterDadosBancarios() { return banco + " | Ag: " + agencia + " | CC: " + conta; }
    public double getComissaoAcumulada() { return comissaoAcumulada; }
}