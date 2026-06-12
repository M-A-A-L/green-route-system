package br.upe.greenroute.view;
import java.util.Scanner;
public class ChargingStationMenu extends BaseMenu{
    private Scanner scanner;
    public ChargingStationMenu(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public void showMenu() {
        int opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("=== Menu Eletropostos ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar eletroposto");
            System.out.println("2. Atualizar eletroposto");
            System.out.println("3. Buscar eletroposto");
            System.out.println("4. Remover eletroposto");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextInt();
            if (opcao == 1) {
                //cadastrar eletroposto
            } else if (opcao == 2) {
                //atualizar eletroposto
            } else if (opcao == 3) {
                //buscar eletroposto
            } else if (opcao == 4) {
                //remover eletroposto
            } else if (opcao == 0) {
                System.out.println("Voltando . . .");
                executando = false;
            } else {
                System.out.println("Digite uma opção válida!");
            }
        }
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