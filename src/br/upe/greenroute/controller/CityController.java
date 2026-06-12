package br.upe.greenroute.controller;

import br.upe.greenroute.model.CityModel;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.view.CityView;

public class CityController extends BaseController{
    private final CityRepository repository;
    private final CityView view;
    public CityController (CityRepository repository, CityView view) {
        this.repository = repository;
        this.view = view;
    }
    public void addCity (String name,String state, String capitalDistanceStr) {
        if ((name == null || name.isBlank()) || (state == null || state.isBlank()) || (capitalDistanceStr == null || capitalDistanceStr.isBlank())) {
            view.displayError("Nenhum dado pode estar vazio!");
            return;
        }
        if (!validDouble(capitalDistanceStr)) {
            view.displayError("A distância deve conter apenas números e pontos");
            return;
        }
        double capitalDistance = Double.parseDouble(capitalDistanceStr);
        if (capitalDistance<0) {
            view.displayError("A distância da cidade para a capital não pode ser negativa!");
            return;
        }
        CityModel city = new CityModel(name, state, capitalDistance);
        repository.add(city);
        view.displayMessage("Cidade cadastrada!");
        view.displayCity(city);
    }
    public void searchCityById (int id) {
        CityModel cityFound = repository.searchById(id);
        if ( cityFound != null) {
            view.displayMessage("Cidade encontrada!");
            view.displayCity(cityFound);
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
    }
    public void updateCity(int id, String name,String state, String capitalDistanceStr) {
        CityModel cityFound = repository.searchById(id);
        if (cityFound != null) {
            if (name != null  && !name.isBlank()) {
                cityFound.setName(name);
            }
            if (state != null && !state.isBlank()) {
                cityFound.setState(state);
            }
            if (capitalDistanceStr != null && !capitalDistanceStr.isBlank()) {
                if (validDouble(capitalDistanceStr)) {
                    double capitalDistance = Double.parseDouble(capitalDistanceStr);
                    if(capitalDistance >= 0) {
                        cityFound.setCapitalDistance(capitalDistance);
                    }else {
                        view.displayError("A distância da capital deve ser maior que zero!");
                    }
                }else {
                    view.displayError("O formato da nova distância é invalido!");
                }
            }
            boolean result = repository.update(cityFound);
            if (result) {
                view.displayMessage("Cidade atualizada com sucesso!");
                view.displayCity(cityFound);
            }else {
                view.displayError("Cidade não pode ser atualizada!");
            }
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
    }
    public void deleteCityById(int id) {
        boolean result = repository.deleteCityById(id);
        if (result) {
            view.displayMessage("Cidade deletada com sucesso!");
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
    }
    public void listCities() {
        CityModel[] cities = repository.getCities();
        if (cities == null || cities.length == 0) {
            view.displayError("Nenhuma cidade cadastrada no sistema");
        } else {
            for (CityModel city : cities) {
                if (city != null) {
                    view.displayCity(city);
                }
            }
        }
    }
}