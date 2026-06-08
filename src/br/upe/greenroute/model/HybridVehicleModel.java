package br.upe.greenroute.model;

public class HybridVehicleModel extends VehicleModel {
    private double fuelTankCapacity;
    private double fuelConsumption;
    private String fuelType;

    public HybridVehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, double fuelTankCapacity, double fuelConsumption, String fuelType) {
        super(id, model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
    }

    public double getFuelTankCapacity() { return fuelTankCapacity; }

    public void setFuelTankCapacity(double fuelTankCapacity) { this.fuelTankCapacity = fuelTankCapacity; }

    public double getFuelConsumption() { return fuelConsumption; }

    public void setFuelConsumption(double fuelConsumption) { this.fuelConsumption = fuelConsumption; }

    public String getFuelType() { return fuelType; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    @Override
    public void displayVehicleData() {
        super.displayVehicleData();
        System.out.println("Capacidade do tanque de combustivel: "+ fuelTankCapacity +" l");
        System.out.println("Consumo do combustivel: "+ fuelConsumption +" Km/l");
        System.out.println("tipo de combustivel: "+ fuelType);
    }
}
