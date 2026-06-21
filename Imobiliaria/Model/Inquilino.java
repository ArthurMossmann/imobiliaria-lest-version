package Imobiliaria.Model;

public class Inquilino extends Pessoa {
    private int mesesNoImovel;
    private boolean historicoBomPagador;
    private String contatoEmergencia;
    private boolean possuiPets;

    public Inquilino(String nome, String cpf, String tel, String email, String emergencia) {
        super(nome, cpf, tel, email);
        this.mesesNoImovel = 0;
        this.historicoBomPagador = true;
        this.contatoEmergencia = emergencia;
        this.possuiPets = false;
    }

    public void registrarMesPago() { this.mesesNoImovel++; }
    public void registrarAtraso() { this.historicoBomPagador = false; }
    public void permitirPet() { this.possuiPets = true; }
    public boolean avaliarRenovacao() { return historicoBomPagador && mesesNoImovel > 6; }
}