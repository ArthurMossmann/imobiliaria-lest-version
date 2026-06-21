package Imobiliaria.Model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Pessoa(String nome, String cpf, String telefone, String email) {
        this.nome = nome; this.cpf = cpf; this.telefone = telefone; this.email = email;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    public void atualizarContato(String novoTelefone, String novoEmail) {
        this.telefone = novoTelefone;
        this.email = novoEmail;
    }

    public String obterResumoPessoa() {
        return "Nome: " + nome + " | Contato: " + telefone;
    }
}