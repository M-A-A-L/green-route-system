package br.upe.greenroute.view;
import br.upe.greenroute.controller.CityController;

import java.util.Scanner;
public class CityMenu extends BaseMenu {
    private final Scanner scanner;
    private final CityView cityView;
    private final CityController cityController;

    public CityMenu(Scanner scanner,CityView cityView, CityController cityController) {
        this.scanner = scanner;
        this.cityView = cityView;
        this.cityController = cityController;
    }
    @Override
    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("=== Menu Cidades ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar cidade");
            System.out.println("2. Atualizar cidade");
            System.out.println("3. Buscar cidade");
            System.out.println("4. Remover cidade");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> requestDataForCreate();
                case 2 -> requestDataForUpdate();
                case 3 -> requestDataForRead();
                case 4 -> requestDataForDelete();
                case 5 -> cityController.listCities();
                case 0 -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> cityView.displayError("Digite uma opção válida!");
            }
        }
    }
    @Override
    public void requestDataForCreate() {
        scanner.nextLine();
        System.out.println("Digite o nome da cidade: ");
        String name = scanner.nextLine();
        System.out.println("Digite o estado (UF): ");
        String state = scanner.nextLine();
        System.out.println("Digite a distância desta cidade para a capital do estado: ");
        String capitalDistanceStr = scanner.nextLine();
        cityController.addCity(name, state, capitalDistanceStr);
    }
    @Override
    public void requestDataForRead() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cityController.searchCityById(id);
    }
    @Override
    public void requestDataForUpdate() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o novo nome da cidade (Enter para manter): ");
        String name = scanner.nextLine();
        System.out.println("Digite o estado (UF) (Enter para manter): ");
        String state = scanner.nextLine();
        System.out.println("Digite a distância desta cidade para a capital do estado (Enter para manter): ");
        String capitalDistanceStr = scanner.nextLine();
        cityController.updateCity(id, name, state, capitalDistanceStr);
    }
    @Override
    public void requestDataForDelete() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cityController.deleteCityById(id);
    }
}