package Imobiliaria.Model;

public abstract class Imovel {
    private String codigo;
    private Endereco endereco;
    private double valorBase;
    private boolean disponivel;

    public Imovel(String codigo, Endereco endereco, double valorBase, boolean disponivel) {
        this.codigo = codigo; this.endereco = endereco;
        this.valorBase = valorBase; this.disponivel = disponivel;
    }
    public String getCodigo() { return codigo; }
    public Endereco getEndereco() { return endereco; }
    public double getValorBase() { return valorBase; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public abstract double calcularValorTotal();
}