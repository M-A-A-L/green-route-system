package br.upe.greenroute.model;

public class ElectricVehicleModel extends VehicleModel {
    private String connectorType;
    private int fastCharging;

    public ElectricVehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, String connectorType, int fastCharging) {
        this(model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime, connectorType, fastCharging);
        this.id = id;

    }
    public ElectricVehicleModel (String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, String connectorType, int fastCharging) {
        super (model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        this.setConnectorType(connectorType);
        this.setFastCharging(fastCharging);
    }

    public String getConnectorType() {
        return connectorType;
    }
    public void setConnectorType(String connectorType) {
        if (connectorType == null || connectorType.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de conector não pode ser nulo ou vazio!");
        }        this.connectorType = connectorType;
    }
    public int getFastCharging() {
        return fastCharging;
    }
    public void setFastCharging(int fastCharging) {
        if (fastCharging <= 0) {
            throw new IllegalArgumentException("O tempo de carregamento rápido deve ser maior que zero!");
        }
        if (fastCharging >= getFullRechargeTime()) {
            throw new IllegalArgumentException("O tempo de carregamento rápido deve ser menor que o tempo de recarga total!");
        }
        this.fastCharging = fastCharging;
    }
}