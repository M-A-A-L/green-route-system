package br.upe.greenroute.view;
import java.util.Scanner;
public class MainMenu {
    private Scanner scanner;
    private VehicleMenu vehicleMenu;
    private ChargingStationMenu chargingStationMenu;
    private CityMenu cityMenu;
    public MainMenu() {
        this.scanner = new Scanner(System.in);
        vehicleMenu = new VehicleMenu(scanner);
        chargingStationMenu = new ChargingStationMenu(scanner);
        cityMenu = new CityMenu(scanner);
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
              SISTEMA DE LOGÍSTICA INTELIGENTE\n""");
            System.out.println(" ");
           System.out.println("Escolha uma opção: ");
           System.out.println("1. Gerenciar veiculos");
           System.out.println("2. Gerenciar eletropostos");
           System.out.println("3. Gerenciar cidades");
           System.out.println("0. Sair");
           opcao = scanner.nextInt();
           if (opcao == 1){
               vehicleMenu.showMenu();
           }else if (opcao == 2) {
               chargingStationMenu.showMenu();
           }else if (opcao == 3) {
               cityMenu.showMenu();
           }else if (opcao == 0) {
               System.out.println("Encerrando o programa . . .");
               executando = false;
               scanner.close();
           }else {
               System.out.println("Digite uma opção válida!");
           }
        }
    }
}