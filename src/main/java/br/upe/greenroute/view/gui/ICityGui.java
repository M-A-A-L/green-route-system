package br.upe.greenroute.view.gui;

public interface ICityGui {
    String[] getCityInputs();
    void clearTextFields();
    void setCityData(String name, String state, String distance);
    boolean isConfirmed();
}