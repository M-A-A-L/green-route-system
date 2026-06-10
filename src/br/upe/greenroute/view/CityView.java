package br.upe.greenroute.view;
import java.util.Scanner;
public class CityView extends BaseView{
    private Scanner scanner;
    public CityView(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public void requestDataForCreate() {
        System.out.println("Digite o nome da cidade: ");
        String name = scanner.nextLine();
        System.out.println("Digite o estado (UF): ");
        String state = scanner.nextLine();
        System.out.println("Digite a distância desta cidade para a capital do estado: ");
        double capitalDistance = scanner.nextDouble();
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
