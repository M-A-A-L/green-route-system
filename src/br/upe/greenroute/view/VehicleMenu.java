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
            switch (opcao) {
                case 1 -> requestDataForCreate();
                case 2 -> requestDataForUpdate();
                case 3 -> requestDataForRead();
                case 4 -> requestDataForDelete();
                case 5 -> controller.listVehicles();
                case 0 -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> System.out.println("Digite uma opção válida!");
            }
        }
    }

    private int requestVehicleType() {
        System.out.println("Digite o tipo de veiculo:");
        System.out.println("1-Eletrico");
        System.out.println("2-Hibrido");
        int type = scanner.nextInt();
        scanner.nextLine();
        if (type == 1 || type == 2) {
            return type;
        }else {
            view.displayError("Deve digitar 1 para eletricos ou 2 para hibridos\n");
            return requestVehicleType();
        }
    }
    @Override
    public void requestDataForCreate() {
        int type = requestVehicleType();
        System.out.println("Digite o modelo do veiculo: ");
        String model = scanner.nextLine();
        System.out.println("Digite a autonomia máxima do veiculo: ");
        String maximumAutonomyStr = scanner.nextLine();
        System.out.println("Digite o carga atual da bateria do veiculo: ");
        String currentBatteryChargeStr = scanner.nextLine();
        System.out.println("Digite o consumo (kWh/Km) do veiculo: ");
        String consumptionKWhPerKmStr = scanner.nextLine();
        System.out.println("Digite o tempo de recarga (em minutos) do veiculo: ");
        String fullRechargeTimeStr = scanner.nextLine();
        switch (type) {
            case 1 -> requestDataForCreateElectricVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr);
            case 2 -> requestDataForCreateHybridVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr);
        }
    }

    private void requestDataForCreateElectricVehicle(String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        System.out.println("Digite o tipo de conector: )");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
        String fastChargingStr = scanner.nextLine();
        controller.addElectricVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr, connectorType, fastChargingStr);
    }
    private void requestDataForCreateHybridVehicle(String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        System.out.println("Digite a capacidade do tanque de combustivel (em litros): ");
        String fuelTankCapacityStr = scanner.nextLine();
        System.out.println("Digite o consumo de combustivel (em Km/l) do motor a combustão: ");
        String fuelConsumptionStr = scanner.nextLine();
        System.out.println("Digite o tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        controller.addHybridVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr, fuelTankCapacityStr, fuelConsumptionStr, fuelType);
    }
    @Override
    public void requestDataForRead() {
        System.out.println("Digite o id do veiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.searchVehicleById(id);
    }
    @Override
    public void requestDataForUpdate() {
        System.out.println("Digite o id do veiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String type = controller.vehicleType(id);
        if(type == "1" || type == "2") {
            System.out.println("Digite o novo modelo do veiculo: ");
            String model = scanner.nextLine();
            System.out.println("Digite a nova autonomia máxima: ");
            String maximumAutonomyStr = scanner.nextLine();
            System.out.println("Digite a nova carga atual da bateria: ");
            String currentBatteryChargeStr = scanner.nextLine();
            System.out.println("Digite o novo consumo (kWh/Km): ");
            String consumptionKWhPerKmStr = scanner.nextLine();
            System.out.println("Digite o novo tempo de recarga (em minutos): ");
            String fullRechargeTimeStr = scanner.nextLine();
            if (type == "1") {
                System.out.println("Digite o novo tipo de conector: )");
                String connectorType = scanner.nextLine();
                System.out.println("Digite o novo tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
                String fastChargingStr = scanner.nextLine();
                controller.updateElectricVehicle(id, model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr, connectorType, fastChargingStr);
            }else if (type == "2") {
                System.out.println("Digite a nova capacidade do tanque de combustivel (em litros): ");
                String fuelTankCapacityStr = scanner.nextLine();
                System.out.println("Digite o novo consumo de combustivel (em Km/l) do motor a combustão: ");
                String fuelConsumptionStr = scanner.nextLine();
                System.out.println("Digite o novo tipo de combustivel do veiculo: ");
                String fuelType = scanner.nextLine();
                controller.updateHybridVehicle(id, model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr, fuelTankCapacityStr, fuelConsumptionStr, fuelType);
            }
        }else {
            view.displayError(type);
        }
    }
    @Override
    public void requestDataForDelete() {
        System.out.println("Digite o id do veiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.deleteVehicleById(id);
    }
}