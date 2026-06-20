package Imobiliaria.Model;

public class ImovelComercial extends Imovel {
    private double taxaAlvara;
    private int salas;

    public ImovelComercial(String cod, Endereco end, double valorBase, double taxaAlvara, int salas, boolean disp, String tipo) {
        super(cod, end, valorBase, disp);
        this.taxaAlvara = taxaAlvara; this.salas = salas;
    }
    @Override
    public double calcularValorTotal() { return getValorBase() + taxaAlvara; }
}