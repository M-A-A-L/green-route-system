package br.upe.greenroute.controller;

import br.upe.greenroute.model.CityModel;
import br.upe.greenroute.model.VehicleModel;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.CityMenu;
import br.upe.greenroute.view.TripView;

public class TripController {
    private final TripView view;
    private final VehicleRepository vehicleRepository;
    private final CityRepository cityRepository;
    private final ChargingStationRepository chargingStationRepository;
    public TripController (TripView tripView, VehicleRepository vehicleRepository, CityRepository cityRepository, ChargingStationRepository chargingStationRepository) {
        this.view = tripView;
        this.chargingStationRepository = chargingStationRepository;
        this.cityRepository = cityRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public void TripSimulation (int vehicleId, int cityId) {
        VehicleModel vehicle = vehicleRepository.searchById(vehicleId);
        CityModel destination = cityRepository.searchById(cityId);
        if (vehicle == null ||  destination == null) {
            view.displayError("Veiculo ou cidade não encontrado no sistema!");
            return;
        }
        double currentAutonnomy = vehicle.calculateCurrentAutonomy();
        double destinyDistance = destination.getCapitalDistance();
        if (currentAutonnomy >= destinyDistance) {
            view.displayMessage("A autonomia atual do carro é suficiente para concluir a viagem!");
        }else {
            view.displayMessage("A autonomia atual do carro é insuficiente para concluir a viagem!");
        }
    }
}
