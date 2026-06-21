package Imobiliaria.Util;
import java.util.Scanner;

public class Leitor {
    private Scanner sc = new Scanner(System.in);
    private int totalLeiturasRealizadas = 0;
    private int contadorErros = 0;
    private String ultimaAcao = "";

    public String lerString(String msg) {
        System.out.print(msg);
        totalLeiturasRealizadas++;
        return sc.nextLine();
    }

    public int lerInteiro(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim();
            totalLeiturasRealizadas++;
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                contadorErros++;
                System.out.println(">>> ERRO: Digite apenas números inteiros.");
            }
        }
    }

    public double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim().replace(",", ".");
            totalLeiturasRealizadas++;
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                contadorErros++;
                System.out.println(">>> ERRO: Digite um valor válido (ex: 1500.50).");
            }
        }
    }

    public void exibirEstatisticaLeitura() {
        System.out.println("Leituras: " + totalLeiturasRealizadas + " | Erros evitados: " + contadorErros);
    }
}