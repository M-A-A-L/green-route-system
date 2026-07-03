package br.upe.greenroute.repository;

import br.upe.greenroute.model.CityModel;
import java.util.List;
import java.util.ArrayList;

public class CityRepository {
    private final List<CityModel> cities;
    private int id;

    public CityRepository() {
        cities = new ArrayList<>();
        id = 1;
    }

    public void add(CityModel city) {
        city.setId(this.id);
        cities.add(city);
        this.id++;
    }

    public CityModel searchById(int id) {
        for (CityModel city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }
    public List<CityModel> searchByState(String state) {
        List<CityModel> citiesByState = new ArrayList<>();
        for (CityModel city : cities) {
            if (city.getState().equalsIgnoreCase(state)) {
                citiesByState.add(city);
            }
        }
        return citiesByState;
    }
    public boolean update(CityModel updateCity) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getId()==updateCity.getId()) {
                cities.set(i, updateCity);
                return true;
            }
        }
        return false;
    }
    public boolean deleteById(int id) {
        return cities.removeIf(city -> city.getId() == id);
    }
    public List<CityModel> getCities() {
        return cities;
    }
    public boolean isEmpty() {
        return cities.isEmpty();
    }
}