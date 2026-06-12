package br.upe.greenroute;
import java.util.Scanner;
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
        CityView cityView = new CityView();
        CityController cityController = new CityController(cityRepository, cityView);
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleView vehicleView = new VehicleView();

        MainMenu mainMenu = new MainMenu(scanner, vehicleView, vehicleRepository, cityView, cityRepository, cityController);
        mainMenu.showMenu();
        scanner.close();
    }
}
