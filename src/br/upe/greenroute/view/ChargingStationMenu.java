package br.upe.greenroute.view;
import java.util.Scanner;
public class ChargingStationMenu{
    private Scanner scanner;
    public ChargingStationMenu(Scanner scanner) {
        this.scanner = scanner;
    }
    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("=== Menu Eletropostos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar eletroposto");
            System.out.println("2. Atualizar eletroposto");
            System.out.println("3. Buscar eletroposto");
            System.out.println("4. Remover eletroposto");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            if (opcao == 1) {
                //cadastrar eletroposto
            } else if (opcao == 2) {
                //atualizar eletroposto
            } else if (opcao == 3) {
                //buscar eletroposto
            } else if (opcao == 4) {
                //remover eletroposto
            } else if (opcao == 0) {
                System.out.println("Voltando . . .");
                executando = false;
            } else {
                System.out.println("Digite uma opção válida!");
            }
        }
    }
}