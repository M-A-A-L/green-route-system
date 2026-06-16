package br.upe.greenroute;
import java.util.Scanner;

import br.upe.greenroute.controller.ChargingStationController;
import br.upe.greenroute.controller.VehicleController;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.view.ChargingStationView;
import br.upe.greenroute.view.CityView;
import br.upe.greenroute.controller.CityController;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.MainMenu;
import br.upe.greenroute.view.VehicleView;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityRepository cityRepository = new CityRepository();
        CityView cityView = new CityView(scanner);
        CityController cityController = new CityController(cityRepository, cityView);
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleView vehicleView = new VehicleView(scanner);
        VehicleController vehicleController = new VehicleController(vehicleRepository, vehicleView);
        ChargingStationView chargingStationView = new ChargingStationView(scanner);
        ChargingStationRepository chargingStationRepository = new ChargingStationRepository();
        ChargingStationController chargingStationController = new ChargingStationController(chargingStationRepository, cityRepository, chargingStationView);

        MainMenu mainMenu = new MainMenu(scanner, vehicleView, vehicleRepository, vehicleController, cityView, cityRepository, cityController, chargingStationView, chargingStationRepository, chargingStationController);
        mainMenu.showMenu();
        scanner.close();
    }
}
