package br.upe.greenroute.model;

public class ChargingStationModel {
    private int id;
    private String name;
    private String location;
    private int cityId;
    private String availableConnectorsType;
    private double chargingPowerKWh;
    private double pricePerKWh;
    private int availableVacancies;

    public ChargingStationModel(int id, String name, String location, int cityId, String availableConnectorsType, double chargingPowerKWh, double pricePerKWh, int availableVacancies) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cityId = cityId;
        this.availableConnectorsType = availableConnectorsType;
        this.chargingPowerKWh = chargingPowerKWh;
        this.pricePerKWh = pricePerKWh;
        this.availableVacancies = availableVacancies;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public int getCityId() {return cityId;}

    public void setCityId(int cityId) {this.cityId = cityId;}

    public String getAvailableConnectorsType() {return availableConnectorsType;}

    public void setAvailableConnectorsType(String availableConnectorsType) {this.availableConnectorsType = availableConnectorsType;}

    public double getChargingPowerKWh() {return chargingPowerKWh;}

    public void setChargingPowerKWh(double chargingPowerKWh) {this.chargingPowerKWh = chargingPowerKWh;}

    public double getPricePerKWh() {return pricePerKWh;}

    public void setPricePerKWh(double pricePerKWh) {this.pricePerKWh = pricePerKWh;}

    public int getAvailableVacancies() {return availableVacancies;}

    public void setAvailableVacancies(int availableVacancies) {this.availableVacancies = availableVacancies;}
}
