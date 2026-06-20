package Imobiliaria.Model;

public class ContratoLocacao extends Contrato {
    private double valorContrato;
    public ContratoLocacao(int num, Locatario l, Vendedor v, Imovel i, double valor) {
        super(num, i);
        this.valorContrato = valor;
    }
    @Override
    public String obterTermosContrato() { return "Locacao no valor de: R$ " + valorContrato; }
}