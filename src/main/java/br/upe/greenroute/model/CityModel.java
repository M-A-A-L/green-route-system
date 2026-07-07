package br.upe.greenroute.model;

public class CityModel {
    private int id;
    private String name;
    private String state;
    private double capitalDistance;

    public CityModel(int id, String name, String state, double capitalDistance) {
        this(name, state, capitalDistance);
        this.id = id;
    }
    public CityModel(String name, String state, double capitalDistance) {
        this.setName(name);
        this.setState(state);
        this.setCapitalDistance(capitalDistance);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("O ID não pode ser negativo!");
        }
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio!");
        }
        this.name = name;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("O estado não pode ser nulo ou vazio!");
        }
        this.state = state;
    }
    public double getCapitalDistance() {
        return capitalDistance;
    }
    public void setCapitalDistance(double capitalDistance) {
        if (capitalDistance < 0) {
            throw new IllegalArgumentException("A distância até a capital não pode ser negativa!");
        }        this.capitalDistance = capitalDistance;
    }
}