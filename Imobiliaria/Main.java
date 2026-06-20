package Imobiliaria;

import Imobiliaria.Model.*;
import Imobiliaria.Service.ImobiliariaService;
import Imobiliaria.Util.*;

public class Main {
    public static void main(String[] args) {
        ImobiliariaService servico = new ImobiliariaService();
        Leitor leitor = new Leitor();
        Impressora impressora = new Impressora();

        // --- Carga Inicial de Dados ---
        Endereco end = new Endereco("Rua dos Coqueiros", 1207, "Sao Lucas", "Viamao", "RS");
        ImovelResidencial imovelReal = new ImovelResidencial("38.257", end, 240000.00, 250.00, 100.00, 3, true);
        Vendedor vendReal = new Vendedor("Robinson Pereira", "000", "0", "0", "0", "0", "0", "Casado");

        try { servico.cadastrarImovel(imovelReal); } catch (Exception e) {}

        int opcao = -1;
        do {
            impressora.imprimirMenu();
            opcao = leitor.lerInteiro("👉 Selecione a acao operacional: ");

            switch (opcao) {
                case 1:
                    if(servico.getListaImoveis().isEmpty()) {
                        impressora.mensagemFeedback("INFO", "Sem imoveis cadastrados.");
                    } else {
                        impressora.imprimirPortfolio(servico.getListaImoveis(), servico.getListaContratos());
                    }
                    break;

                case 2:
                    System.out.println("\n--- CADASTRO DE IMOVEL ---");
                    int tipo = leitor.lerInteiro("Tipo (1-Residencial, 2-Comercial): ");
                    String cod = leitor.lerString("Matricula: ");
                    String rua = leitor.lerString("Rua: ");
                    int num = leitor.lerInteiro("Numero: ");
                    String cidade = leitor.lerString("Cidade: ");
                    double valor = leitor.lerDouble("Valor Base: ");

                    Endereco endNovo = new Endereco(rua, num, "Centro", cidade, "RS");

                    try {
                        if (tipo == 1) {
                            double cond = leitor.lerDouble("Condominio: ");
                            double iptu = leitor.lerDouble("IPTU: ");
                            int quartos = leitor.lerInteiro("Quartos: ");
                            servico.cadastrarImovel(new ImovelResidencial(cod, endNovo, valor, cond, iptu, quartos, true));
                        } else {
                            double taxa = leitor.lerDouble("Taxa de Alvara: ");
                            int salas = leitor.lerInteiro("Salas: ");
                            servico.cadastrarImovel(new ImovelComercial(cod, endNovo, valor, taxa, salas, true, "Geral"));
                        }
                        impressora.mensagemFeedback("SUCESSO", "Imovel cadastrado no portfolio.");
                    } catch (ImovelJaCadastradoException e) {
                        impressora.mensagemFeedback("ERRO", e.getMessage());
                    }
                    break;

                case 3:
                    String busca = leitor.lerString("\nDigite a Matricula: ");
                    Imovel i = servico.buscarImovel(busca);
                    if(i != null) {
                        impressora.imprimirDadosImovel(i, servico.getListaContratos());
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel nao encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- LOCACAO DE IMOVEL ---");
                    String matLoc = leitor.lerString("Matricula desejada: ");
                    String nomeLoc = leitor.lerString("Nome do Locatario: ");
                    double rendaLoc = leitor.lerDouble("Renda Comprovada (Exige 3x): ");
                    int nContratoLoc = leitor.lerInteiro("Numero do Contrato: ");

                    Locatario locatario = new Locatario(nomeLoc, "000", "0", "0", rendaLoc, "0", "0", "0");
                    if(servico.criarContratoLocacao(nContratoLoc, locatario, vendReal, matLoc)) {
                        impressora.mensagemFeedback("SUCESSO", "Contrato de Locacao Homologado!");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel ocupado, inexistente ou renda insuficiente.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- VENDA DE IMOVEL ---");
                    String matVenda = leitor.lerString("Matricula desejada: ");
                    String nomeComp = leitor.lerString("Nome do Comprador: ");
                    double rendaComp = leitor.lerDouble("Renda Comprovada: ");
                    int nContratoVenda = leitor.lerInteiro("Numero do Contrato: ");

                    Locatario comprador = new Locatario(nomeComp, "000", "0", "0", rendaComp, "0", "0", "0");
                    if(servico.criarContratoVenda(nContratoVenda, comprador, vendReal, matVenda)) {
                        impressora.mensagemFeedback("SUCESSO", "Contrato de Venda Definitiva Homologado!");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel ocupado ou inexistente.");
                    }
                    break;

                case 6:
                    if(servico.getListaContratos().isEmpty()) {
                        impressora.mensagemFeedback("INFO", "Nenhum contrato ativo no momento.");
                    } else {
                        System.out.println("\n--- CONTRATOS ATIVOS ---");
                        servico.getListaContratos().forEach(c ->
                                System.out.println("📄 Contrato Num: " + c.getNumeroContrato() + " | Mat: " + c.getImovel().getCodigo() + " | " + c.obterTermosContrato())
                        );
                    }
                    break;

                case 7:
                    int nResc = leitor.lerInteiro("\nNum do contrato para rescindir: ");
                    if(servico.rescindirContratoComMulta(nResc)) {
                        impressora.mensagemFeedback("SUCESSO", "Rescisao processada e imovel liberado!");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Contrato inexistente.");
                    }
                    break;

                case 8:
                    int nReaj = leitor.lerInteiro("\nNum contrato para reajuste: ");
                    double ind = leitor.lerDouble("Indice IGP-M %: ");
                    if(servico.aplicarReajusteIGPM(nReaj, ind)) {
                        impressora.mensagemFeedback("SUCESSO", "Reajuste aplicado no contrato.");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Falha no reajuste, contrato nao localizado.");
                    }
                    break;

                case 9:
                    String cExc = leitor.lerString("\nMatricula para excluir: ");
                    if(servico.excluirImovel(cExc)) {
                        impressora.mensagemFeedback("SUCESSO", "Imovel removido da base de dados.");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Matricula inexistente ou imovel esta vinculado a um contrato.");
                    }
                    break;

                case 0:
                    impressora.mensagemFeedback("FIM", "Encerrando software...");
                    break;

                default:
                    impressora.mensagemFeedback("AVISO", "Opcao invalida, tente novamente!");
            }
        } while (opcao != 0);
    }
}