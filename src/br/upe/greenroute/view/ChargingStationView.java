package br.upe.greenroute.view;
import br.upe.greenroute.model.ChargingStationModel;

import java.util.Scanner;
public class ChargingStationView extends BaseView{
    public void displayChargingStation (ChargingStationModel chargingStation) {
        String conectores = String.join(" | ",chargingStation.getAvailableConnectorsType());
        System.out.println("=== Dados do eletorposto ===");
        System.out.println("ID: "+chargingStation.getId());
        System.out.println("Nome: "+chargingStation.getName());
        System.out.println("Localização: "+chargingStation.getLocation());
        System.out.println("Conectores disponiveis: "+conectores);
        System.out.println("Potencia máxima do carregador: "+chargingStation.getChargingPowerKW());
        System.out.println("Preço por kWh: "+chargingStation.getPricePerKWh());
        System.out.println("Vagas disponiveis: "+chargingStation.getAvailableVacancies());
    }

}
