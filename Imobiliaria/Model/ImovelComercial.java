package Imobiliaria.Model;

public class ImovelComercial extends Imovel {
    private double taxaAlvara;
    private int salas;
    private String tipoComercio;
    private boolean possuiEstacionamento;

    public ImovelComercial(String cod, Endereco end, double valorBase, double taxa, int salas, boolean disp, String finalidade) {
        super(cod, end, valorBase, disp, finalidade);
        this.taxaAlvara = taxa; this.salas = salas;
        this.tipoComercio = "Geral"; this.possuiEstacionamento = true;
    }

    @Override
    public double calcularValorTotal() { return getValorBase() + taxaAlvara; }

    public void atualizarTaxaAlvara(double novaTaxa) { this.taxaAlvara = novaTaxa; }
    public void definirRestricaoComercio(String tipo) { this.tipoComercio = tipo; }
    public String avaliarCapacidade() { return salas > 5 ? "Grande Porte" : "Pequeno Porte"; }
}