package Imobiliaria.Util;
import java.util.Scanner;

public class Leitor {
    private Scanner sc = new Scanner(System.in);

    public String lerString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int lerInteiro(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim(); // Lê como texto e tira espaços em branco

            try {
                return Integer.parseInt(entrada); // Tenta converter para int
            } catch (NumberFormatException e) {
                System.out.println(">>> ERRO: Por favor, digite apenas numeros inteiros (ex: 12).");
            }
        }
    }

    public double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = sc.nextLine().trim(); // Lê como texto

            // Troca a vírgula por ponto para não dar erro
            entrada = entrada.replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println(">>> ERRO: Por favor, digite um valor valido (ex: 1500.50).");
            }
        }
    }
}