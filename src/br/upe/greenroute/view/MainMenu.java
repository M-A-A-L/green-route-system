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
package br.upe.greenroute.model;

public abstract class Veiculo {
    private int id;
    private String modelo;
    private double autonomiaMaxima;
    private double cargaBateriaAtual;
    private double consumoKwhPorKm;
    private int tempoRecargaCompleta;

    public Veiculo(int id, String modelo, double autonomiaMaxima,
            double cargaBateriaAtual, double consumoKwhPorKm,
            int tempoRecargaCompleta) {
        this.id = id;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.consumoKwhPorKm = consumoKwhPorKm;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public void setAutonomiaMaxima(double autonomiaMaxima) {
        this.autonomiaMaxima = autonomiaMaxima;
    }

    public double getCargaBateriaAtual() {
        return cargaBateriaAtual;
    }

    public void setCargaBateriaAtual(double cargaBateriaAtual) {
        this.cargaBateriaAtual = cargaBateriaAtual;
    }

    public double getConsumoKwhPorKm() {
        return consumoKwhPorKm;
    }

    public void setConsumoKwhPorKm(double consumoKwhPorKm) {
        this.consumoKwhPorKm = consumoKwhPorKm;
    }

    public int getTempoRecargaCompleta() {
        return tempoRecargaCompleta;
    }

    public void setTempoRecargaCompleta(int tempoRecargaCompleta) {
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public double calcularAutonomiaAtual() {
        return autonomiaMaxima * (cargaBateriaAtual / 100);
    }

    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Modelo: " + modelo);
        System.out.println("Autonomia Máxima: " + autonomiaMaxima + " km");
        System.out.println("Carga da Bateria: " + cargaBateriaAtual + "%");
        System.out.println("Consumo: " + consumoKwhPorKm + " kWh/km");
        System.out.println("Tempo de Recarga Completa: " + tempoRecargaCompleta + " min");
    }
}
