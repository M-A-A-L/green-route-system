package br.upe.greenroute.view;
import java.util.Scanner;
import br.upe.greenroute.model.ElectricVehicleModel;
import br.upe.greenroute.model.HybridVehicleModel;
import br.upe.greenroute.model.VehicleModel;

public class VehicleView extends BaseView{
    private Scanner scanner;

    public VehicleView(Scanner scanner) {
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
}
