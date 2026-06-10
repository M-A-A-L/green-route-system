package br.upe.greenroute.view;
import java.util.Scanner;
public class ChargingStationView extends BaseView{
    private Scanner scanner;
    public ChargingStationView(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public void requestDataForCreate() {
        System.out.println("Digite o nome do eletroposto: ");
        String name = scanner.nextLine();
        System.out.println("Digite a localização (endereço/rodovia): ");
        String location = scanner.nextLine();
        System.out.println("Digite o ID da cidade: ");
        int cityId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite os tipos de conectores disponiveis: ");
        String availableConnectorsType = scanner.nextLine();
        System.out.println("Digite a potência do carregador (Kw): ");
        double chargingPowerKW = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite o preço cobrado por kWh: ");
        double pricePerKWh = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite a quantidade de vagas disponiveis: ");
        int availableVacancies = scanner.nextInt();
        scanner.nextLine();
    }
    @Override
    public void requestDataForRead() {

    }
    @Override
    public void requestDataForUpdate() {

    }
    @Override
    public void requestDataForDelete() {

    }
}
