package Imobiliaria.Service;

import Imobiliaria.Model.*;
import Imobiliaria.Util.*;
import java.util.ArrayList;

public class ImobiliariaService {
    private ArrayList<Imovel> listaImoveis = new ArrayList<>();
    private ArrayList<Contrato> listaContratos = new ArrayList<>();
    private String razaoSocial = "Imobiliaria Uniritter LTDA";
    private String cnpjResponsavel = "12.345.678/0001-99";

    public void cadastrarImovel(Imovel i) throws ImovelJaCadastradoException {
        if(buscarImovel(i.getCodigo()) != null) throw new ImovelJaCadastradoException("Matricula duplicada!");
        listaImoveis.add(i);
    }

    public Imovel buscarImovel(String cod) {
        for (Imovel i : listaImoveis) if (i.getCodigo().equals(cod)) return i;
        return null;
    }

    public boolean criarContratoLocacao(int num, Locatario l, Vendedor v, String cod, double valorAluguelAcordado) {
        Imovel i = buscarImovel(cod);

        if (i == null || !i.isDisponivel()) return false;

        if (i.getFinalidade().equalsIgnoreCase("Venda")) return false;

        if (l.getRendaComprovada() < (valorAluguelAcordado * 3)) return false;

        listaContratos.add(new ContratoLocacao(num, l, v, i, valorAluguelAcordado));
        i.setDisponivel(false);
        return true;
    }

    public boolean criarContratoVenda(int num, Locatario comprador, Vendedor v, String cod) {
        Imovel i = buscarImovel(cod);
        if (i == null || !i.isDisponivel()) return false;

        if (i.getFinalidade().equalsIgnoreCase("Locacao")) return false;

        double valorDaVenda = i.calcularValorTotal();
        if (comprador.getRendaComprovada() < (valorDaVenda * 0.01)) return false;

        ContratoVenda cv = new ContratoVenda(num, comprador, v, i, valorDaVenda);
        cv.autorizarComissao();
        listaContratos.add(cv);
        i.setDisponivel(false);
        return true;
    }

    public boolean rescindirContratoComMulta(int num) {
        for (Contrato c : listaContratos) {
            if (c.getNumeroContrato() == num && c.isAtivo()) {
                c.cancelarContrato(); // Usa o método novo
                c.getImovel().setDisponivel(true);
                return true;
            }
        }
        return false;
    }

    public boolean aplicarReajusteIGPM(int num, double ind) { return true; }

    public boolean excluirImovel(String cod) {
        Imovel i = buscarImovel(cod);
        if (i != null && i.isDisponivel()) {
            listaImoveis.remove(i);
            return true;
        }
        return false;
    }

    public ArrayList<Imovel> getListaImoveis() { return listaImoveis; }
    public ArrayList<Contrato> getListaContratos() { return listaContratos; }
}