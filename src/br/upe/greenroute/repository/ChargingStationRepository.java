package br.upe.greenroute.repository;

import br.upe.greenroute.model.ChargingStationModel;

public class ChargingStationRepository {

    private ChargingStationModel[] stations;
    private int count;
    private int id;

    public ChargingStationRepository() {
        stations = new ChargingStationModel[10];
        count = 0;
        id = 1;
    }
    private void expandArray() {
        ChargingStationModel[] newArray = new ChargingStationModel[stations.length * 2];
        for (int i = 0; i < stations.length; i++) {
            newArray[i] = stations[i];
        }
        stations = newArray;
    }
    public void add(ChargingStationModel station) {
        if (this.count == stations.length) {
            expandArray();
        }
        station.setId(this.id);
        stations[this.count++] = station;
        this.id++;
    }
    public ChargingStationModel searchByID(int id) {
        for (int i = 0; i < this.count; i++) {
            if (stations[i].getId() == id) {
                return stations[i];
            }
        }
        return null;
    }
    public boolean update(ChargingStationModel updateStation) {
        for (int i = 0; i < this.count; i++) {
            if (stations[i].getId() == updateStation.getId()) {
                stations[i] = updateStation;
                return true;
            }
        }
        return false;
    }
    public boolean deleteStationById(int id) {
        for (int i = 0; i < this.count; i++) {
            if (stations[i].getId() == id) {
                for (int j = i; j < count - 1; j++) {
                    stations[j] = stations[j + 1];
                }
                stations[this.count - 1] = null;
                this.count--;
                return true;
            }
        }
        return false;
    }
    public ChargingStationModel[] getStations() {
        return stations;
    }
    public int getCount() {
        return count;
    }
}