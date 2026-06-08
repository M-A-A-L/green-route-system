package br.upe.greenroute.view;
import java.util.Scanner;
public class VehicleMenu {
    private Scanner scanner;
    private VehicleView view;
    public VehicleMenu(Scanner scanner, VehicleView vehicleView) {
        this.scanner = scanner;
        this.view = vehicleView;
    }
    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("=== Menu Veiculos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar veiculo");
            System.out.println("2. Atualizar veiculo");
            System.out.println("3. Buscar veiculo");
            System.out.println("4. Remover veiculo");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            if (opcao == 1) {
                view.requestDataForCreate();
            } else if (opcao == 2) {
                //atualizar veiculo
            } else if (opcao == 3) {
                //buscar veiculo
            } else if (opcao == 4) {
                //remover veiculo
            } else if (opcao == 0) {
                System.out.println("Voltando . . .");
                executando = false;
            } else {
                System.out.println("Digite uma opção válida!");
            }
        }
    }
}