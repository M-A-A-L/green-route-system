package br.upe.greenroute.view;
import br.upe.greenroute.controller.VehicleController;

import java.util.Scanner;
public class VehicleMenu extends BaseMenu{
    private final Scanner scanner;
    private VehicleView view;
    private VehicleController controller;
    public VehicleMenu(Scanner scanner, VehicleView vehicleView, VehicleController vehicleController) {
        this.scanner = scanner;
        this.view = vehicleView;
        this.controller = vehicleController;
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
            System.out.println("5. Listar veiculos");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> controller.addVehicle();
                case 2 -> controller.updateVehicle();
                case 3 -> controller.searchVehicleById();
                case 4 -> controller.deleteVehicleById();
                case 5 -> controller.listVehicles();
                case 0 -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> System.out.println("Digite uma opção válida!");
            }
        }
    }
}