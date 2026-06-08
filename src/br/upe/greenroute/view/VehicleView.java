package br.upe.greenroute.view;
import java.util.Scanner;
public class VehicleView extends BaseView{
    private Scanner scanner;
    public VehicleView(Scanner scanner) {
        this.scanner = scanner;
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
            displayError("Deve digitar 1 para eletricos ou 2 para hibridos");
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
    public void requestDataForCreateElectricVehicle(String model, double autonomy, double baterryCharge, double consumption, int rechargeTime) {
        System.out.println("Digite o tipo de conector: )");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
        int fastCharging = scanner.nextInt();
        //controller.cadastrarEletrico
    }
    public void requestDataForCreateHybridVehicle(String model, double autonomy, double baterryCharge, double consumption, int rechargeTime) {
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
