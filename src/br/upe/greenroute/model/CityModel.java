package br.upe.greenroute.model;

public class CityModel {
    private int id;
    private String name;
    private String state;
    private double capitalDistance;

    public CityModel(int id, String name, String state, double capitalDistance) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.capitalDistance = capitalDistance;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;}

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public double getCapitalDistance() {return capitalDistance;}

    public void setCapitalDistance(double capitalDistance) {this.capitalDistance = capitalDistance;}
}