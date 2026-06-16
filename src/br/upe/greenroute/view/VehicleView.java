package br.upe.greenroute.view;
import java.util.Scanner;
import br.upe.greenroute.model.ElectricVehicleModel;
import br.upe.greenroute.model.HybridVehicleModel;
import br.upe.greenroute.model.VehicleModel;

public class VehicleView extends BaseView{
    private final Scanner scanner;

    public VehicleView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }

    public void displayVehicleData(VehicleModel vehicle) {
        System.out.println("=== Dados do Veiculo ===");
        System.out.println("ID: " + vehicle.getId());
        System.out.println("Modelo: " + vehicle.getModel());
        System.out.println("Autonomia Máxima: " + vehicle.getMaximumAutonomy() + " km");
        System.out.println("Carga da Bateria: " + vehicle.getCurrentBatteryCharge() + "%");
        System.out.println("Consumo: " + vehicle.getConsumeKwhPerKm() + " kWh/km");
        System.out.println("Tempo de Recarga Completa: " + vehicle.getFullRechargeTime() + " min");
    }

    public void displayVehicleData(ElectricVehicleModel electricVehicle) {
        displayVehicleData((VehicleModel) electricVehicle);
        System.out.println("Tipo de Conector: " + electricVehicle.getConnectorType());
        System.out.println("Tempo de Recarga Rápida: " + electricVehicle.getFastCharging() + " min");
    }
    public void displayVehicleData(HybridVehicleModel hybridVehicle) {
        displayVehicleData((VehicleModel) hybridVehicle);
        System.out.println("Capacidade do tanque de combustivel: "+ hybridVehicle.getFuelTankCapacity() +" l");
        System.out.println("Consumo do combustivel: "+ hybridVehicle.getFuelConsumption() +" Km/l");
        System.out.println("tipo de combustivel: "+ hybridVehicle.getFuelType());
    }
    public int requestVehicleType() {
        System.out.println("Digite o tipo de veiculo:");
        System.out.println("1-Eletrico");
        System.out.println("2-Hibrido");
        int type = scanner.nextInt();
        scanner.nextLine();
        if (type == 1 || type == 2) {
            return type;
        }else {
            displayError("Deve digitar 1 para eletricos ou 2 para hibridos\n");
            return requestVehicleType();
        }
    }
    public String[] requestDataForCreate() {
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
        return new String[] {model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr};
    }

    public String[] requestDataForCreateElectricVehicle() {
        System.out.println("Digite o tipo de conector: )");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o tempo de recarga rápida (em minutos) em carregadores de alta potencia: ");
        String fastChargingStr = scanner.nextLine();
        return new String[] {connectorType, fastChargingStr};
    }
    public String[] requestDataForCreateHybridVehicle() {
        System.out.println("Digite a capacidade do tanque de combustivel (em litros): ");
        String fuelTankCapacityStr = scanner.nextLine();
        System.out.println("Digite o consumo de combustivel (em Km/l) do motor a combustão: ");
        String fuelConsumptionStr = scanner.nextLine();
        System.out.println("Digite o tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        return new String[] {fuelTankCapacityStr, fuelConsumptionStr, fuelType};
    }
    public String[] requestDataForUpdate() {
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
        return new String[] {model, maximumAutonomyStr, currentBatteryChargeStr,consumptionKWhPerKmStr, fullRechargeTimeStr};
    }
    public String[] requestDataForUpdateElectricVehicle() {
        System.out.println("Digite o novo tipo de conector: ");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o novo tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
        String fastChargingStr = scanner.nextLine();
        return new String[]{connectorType, fastChargingStr};
    }
   public String[] requestDataForUpdateHybridVehicle() {
        System.out.println("Digite a nova capacidade do tanque de combustivel (em litros): ");
        String fuelTankCapacityStr = scanner.nextLine();
        System.out.println("Digite o novo consumo de combustivel (em Km/l) do motor a combustão: ");
        String fuelConsumptionStr = scanner.nextLine();
        System.out.println("Digite o novo tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        return new String[] {fuelTankCapacityStr, fuelConsumptionStr, fuelType};
    }
    public int requestId() {
        System.out.println("Digite o id do veiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}
