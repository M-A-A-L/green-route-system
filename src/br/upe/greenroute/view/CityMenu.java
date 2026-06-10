package br.upe.greenroute.view;
import java.util.Scanner;
public class CityMenu{
    private Scanner scanner;
    public CityMenu(Scanner scanner) {
        this.scanner = scanner;
    }
    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("=== Menu Cidades ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar cidade");
            System.out.println("2. Atualizar cidade");
            System.out.println("3. Buscar cidade");
            System.out.println("4. Remover cidade");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            if (opcao == 1) {
                //cadastrar cidade
            } else if (opcao == 2) {
                //atualizar cidade
            } else if (opcao == 3) {
                //buscar cidade
            } else if (opcao == 4) {
                //remover cidade
            } else if (opcao == 0) {
                System.out.println("Voltando . . .");
                executando = false;
            } else {
                System.out.println("Digite uma opção válida!");
            }
        }
    }
}