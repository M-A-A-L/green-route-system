package br.upe.greenroute.view;

import br.upe.greenroute.controller.ChargingStationController;
import br.upe.greenroute.controller.CityController;
import br.upe.greenroute.controller.VehicleController;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.gui.MainView;
import iaService.AIPlannerService;
import iaService.ConexaoGemini;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AIPlannerService aiService = new ConexaoGemini();
            CityRepository cityRepository = new CityRepository();
            ChargingStationRepository stationRepository = new ChargingStationRepository();
            VehicleRepository vehicleRepository = new VehicleRepository();
            MainView telaPrincipal = new MainView();
            ViewManager viewManager = new ViewManager(telaPrincipal);
            CityController cityController = new CityController(cityRepository, viewManager, stationRepository, aiService);
            ChargingStationController stationController = new ChargingStationController(stationRepository, cityRepository, viewManager, aiService);
            VehicleController vehicleController = new VehicleController(vehicleRepository, viewManager, aiService);
            telaPrincipal.getBtnAddCity().addActionListener(e -> {
                cityController.addCity(null);
            });
            telaPrincipal.getBtnUpdtCity().addActionListener(e -> {
                cityController.updateCity();
            });
            telaPrincipal.getBtnDelCity().addActionListener(e -> {
                cityController.deleteCityById();
            });
            telaPrincipal.getBtnCityFilter().addActionListener(e -> {
                cityController.filterCities();
            });
            telaPrincipal.getCbSearchCity().addActionListener(e -> {
                String selected = (String) telaPrincipal.getCbSearchCity().getSelectedItem();
                if ("Todos".equals(selected)) cityController.filterCities();
            });
            telaPrincipal.getBtnAddStation().addActionListener(e -> {
                stationController.addChargingStation(null);
            });
            telaPrincipal.getBtnUpdStation().addActionListener(e -> {
                stationController.updateChargingStation();
            });
            telaPrincipal.getBtnDelStation().addActionListener(e -> {
                stationController.deleteChargingStationById();
            });
            telaPrincipal.getBtnStationFilter().addActionListener(e -> {
                stationController.filterChargingStations();
            });
            telaPrincipal.getCbSearchStation().addActionListener(e -> {
                String selected = (String) telaPrincipal.getCbSearchStation().getSelectedItem();
                if ("Todos".equals(selected)) stationController.filterChargingStations();
            });
            telaPrincipal.getBtnAddVehicle().addActionListener(e -> {
                vehicleController.addVehicle(null);
            });
            telaPrincipal.getBtnUpdVehicle().addActionListener(e -> {
                vehicleController.updateVehicle();
            });
            telaPrincipal.getBtnDelVehicle().addActionListener(e -> {
                vehicleController.deleteVehicleById();
            });
            telaPrincipal.getBtnVehicleFilter().addActionListener(e -> {
                vehicleController.filterVehicles();
            });
            telaPrincipal.getCbSearchVehicle().addActionListener(e -> {
                String selected = (String) telaPrincipal.getCbSearchVehicle().getSelectedItem();
                if ("Todos".equals(selected)) vehicleController.filterVehicles();
            });
            telaPrincipal.getBtnFastAddCity().addActionListener(e -> {
                cityController.fastAddCity();
            });
            telaPrincipal.getBtnFastAddStation().addActionListener(e -> {
                stationController.fastAddStation();
            });
            telaPrincipal.getBtnFastAddVehicle().addActionListener(e -> {
                vehicleController.fastAddVehicle();
            });
            telaPrincipal.setVisible(true);
        });
    }
}