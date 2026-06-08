package br.upe.greenroute.model;

public abstract class VehicleModel {
    protected int id;
    protected String model;
    protected double maximumAutonomy;
    protected double currentBatteryCharge;
    protected double consumeKwhPerKm;
    protected int fullRechargeTime;

    public VehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumeKwhPerKm, int fullRechargeTime) {
        this.id = id;
        this.model = model;
        this.maximumAutonomy = maximumAutonomy;
        this.currentBatteryCharge = currentBatteryCharge;
        this.consumeKwhPerKm = consumeKwhPerKm;
        this.fullRechargeTime = fullRechargeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMaximumAutonomy() {
        return maximumAutonomy;
    }

    public void setMaximumAutonomy(double maximumAutonomy) {
        this.maximumAutonomy = maximumAutonomy;
    }

    public double getCurrentBatteryCharge() {
        return currentBatteryCharge;
    }

    public void setCurrentBatteryCharge(double currentBatteryCharge) {
        this.currentBatteryCharge = currentBatteryCharge;
    }

    public double getConsumeKwhPerKm() {
        return consumeKwhPerKm;
    }

    public void setConsumeKwhPerKm(double consumeKwhPerKm) {
        this.consumeKwhPerKm = consumeKwhPerKm;
    }

    public int getFullRechargeTime() {
        return fullRechargeTime;
    }

    public void setFullRechargeTime(int fullRechargeTime) {
        this.fullRechargeTime = fullRechargeTime;
    }

    public double calcularAutonomiaAtual() {
        return maximumAutonomy * (currentBatteryCharge / 100);
    }

    public void displayVehicleData() {
        System.out.println("ID: " + id);
        System.out.println("Modelo: " + model);
        System.out.println("Autonomia Máxima: " + maximumAutonomy + " km");
        System.out.println("Carga da Bateria: " + currentBatteryCharge + "%");
        System.out.println("Consumo: " + consumeKwhPerKm + " kWh/km");
        System.out.println("Tempo de Recarga Completa: " + fullRechargeTime + " min");
    }
}
