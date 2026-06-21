package Imobiliaria.Model;

public class ContratoLocacao extends Contrato {
    private double valorAluguelMensal;
    private Locatario locatario;
    private int duracaoMeses;
    private double taxaAdministrativa;

    public ContratoLocacao(int num, Locatario l, Vendedor v, Imovel i, double valor) {
        super(num, i, v);
        this.locatario = l;
        this.valorAluguelMensal = valor;
        this.duracaoMeses = 12;
        this.taxaAdministrativa = 10.0;
    }

    public Locatario getLocatario() { return locatario; } // NOVO: Para a impressora puxar o nome

    @Override
    public String obterTermosContrato() { return "Locação: R$ " + valorAluguelMensal + " mensais."; }

    public void prorrogarContrato(int mesesExtras) { this.duracaoMeses += mesesExtras; }
    public double calcularCustoTotalAnual() { return valorAluguelMensal * 12; }
    public void isentarTaxaAdministrativa() { this.taxaAdministrativa = 0.0; }
}