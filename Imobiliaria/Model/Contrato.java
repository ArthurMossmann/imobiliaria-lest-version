package Imobiliaria.Model;

public abstract class Contrato {
    private int numeroContrato;
    private Imovel imovel;

    public Contrato(int numeroContrato, Imovel imovel) {
        this.numeroContrato = numeroContrato; this.imovel = imovel;
    }
    public int getNumeroContrato() { return numeroContrato; }
    public Imovel getImovel() { return imovel; }
    public abstract String obterTermosContrato();
}