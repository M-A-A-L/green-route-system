package br.upe.greenroute.controller;

import br.upe.greenroute.model.CityModel;
import br.upe.greenroute.model.ElectricVehicleModel;
import br.upe.greenroute.model.HybridVehicleModel;
import br.upe.greenroute.model.VehicleModel;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.VehicleView;

public class VehicleController extends BaseController{
    private final VehicleRepository repository;
    private final VehicleView view;
    String error;

    public VehicleController(VehicleRepository repository, VehicleView view) {
        this.repository = repository;
        this.view = view;
    }

    private void addVehicle (String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        if (error == null) {
            error = isAnyBlank(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        }
        if (error == null) {
            error = isDouble(maximumAutonomyStr,"A autonomia máxima deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(currentBatteryChargeStr, "A carga atual da bateria deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(consumeKwhPerKmStr, "O consumo de energia deve ser um valor valido!");
        }
        if (error == null) {
            error = isInt(fullRechargeTimeStr, "O tempo de recarga completa deve ser um valor valido!");
        }
    }
    public void addElectricVehicle(String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr, String connectorType, String fastChargingStr) {
        addVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        this.error = null;
        if (error == null) {
            error = isAnyBlank(connectorType, fastChargingStr);
            }
        if (error == null) {
            error = isInt(fastChargingStr, "O tempo de recarga rápida deve se um valor inteiro!");
            }
        if (error != null) {
            view.displayError(error);
            return;
        }
        double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
        double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
        double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
        int fullRechargeTime = Integer.parseInt(fullRechargeTimeStr);
        int fastCharging = Integer.parseInt(fastChargingStr);

        if (maximumAutonomy <= 0) {
            error = ("A autonomia máxima deve ser um valor positivo!");
        }else if (currentBatteryCharge < 0 || currentBatteryCharge > 100) {
            error = ("A bateria só pode ir de 0% à 100%");
        }
        else if (consumeKwhPerKm <= 0) {
            error = ("O consumo de kWh por Km deve ser um valor positivo!");
        }
        else if (fullRechargeTime <= 0) {
            error = ("o tempo de recarga da bateria deve ser um valor positivo!");
        }
        else if (fastCharging < 0) {
            error = ("O tempo de recarga rápida da bateria deve ser um valor positivo!");
        }
        if (error != null) {
            view.displayError(error);
            return;
        }
        VehicleModel vehicle = new ElectricVehicleModel(model, maximumAutonomy, currentBatteryCharge, consumeKwhPerKm, fullRechargeTime, connectorType, fastCharging);
        repository.add(vehicle);
        view.displayMessage("Veiculo cadastrado no sistema!");
        view.displayVehicleData((ElectricVehicleModel) vehicle);
    }
    public void addHybridVehicle (String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr, String fuelTankCapacityStr, String fuelConsumptionStr, String fuelType) {
        addVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        this.error = null;
        if (error == null) {
            error = isAnyBlank(fuelTankCapacityStr, fuelConsumptionStr, fuelType);
        }
        if (error == null) {
            error = isDouble(fuelTankCapacityStr, "A capacidade do tanque de combustivel deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(fuelConsumptionStr, "O consumo de combustivel do motor deve ser um valor valido!");
        }
        if (error != null) {
            view.displayError(error);
            return;
        }
        double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
        double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
        double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
        int fullRechargeTime = Integer.parseInt(fullRechargeTimeStr);
        double fuelTankCapacity = Double.parseDouble(fuelTankCapacityStr);
        double fuelConsumption = Double.parseDouble(fuelConsumptionStr);

        if (maximumAutonomy <= 0) {
            error = ("A autonomia máxima deve ser um valor positivo!");
        }else if (currentBatteryCharge < 0 || currentBatteryCharge > 100) {
            error = ("A bateria só pode ir de 0% à 100%");
        }else if (consumeKwhPerKm <= 0) {
            error = ("O consumo de kWh por Km deve ser um valor positivo!");
        }else if (fullRechargeTime <= 0) {
            error = ("o tempo de recarga da bateria deve ser um valor positivo!");
        }else if (fuelTankCapacity <= 0) {
            error = ("A capacidade do tanque de combustivel deve ser valor positivo!");
        }else if (fuelConsumption <= 0) {
            error = ("O consumo do combustivel deve ser valor positivo!");
        }
        if (error != null) {
            view.displayError(error);
            return;
        }
        VehicleModel vehicle = new HybridVehicleModel(model, maximumAutonomy, currentBatteryCharge, consumeKwhPerKm, fullRechargeTime, fuelTankCapacity, fuelConsumption, fuelType);
        repository.add(vehicle);
        view.displayMessage("Veiculo cadastrado no sistema!");
        view.displayVehicleData((HybridVehicleModel) vehicle);
    }
    public String vehicleType(int id) {
        VehicleModel vehicleFound = repository.searchById(id);
        if (vehicleFound != null) {
            if (vehicleFound instanceof ElectricVehicleModel) {
                return "1";
            }else if (vehicleFound instanceof HybridVehicleModel) {
                return "2";
            }
        }
        return "veiculo não encontrado no sistema!";
    }
    public void searchVehicleById (int id) {
        VehicleModel vehicleFound = repository.searchById(id);
        String result = vehicleType(id);
        switch (result) {
            case "1" -> {
                view.displayMessage("Veiculo encontrado!");
                view.displayVehicleData((ElectricVehicleModel) vehicleFound);
            }
            case "2" -> {
                view.displayMessage("Veiculo encontrado!");
                view.displayVehicleData((HybridVehicleModel) vehicleFound);
            }
            default -> view.displayError(result);
        }
    }
    private VehicleModel updateVehicle(int id,String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        VehicleModel vehicleFound = repository.searchById(id);
        if (vehicleFound != null) {
            if (model != null && !model.isBlank()) {
                vehicleFound.setModel(model);
            }
            if (maximumAutonomyStr != null && !maximumAutonomyStr.isBlank()) {
                if (validDouble(maximumAutonomyStr)) {
                    double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
                    if (maximumAutonomy > 0) {
                        vehicleFound.setMaximumAutonomy(maximumAutonomy);
                    }else {
                        view.displayError("A autonomia máxima deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("A autonomia máxima deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (currentBatteryChargeStr != null && !currentBatteryChargeStr.isBlank()) {
                if (validDouble(currentBatteryChargeStr)) {
                    double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
                    if (currentBatteryCharge >= 0 && currentBatteryCharge <= 100) {
                        vehicleFound.setCurrentBatteryCharge(currentBatteryCharge);
                    }else {
                        view.displayError("A bateria só pode ir de 0% à 100% (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("A carga atual da bateria deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (consumeKwhPerKmStr != null && !consumeKwhPerKmStr.isBlank()) {
                if (validDouble(consumeKwhPerKmStr)) {
                    double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
                    if (consumeKwhPerKm > 0) {
                        vehicleFound.setConsumeKwhPerKm(consumeKwhPerKm);
                    }else {
                        view.displayError("O consumo de kWh por Km deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("O consumo de energia deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (fullRechargeTimeStr != null && !fullRechargeTimeStr.isBlank()) {
                if (validInt(fullRechargeTimeStr)) {
                    int fullrechargeTime = Integer.parseInt(fullRechargeTimeStr);
                    if (fullrechargeTime > 0) {
                        vehicleFound.setFullRechargeTime(fullrechargeTime);
                    }else {
                        view.displayError("O tempo de recarga da bateria deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                        view.displayError("O tempo de recarga completa deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            return vehicleFound;
        }
        return null;
    }
    public void updateElectricVehicle (int id,String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr,  String connectorType, String fastChargingStr) {
        VehicleModel vehicleFound = updateVehicle(id, model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        if (vehicleFound != null) {
            ElectricVehicleModel vehicle = (ElectricVehicleModel) vehicleFound;
            if (connectorType != null && !connectorType.isBlank()) {
                vehicle.setConnectorType(connectorType);
            }
            if (fastChargingStr != null && !fastChargingStr.isBlank()) {
                if (validInt(fastChargingStr)) {
                    int fastCharging = Integer.parseInt(fastChargingStr);
                    if (fastCharging > 0 && fastCharging < vehicle.getFullRechargeTime()) {
                        vehicle.setFastCharging(fastCharging);
                    }else {
                        view.displayError("O tempo de recarga rápida da bateria deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("O tempo de recarga rápida deve se um valor válido! (Será mantido o valor anterior)");
                }
            }
            boolean result = repository.update(vehicle);
            if (result) {
              view.displayMessage("Veiculo atualizado com sucesso!");
              view.displayVehicleData(vehicle);
            }else {
                view.displayError("veiculo não pode ser atualizado!");
            }
        }else {
            view.displayError("Veiculo não encontrao no sistema!");
        }
    }
    public void updateHybridVehicle (int id,String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr, String fuelTankCapacityStr, String fuelConsumptionStr, String fuelType) {
        VehicleModel vehicleFound = updateVehicle(id, model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        if (vehicleFound != null) {
            HybridVehicleModel vehicle = (HybridVehicleModel) vehicleFound;
            if (fuelType != null && !fuelType.isBlank()) {
                vehicle.setFuelType(fuelType);
            }
            if (fuelTankCapacityStr != null && !fuelTankCapacityStr.isBlank()) {
                if (validDouble(fuelTankCapacityStr)) {
                    double fuelTankCapacity = Double.parseDouble(fuelTankCapacityStr);
                    if (fuelTankCapacity > 0) {
                        vehicle.setFuelTankCapacity(fuelTankCapacity);
                    }else {
                        view.displayError("A capacidade do tanque de combustivel deve ser valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("A capacidade do tanque de combustivel deve ser um valor válido! (Será mantido o valor anterior)");
                }
            }
            if (fuelConsumptionStr != null && !fuelConsumptionStr.isBlank()) {
                if (!validDouble(fuelConsumptionStr)) {
                    double fuelConsumption = Double.parseDouble(fuelConsumptionStr);
                    if (fuelConsumption > 0) {
                        vehicle.setFuelConsumption(fuelConsumption);
                    }else {
                        view.displayError("O consumo do combustivel deve ser valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("O consumo de combustivel do motor deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            boolean result = repository.update(vehicle);
            if (result) {
                view.displayMessage("Veiculo atualizado com sucesso!");
                view.displayVehicleData(vehicle);
            }else {
                view.displayError("veiculo não pode ser atualizado!");
            }
        }else {
            view.displayError("Veiculo não encontrao no sistema!");
        }
    }
    public void deleteVehicleById(int id) {
        boolean result = repository.deleteById(id);
        if (result) {
            view.displayMessage("Veiculo deletado com sucesso!");
        }else {
            view.displayError("Veiculo não encontrado no sistema!");
        }
    }
    public void listVehicles() {
        VehicleModel[] vehicles = repository.getVehicles();
        if (vehicles == null || vehicles.length == 0) {
            view.displayError("Nenhum veiculo cadastrado no sistema!");
        } else {
            for (VehicleModel vehicle : vehicles) {
                if (vehicle != null) {
                    String type = vehicleType(vehicle.getId());
                    switch (type) {
                        case "1" -> view.displayVehicleData((ElectricVehicleModel) vehicle);
                        case "2" -> view.displayVehicleData((HybridVehicleModel) vehicle);
                    }
                }
            }
        }
    }
}