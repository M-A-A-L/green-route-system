package br.upe.greenroute.model;

import java.util.prefs.PreferenceChangeEvent;

public class ElectricVehicleModel extends VehicleModel {
    private String connectorType;
    private int fastCharging;

    public ElectricVehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, String connectorType, int fastCharging) {
        super(id, model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        this.connectorType = connectorType;
        this.fastCharging = fastCharging;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public int getFastCharging() {
        return fastCharging;
    }

    public void setFastCharging(int fastCharging) {
        this.fastCharging = fastCharging;
    }

    @Override
    public void displayVehicleData() {
        super.displayVehicleData();
        System.out.println("Tipo de Conector: " + connectorType);
        System.out.println("Tempo de Recarga Rápida: " + fastCharging + " min");
    }
}
