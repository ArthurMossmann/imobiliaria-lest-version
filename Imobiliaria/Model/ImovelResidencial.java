package Imobiliaria.Model;

public class ImovelResidencial extends Imovel {
    private double condominio, iptu;
    private int quartos;

    public ImovelResidencial(String cod, Endereco end, double valorBase, double cond, double iptu, int quartos, boolean disp) {
        super(cod, end, valorBase, disp);
        this.condominio = cond; this.iptu = iptu; this.quartos = quartos;
    }
    @Override
    public double calcularValorTotal() { return getValorBase() + condominio + iptu; }
}