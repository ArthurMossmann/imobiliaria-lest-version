package Imobiliaria.Model;

public class ImovelResidencial extends Imovel {
    private double condominio;
    private double iptu;
    private int quartos;
    private boolean possuiPiscina;

    public ImovelResidencial(String cod, Endereco end, double valorBase, double cond, double iptu, int quartos, boolean disp, String finalidade) {
        super(cod, end, valorBase, disp, finalidade);
        this.condominio = cond; this.iptu = iptu;
        this.quartos = quartos; this.possuiPiscina = false;
    }

    @Override
    public double calcularValorTotal() { return getValorBase() + condominio + iptu; }

    public void adicionarPiscina() { this.possuiPiscina = true; }
    public void reajustarCondominio(double percentual) { this.condominio += (this.condominio * percentual / 100); }
    public String classificarTamanho() { return quartos >= 3 ? "Amplo" : "Padrão"; }
}