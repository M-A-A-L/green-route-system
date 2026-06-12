package br.upe.greenroute.view;
import java.util.Scanner;
public class VehicleMenu extends BaseMenu{
    private final Scanner scanner;
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
                requestDataForCreate();
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

    private int requestVehicleType() {
        System.out.println("Digite o tipo de veiculo:");
        System.out.println("1-Eletrico");
        System.out.println("2-Hibrido");
        int type = scanner.nextInt();
        scanner.nextLine();
        if (type == 1 || type == 2) {
            return type;
        }else {
            view.displayError("Deve digitar 1 para eletricos ou 2 para hibridos");
            return requestVehicleType();
        }
    }
    @Override
    public void requestDataForCreate() {
        int vehicleType= requestVehicleType();
        System.out.println("Digite o modelo do veiculo: ");
        String vehicleModel = scanner.nextLine();
        System.out.println("Digite a autonomia máxima do veiculo: ");
        double maximumAutonomy = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o carga atual da bateria do veiculo: ");
        double currentBatteryCharge = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o consumo (kWh/Km) do veiculo: ");
        double consumptionKWhPerKm = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o tempo de recarga (em minutos) do veiculo: ");
        int fullRechargeTime = scanner.nextInt();
        scanner.nextLine();
        if (vehicleType == 1) {
            requestDataForCreateElectricVehicle(vehicleModel, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        }else if (vehicleType == 2) {
            requestDataForCreateHybridVehicle(vehicleModel, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        }
    }
    private void requestDataForCreateElectricVehicle(String model, double autonomy, double baterryCharge, double consumption, int rechargeTime) {
        System.out.println("Digite o tipo de conector: )");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
        int fastCharging = scanner.nextInt();
        //controller.cadastrarEletrico
    }
    private void requestDataForCreateHybridVehicle(String model, double autonomy, double baterryCharge, double consumption, int rechargeTime) {
        System.out.println("Digite a capacidade do tanque de combustivel (em litros): ");
        double fuelTankCapacity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite a consumo de combustivel (em Km/l) do motor a combustão: ");
        double fuelConsumption = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        //controller.cadastrarHibrido
    }
    @Override
    public void requestDataForRead() {
    }
    @Override
    public void requestDataForUpdate() {
    }
    @Override
    public void requestDataForDelete() {
    }
}