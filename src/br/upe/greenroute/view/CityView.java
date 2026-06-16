package br.upe.greenroute.view;

import br.upe.greenroute.model.CityModel;

import java.util.Scanner;

public class CityView extends BaseView{
    private final Scanner scanner;
    public CityView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }

    public void displayCity (CityModel city) {
        System.out.println("=== Dados Da Cidade ===");
        System.out.println("ID:   "+ city.getId());
        System.out.println("Nome: "+city.getName());
        System.out.println("Estado (UF): "+city.getState());
        System.out.println("Distância da capital: "+city.getCapitalDistance());
    }
    public String[] requestDataForCreate() {
        System.out.println("Digite o nome da cidade: ");
        String name = scanner.nextLine();
        System.out.println("Digite o estado (UF): ");
        String state = scanner.nextLine();
        System.out.println("Digite a distância desta cidade para a capital do estado: ");
        String capitalDistanceStr = scanner.nextLine();
        return new String[] {name,state,capitalDistanceStr};
    }
    public String[] requestDataForUpdate() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o novo nome da cidade (Enter para manter): ");
        String name = scanner.nextLine();
        System.out.println("Digite o estado (UF) (Enter para manter): ");
        String state = scanner.nextLine();
        System.out.println("Digite a distância desta cidade para a capital do estado (Enter para manter): ");
        String capitalDistanceStr = scanner.nextLine();
        return new String[] {name, state, capitalDistanceStr};
    }
    public int requestId() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}

