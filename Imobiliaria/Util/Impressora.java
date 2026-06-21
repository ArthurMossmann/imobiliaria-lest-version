package Imobiliaria.Util;

import Imobiliaria.Model.*;
import java.util.ArrayList;

public class Impressora {
    private String nomeSistema = "PORTAL IMOBILIARIO - UNIRITTER";
    private String versao = "v3.0";
    private String caractereBorda = "─";
    private boolean modoDetalhado = true;

    public void imprimirMenu() {
        System.out.println("\n┌──────────────────────────────────────────────────────────┐");
        System.out.println("│              " + nomeSistema + "              │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.println("│  [1] Listar Portfolio    [2] Cadastrar    [3] Buscar     │");
        System.out.println("│  [4] Locacao             [5] Venda        [6] Contratos  │");
        System.out.println("│  [7] Rescindir           [8] Reajuste     [9] Excluir    │");
        System.out.println("│  [0] Sair do Sistema                                     │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    public void mensagemFeedback(String tag, String msg) {
        System.out.println("\n>>> " + tag.toUpperCase() + ": " + msg);
    }

    public void imprimirPortfolio(ArrayList<Imovel> lista, ArrayList<Contrato> contratos) {
        System.out.println("\n--- PORTFOLIO DE IMOVEIS ---");
        for (Imovel i : lista) {
            System.out.println("Matricula: " + i.getCodigo() + " | Status: [" + definirStatusVisual(i, contratos) + "] | Valor: R$ " + i.getValorBase());

            Contrato contratoAtivo = null;
            for (Contrato c : contratos) {
                if (c.getImovel().getCodigo().equals(i.getCodigo()) && c.isAtivo()) {
                    contratoAtivo = c;
                    break;
                }
            }

            if (contratoAtivo != null) {
                if (i instanceof ImovelResidencial) {
                    // Residencial: Mostra SÓ o Dono
                    System.out.println("   └─ Dono/Proprietario: " + contratoAtivo.getProprietario().getNome());
                }
                else if (i instanceof ImovelComercial) {
                    // Comercial: Mostra o Dono E o Inquilino/Comprador
                    System.out.println("   └─ Dono/Proprietario: " + contratoAtivo.getProprietario().getNome());

                    if (contratoAtivo instanceof ContratoLocacao) {
                        System.out.println("   └─ Inquilino: " + ((ContratoLocacao) contratoAtivo).getLocatario().getNome());
                    } else if (contratoAtivo instanceof ContratoVenda) {
                        System.out.println("   └─ Comprador: " + ((ContratoVenda) contratoAtivo).getComprador().getNome());
                    }
                }
            }
        }
    }

    public void imprimirDadosImovel(Imovel imovel, ArrayList<Contrato> contratos) {
        System.out.println("\n--- DADOS DETALHADOS DO IMOVEL ---");
        System.out.println("Matricula: " + imovel.getCodigo());
        System.out.println("Endereco: " + imovel.getEndereco().formatarEnderecoCompleto());
        System.out.println("Valor Base: R$ " + imovel.getValorBase());
        System.out.println("Valor Total Calculado: R$ " + imovel.calcularValorTotal());
        System.out.println("Status: [" + definirStatusVisual(imovel, contratos) + "]");

        // Puxa as informações de Dono e Inquilino se estiver alugado/vendido
        for (Contrato c : contratos) {
            if (c.getImovel().getCodigo().equals(imovel.getCodigo()) && c.isAtivo()) {
                System.out.println("Dono/Proprietario: " + c.getProprietario().getNome());

                if (imovel instanceof ImovelComercial) {
                    if (c instanceof ContratoLocacao) {
                        System.out.println("Inquilino: " + ((ContratoLocacao) c).getLocatario().getNome());
                    } else if (c instanceof ContratoVenda) {
                        System.out.println("Comprador: " + ((ContratoVenda) c).getComprador().getNome());
                    }
                }
                break;
            }
        }
    }

    private String definirStatusVisual(Imovel i, ArrayList<Contrato> contratos) {
        if (i.isDisponivel()) return "DISPONIVEL";
        for (Contrato c : contratos) {
            if (c.getImovel().getCodigo().equals(i.getCodigo()) && c.isAtivo()) {
                if (c instanceof ContratoLocacao) return "ALUGADO";
                if (c instanceof ContratoVenda) return "VENDIDO";
            }
        }
        return "INDISPONIVEL";
    }
}