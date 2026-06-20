package Imobiliaria.Util;

import Imobiliaria.Model.*;
import java.util.ArrayList;

public class Impressora {
    public void imprimirMenu() {
        System.out.println("\n┌──────────────────────────────────────────────────────────┐");
        System.out.println("│              PORTAL IMOBILIARIO - UNIRITTER              │");
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
            String status = definirStatusVisual(i, contratos);
            System.out.println("Matricula: " + i.getCodigo() + " | Status: [" + status + "] | Valor Base: R$ " + String.format("%,.2f", i.getValorBase()));
        }
    }

    public void imprimirDadosImovel(Imovel imovel, ArrayList<Contrato> contratos) {
        String status = definirStatusVisual(imovel, contratos);
        System.out.println("\n--- DADOS DO IMOVEL ---");
        System.out.println("Matricula: " + imovel.getCodigo() + " | Status: [" + status + "]");
        System.out.println("Valor Total OBR: R$ " + String.format("%,.2f", imovel.calcularValorTotal()));
        System.out.println("Endereco: " + imovel.getEndereco().getRua() + ", " + imovel.getEndereco().getNumero() + " - " + imovel.getEndereco().getCidade());
    }

    // Lógica para descobrir se está Alugado, Vendido ou Disponível
    private String definirStatusVisual(Imovel i, ArrayList<Contrato> contratos) {
        if (i.isDisponivel()) return "DISPONIVEL";

        for (Contrato c : contratos) {
            if (c.getImovel().getCodigo().equals(i.getCodigo())) {
                if (c instanceof ContratoLocacao) return "ALUGADO";
                if (c instanceof ContratoVenda) return "VENDIDO";
            }
        }
        return "INDISPONIVEL";
    }
}