package br.upe.greenroute.view;

import br.upe.greenroute.model.CityModel;

public class CityView extends BaseView{
    public void displayCity (CityModel city) {
        System.out.println("=== Dados Da Cidade ===");
        System.out.println("ID:   "+ city.getId());
        System.out.println("Nome: "+city.getName());
        System.out.println("Estado (UF): "+city.getState());
        System.out.println("Distância da capital: "+city.getCapitalDistance());
    }
}

