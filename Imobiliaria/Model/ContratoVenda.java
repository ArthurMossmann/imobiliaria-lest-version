package Imobiliaria.Model;

public class ContratoVenda extends Contrato {
    private double valorVenda;

    public ContratoVenda(int num, Locatario comprador, Vendedor vendedor, Imovel imovel, double valor) {
        super(num, imovel);
        this.valorVenda = valor;
    }

    @Override
    public String obterTermosContrato() {
        return "Venda Definitiva no valor de: R$ " + String.format("%,.2f", valorVenda);
    }
}