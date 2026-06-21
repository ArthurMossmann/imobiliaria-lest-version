package Imobiliaria;

import Imobiliaria.Model.*;
import Imobiliaria.Service.ImobiliariaService;
import Imobiliaria.Util.*;

public class Main {
    public static void main(String[] args) {
        ImobiliariaService servico = new ImobiliariaService();
        Leitor leitor = new Leitor();
        Impressora impressora = new Impressora();

        Endereco end = new Endereco("Rua dos Coqueiros", 1207, "Sao Lucas", "Viamao", "RS");
        ImovelResidencial imovelReal = new ImovelResidencial("38.257", end, 240000.00, 250.00, 100.00, 3, true, "Venda");
        Vendedor vendReal = new Vendedor("Robinson Pereira", "000", "5199999", "email@.com", "Banco do Brasil", "0123", "456-7");

        try { servico.cadastrarImovel(imovelReal); } catch (Exception e) {}

        int opcao = -1;
        do {
            impressora.imprimirMenu();
            opcao = leitor.lerInteiro("👉 Selecione a acao operacional: ");

            switch (opcao) {
                case 1:
                    if(servico.getListaImoveis().isEmpty()) impressora.mensagemFeedback("INFO", "Sem imoveis.");
                    else impressora.imprimirPortfolio(servico.getListaImoveis(), servico.getListaContratos());
                    break;

                case 2:
                    System.out.println("\n--- CADASTRO DE IMOVEL ---");
                    int tipo = leitor.lerInteiro("Tipo (1-Resid, 2-Comerc): ");
                    String cod = leitor.lerString("Matricula: ");
                    Endereco endNovo = new Endereco(leitor.lerString("Rua: "), leitor.lerInteiro("Num: "), "Bairro", "Cidade", "UF");
                    double valor = leitor.lerDouble("Valor Base: ");

                    int opFinalidade = leitor.lerInteiro("Finalidade (1-Locacao, 2-Venda, 3-Ambos): ");

                    String finalidadeEscolhida = (opFinalidade == 1) ? "Locacao" : (opFinalidade == 2) ? "Venda" : "Ambos";

                    try {
                        if (tipo == 1) {
                            servico.cadastrarImovel(new ImovelResidencial(cod, endNovo, valor, 100, 50, 2, true, finalidadeEscolhida));
                        } else {
                            servico.cadastrarImovel(new ImovelComercial(cod, endNovo, valor, 200, 4, true, finalidadeEscolhida));
                        }
                        impressora.mensagemFeedback("SUCESSO", "Cadastrado no portfolio como: " + finalidadeEscolhida);
                    } catch (ImovelJaCadastradoException e) {
                        e.gerarLogNoConsole();
                    }
                    break;

                case 3:
                    String busca = leitor.lerString("\nDigite a Matricula: ");
                    Imovel iBuscar = servico.buscarImovel(busca);

                    if(iBuscar != null) {
                        impressora.imprimirDadosImovel(iBuscar, servico.getListaContratos());
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel nao encontrado na base de dados.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- DADOS DO CLIENTE (LOCATARIO) ---");
                    String matLoc = leitor.lerString("Matricula do Imovel: ");
                    String nomeLoc = leitor.lerString("Nome Completo: ");
                    String cpfLoc = leitor.lerString("CPF: ");
                    String telLoc = leitor.lerString("Telefone: ");
                    String emailLoc = leitor.lerString("Email: ");
                    double rendaLoc = leitor.lerDouble("Renda Comprovada (Exige 3x o aluguel): ");
                    String profLoc = leitor.lerString("Profissao: ");
                    String estCivilLoc = leitor.lerString("Estado Civil: ");

                    Locatario loc = new Locatario(nomeLoc, cpfLoc, telLoc, emailLoc, rendaLoc, profLoc, estCivilLoc);

                    int numContrato = leitor.lerInteiro("Numero do Contrato: ");
                    double valorAluguel = leitor.lerDouble("Valor do Aluguel Acordado: R$ "); // NOVO PEDIDO

                    // Passando o valorAluguel para o Service
                    if(servico.criarContratoLocacao(numContrato, loc, vendReal, matLoc, valorAluguel)) {
                        impressora.mensagemFeedback("SUCESSO", "Contrato de Locacao Homologado!");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel indisponivel OU Renda insuficiente para este aluguel.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- DADOS DO CLIENTE (COMPRADOR) ---");
                    String matVenda = leitor.lerString("Matricula do Imovel: ");
                    String nomeComp = leitor.lerString("Nome Completo: ");
                    String cpfComp = leitor.lerString("CPF: ");
                    String telComp = leitor.lerString("Telefone: ");
                    String emailComp = leitor.lerString("Email: ");
                    double rendaComp = leitor.lerDouble("Renda Comprovada (Analise de Credito): ");
                    String profComp = leitor.lerString("Profissao: ");
                    String estCivilComp = leitor.lerString("Estado Civil: ");

                    Locatario comp = new Locatario(nomeComp, cpfComp, telComp, emailComp, rendaComp, profComp, estCivilComp);

                    int numContratoV = leitor.lerInteiro("Numero do Contrato: ");

                    if(servico.criarContratoVenda(numContratoV, comp, vendReal, matVenda)) {
                        impressora.mensagemFeedback("SUCESSO", "Venda Aprovada e Imovel Vendido!");
                    } else {
                        impressora.mensagemFeedback("ERRO", "Imovel indisponivel OU Renda reprovada no financiamento.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- CONTRATOS ATIVOS ---");
                    boolean temContrato = false;
                    for (Contrato c : servico.getListaContratos()) {
                        if (c.isAtivo()) {
                            System.out.println("Contrato Num: " + c.getNumeroContrato() + " | Mat: " + c.getImovel().getCodigo() + " | " + c.obterTermosContrato());
                            temContrato = true;
                        }
                    }
                    if (!temContrato) {
                        impressora.mensagemFeedback("INFO", "Nenhum contrato ativo no momento.");
                    }
                    break;

                case 7:
                    if(servico.rescindirContratoComMulta(leitor.lerInteiro("\nNum rescisao: "))) impressora.mensagemFeedback("SUCESSO", "Rescindido!");
                    else impressora.mensagemFeedback("ERRO", "Inexistente.");
                    break;

                case 8:
                    if(servico.aplicarReajusteIGPM(leitor.lerInteiro("Num reajuste: "), leitor.lerDouble("Indice %: "))) impressora.mensagemFeedback("SUCESSO", "Reajustado.");
                    break;

                case 9:
                    if(servico.excluirImovel(leitor.lerString("\nMatricula: "))) impressora.mensagemFeedback("SUCESSO", "Apagado.");
                    else impressora.mensagemFeedback("ERRO", "Impedido.");
                    break;

                case 0:
                    leitor.exibirEstatisticaLeitura();
                    impressora.mensagemFeedback("FIM", "Saindo...");
                    break;
            }
        } while (opcao != 0);
    }
}