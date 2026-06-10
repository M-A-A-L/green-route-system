package br.upe.greenroute.repository;

import br.upe.greenroute.model.CityModel;

public class CityRepository {
    private CityModel[] cities;
    private int count;

    public CityRepository() {
        cities = new CityModel[5];
        this.count = 0;
    }

    private void expandArray() {
        CityModel[] newArray = new CityModel[cities.length * 2];
        for (int i = 0; i < cities.length; i++) {
            newArray[i] = cities[i];
        }
        cities = newArray;
    }

    public void add(CityModel city) {
        if (this.count == cities.length) {
            expandArray();
        }
        cities[this.count++] = city;
    }

    public CityModel searchByID(int id) {
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId() == id) {
                return cities[i];
            }
        }
        return null;
    }

    public CityModel[] searchByState(String state) {
        CityModel[] citiesByState = new CityModel[this.count];
        int count = 0;
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getState().equalsIgnoreCase(state)) {
                citiesByState[count++] = cities[i];
            }
        }
        CityModel[] finalCitiesByState = new CityModel[count];
        for (int i = 0; i < count; i++) {
            finalCitiesByState[i] = citiesByState[i];
        }
        return finalCitiesByState;
    }
    public boolean update(CityModel updateCity) {
        boolean foundTheCity = false;
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId()==updateCity.getId()) {
                cities[i] = updateCity;
                foundTheCity=true;
            }
        }
        return foundTheCity;
    }
    public boolean deleteCityById(int id) {
        boolean foundTheId = false;
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId() == id) {
                for (int j = i; j < this.count - 1; j++) {
                    cities[j] = cities[j + 1];
                }
                cities[this.count - 1] = null;
                this.count--;
                foundTheId = true;
            }
        }
        return foundTheId;
    }
}