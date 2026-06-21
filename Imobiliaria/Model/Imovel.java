package Imobiliaria.Model;

public abstract class Imovel implements Calculavel {
    private String codigo;
    private Endereco endereco;
    private double valorBase;
    private boolean disponivel;
    private String finalidade; // NOVO: Guarda se é para Locação, Venda ou Ambos

    public Imovel(String codigo, Endereco endereco, double valorBase, boolean disponivel, String finalidade) {
        this.codigo = codigo; this.endereco = endereco;
        this.valorBase = valorBase; this.disponivel = disponivel;
        this.finalidade = finalidade;
    }

    public String getCodigo() { return codigo; }
    public Endereco getEndereco() { return endereco; }
    public double getValorBase() { return valorBase; }
    public boolean isDisponivel() { return disponivel; }
    public String getFinalidade() { return finalidade; } // NOVO

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public void aplicarDesconto(double percentual) { this.valorBase -= (this.valorBase * percentual / 100); }
    public String exibirResumoLocalizacao() { return "Matrícula: " + codigo + " | Finalidade: " + finalidade; }

    @Override
    public abstract double calcularValorTotal();
}