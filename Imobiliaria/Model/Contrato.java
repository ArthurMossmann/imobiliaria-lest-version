package Imobiliaria.Model;

public abstract class Contrato {
    private int numeroContrato;
    private Imovel imovel;
    private String dataCriacao;
    private boolean ativo;
    private Vendedor proprietario; // NOVO: Guarda o dono do imóvel

    public Contrato(int numeroContrato, Imovel imovel, Vendedor proprietario) {
        this.numeroContrato = numeroContrato;
        this.imovel = imovel;
        this.proprietario = proprietario;
        this.dataCriacao = "Data Atual";
        this.ativo = true;
    }

    public int getNumeroContrato() { return numeroContrato; }
    public Imovel getImovel() { return imovel; }
    public Vendedor getProprietario() { return proprietario; } // NOVO

    public void cancelarContrato() { this.ativo = false; }
    public boolean isAtivo() { return ativo; }

    public abstract String obterTermosContrato();
}