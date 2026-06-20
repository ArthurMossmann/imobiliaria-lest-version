package Imobiliaria.Model;

public class Endereco {
    private String rua, bairro, cidade, estado;
    private int numero;

    public Endereco(String rua, int numero, String bairro, String cidade, String estado) {
        this.rua = rua; this.numero = numero; this.bairro = bairro;
        this.cidade = cidade; this.estado = estado;
    }
    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
}