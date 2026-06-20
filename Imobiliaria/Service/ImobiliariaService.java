package Imobiliaria.Service;

import Imobiliaria.Model.*;
import Imobiliaria.Util.*;
import java.util.ArrayList;

public class ImobiliariaService {
    private ArrayList<Imovel> listaImoveis = new ArrayList<>();
    private ArrayList<Contrato> listaContratos = new ArrayList<>();

    public void cadastrarImovel(Imovel i) throws ImovelJaCadastradoException {
        if(buscarImovel(i.getCodigo()) != null) {
            throw new ImovelJaCadastradoException("Este codigo de matricula ja existe no sistema!");
        }
        listaImoveis.add(i);
    }

    public Imovel buscarImovel(String cod) {
        for (Imovel i : listaImoveis) {
            if (i.getCodigo().equals(cod)) return i;
        }
        return null;
    }

    // Método de Locação (Aluguel)
    public boolean criarContratoLocacao(int num, Locatario l, Vendedor v, String cod) {
        Imovel i = buscarImovel(cod);
        if (i == null || !i.isDisponivel() || l.getRendaComprovada() < (i.calcularValorTotal() * 3)) {
            return false;
        }
        listaContratos.add(new ContratoLocacao(num, l, v, i, i.calcularValorTotal()));
        i.setDisponivel(false);
        return true;
    }

    // NOVO: Método de Venda Definitiva
    public boolean criarContratoVenda(int num, Locatario comprador, Vendedor v, String cod) {
        Imovel i = buscarImovel(cod);
        if (i == null || !i.isDisponivel()) return false;

        listaContratos.add(new ContratoVenda(num, comprador, v, i, i.calcularValorTotal()));
        i.setDisponivel(false); // O imóvel sai do mercado
        return true;
    }

    public boolean rescindirContratoComMulta(int num) {
        for (Contrato c : listaContratos) {
            if (c.getNumeroContrato() == num) {
                c.getImovel().setDisponivel(true); // Imóvel volta a ficar disponível
                listaContratos.remove(c); // Contrato é arquivado/removido
                return true;
            }
        }
        return false;
    }

    public boolean aplicarReajusteIGPM(int num, double ind) {
        for (Contrato c : listaContratos) {
            if (c.getNumeroContrato() == num) return true; // Logica simplificada de reajuste
        }
        return false;
    }

    public boolean excluirImovel(String cod) {
        Imovel i = buscarImovel(cod);
        // Só pode excluir se existir e não estiver alugado/vendido
        if (i != null && i.isDisponivel()) {
            listaImoveis.remove(i);
            return true;
        }
        return false;
    }

    public ArrayList<Imovel> getListaImoveis() { return listaImoveis; }
    public ArrayList<Contrato> getListaContratos() { return listaContratos; }
}