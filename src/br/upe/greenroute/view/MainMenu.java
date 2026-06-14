package br.upe.greenroute.view;

import br.upe.greenroute.controller.CityController;
import br.upe.greenroute.controller.VehicleController;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final VehicleController vehicleController;
    private final VehicleView vehicleView;
    private final VehicleMenu vehicleMenu;
    private final VehicleRepository vehicleRepository;
    private final CityController cityController;
    private final CityView cityView;
    private final CityMenu cityMenu;
    private final CityRepository cityRepository;
    private final ChargingStationMenu chargingStationMenu;

    public MainMenu(Scanner scanner, VehicleView vehicleView, VehicleRepository vehicleRepository, VehicleController vehicleController,
                    CityView cityView, CityRepository cityRepository, CityController cityController
                    ) {
        this.scanner = scanner;
        this.vehicleView = vehicleView;
        this.vehicleController = vehicleController;
        this.vehicleRepository = vehicleRepository;
        this.vehicleMenu = new VehicleMenu(scanner, vehicleView, vehicleController);
        this.cityView = cityView;
        this.cityController = cityController;
        this.cityRepository = cityRepository;
        this.cityMenu = new CityMenu(scanner,cityView, cityController);
        this.chargingStationMenu = new ChargingStationMenu(scanner);
    }

    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.print("""
              \n
                 ________    ⠀⠀⠀ ⣀⣤⣶⠖⠛⠉⠉⠉⠉⣿⠉⠉⠙⠒⠦⣄⠀⠀⠀⠀⠀\s
                 |[____]|⠀⠀  ⠀⢲⣶⣿⣿⣿⣷⡶⠦⣦⣤⣤⣤⣽⣧⣤⣤⣤⣴⣿⣿⣶⣤⣤⣤⣀⣀⣀
                 |⣿    +|>---⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣉⣂
                 |⣿_____|    ⢿⣿⣿⣿⣿⠏⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠈⣿⣿⣿⣿
                 [⣿_____]    ⠀⠀ ⠈⠙⢧⣄⣠⠞⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠻⠦⣤⠴⠋⠉⠀⠀
              ================================================
              ____ ____ ____ ____ _  _ ____ ____ _  _ ___ ____\s
              | __ |__/ |___ |___ |\\ | |__/ |  | |  |  |  |___\s
              |__] |  \\ |___ |___ | \\| |  \\ |__| |__|  |  |___
              ================================================
              SISTEMA DE LOGÍSTICA INTELIGENTE""");
            System.out.println(" ");
           System.out.println("Escolha uma opção: ");
           System.out.println("1. Gerenciar veiculos");
           System.out.println("2. Gerenciar eletropostos");
           System.out.println("3. Gerenciar cidades");
           System.out.println("0. Sair");
           opcao = scanner.nextInt();
           scanner.nextLine();
           if (opcao == 1){
               vehicleMenu.showMenu();
           }else if (opcao == 2) {
               //chargingStationMenu.showMenu();
           }else if (opcao == 3) {
               cityMenu.showMenu();
           }else if (opcao == 0) {
               System.out.println("Encerrando o programa . . .");
               executando = false;
           }else {
               System.out.println("Digite uma opção válida!");
           }
        }
    }
}