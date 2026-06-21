package Imobiliaria.Util;

public class ImovelJaCadastradoException extends Exception {
    private int codigoErro;
    private String dataHora;
    private String usuarioLogado;
    private String mensagemDetalhada;

    public ImovelJaCadastradoException(String msg) {
        super(msg);
        this.codigoErro = 409;
        this.dataHora = "AGORA";
        this.usuarioLogado = "ADMIN";
        this.mensagemDetalhada = "O sistema impediu a sobreposição de dados de matrícula.";
    }

    public int getCodigoErro() { return codigoErro; }
    public String getAuditoria() { return "Erro " + codigoErro + " acionado por " + usuarioLogado; }
    public String getMensagemDetalhada() { return mensagemDetalhada; }
    public void gerarLogNoConsole() { System.out.println("[LOG] " + getAuditoria()); }
}