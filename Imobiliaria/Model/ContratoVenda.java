package Imobiliaria.Model;

public class ContratoVenda extends Contrato {
    private double valorVenda;
    private Locatario comprador;
    private String metodoPagamento;

    public ContratoVenda(int num, Locatario comprador, Vendedor vendedor, Imovel imovel, double valor) {
        super(num, imovel, vendedor);
        this.valorVenda = valor;
        this.comprador = comprador;
        this.metodoPagamento = "À Vista";
    }

    public Locatario getComprador() { return comprador; } // NOVO: Para a impressora puxar o nome

    @Override
    public String obterTermosContrato() { return "Venda: R$ " + valorVenda; }

    public void alterarMetodoPagamento(String metodo) { this.metodoPagamento = metodo; }
    public double calcularImpostoITBI() { return valorVenda * 0.03; }
    public void autorizarComissao() { getProprietario().adicionarComissao(valorVenda * 0.05); }
}