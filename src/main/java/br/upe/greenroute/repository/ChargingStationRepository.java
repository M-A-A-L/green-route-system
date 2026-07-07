package br.upe.greenroute.repository;

import java.util.ArrayList;
import java.util.List;
import br.upe.greenroute.model.ChargingStationModel;

public class ChargingStationRepository {

    private final List<ChargingStationModel> stations;
    private int id;

    public ChargingStationRepository() {
        stations = new ArrayList<>();
        id = 1;
    }
    public void add(ChargingStationModel station) {
        station.setId(this.id);
        stations.add(station);
        this.id++;
    }
    public ChargingStationModel searchById(int id) {
        for (ChargingStationModel station : stations) {
            if (station.getId() == id) {
                return station;
            }
        }
        return null;
    }
    public boolean update(ChargingStationModel updateStation) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getId() == updateStation.getId()) {
                stations.set(i, updateStation);
                return true;
            }
        }
        return false;
    }
    public boolean deleteById(int id) {
        return stations.removeIf(station -> station.getId() == id);
    }
    public List<ChargingStationModel> searchByCityId(int cityId) {
        List<ChargingStationModel> stationsFound = new ArrayList<>();
        for (ChargingStationModel station : stations) {
            if (station.getCityId() == cityId) {
                stationsFound.add(station);
            }
        }
        return stationsFound;
    }
    public int countStationsByCityId(int cityId) {
        int count = 0;
        for (ChargingStationModel station : stations) {
            if (station.getCityId() == cityId) {
                count++;
            }
        }
        return count;
    }
    public boolean deleteStationsByCityId(int cityId) {
        return stations.removeIf(station -> station.getCityId() == cityId);
    }
    public List<ChargingStationModel> getStations() {
        return stations;
    }
    public boolean isEmpty() {
        return stations.isEmpty();
    }
}