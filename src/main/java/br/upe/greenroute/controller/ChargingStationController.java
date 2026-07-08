package br.upe.greenroute.controller;

import br.upe.greenroute.exceptions.AIServiceException;
import br.upe.greenroute.exceptions.EntityNotFoundException;
import br.upe.greenroute.exceptions.GreenRouteException;
import br.upe.greenroute.model.ChargingStationModel;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.view.ViewManager;
import iaService.AIPlannerService;

import java.util.Arrays;
import java.util.List;

public class ChargingStationController extends BaseController {
    private final ChargingStationRepository repository;
    private final ViewManager view;
    private final CityRepository cityRepository;
    private final AIPlannerService aiService;

    public ChargingStationController (ChargingStationRepository repository, CityRepository cityRepository, ViewManager view, AIPlannerService aiService) {
        this.repository = repository;
        this.view = view;
        this.cityRepository = cityRepository;
        this.aiService = aiService;
    }
    public void fastAddStation() {
        String userText = view.showAiFormDialog();
        if (userText != null && !userText.trim().isEmpty()) {
            try {
                String[] aiData = aiService.parseStation(userText);
                if (aiData != null && aiData.length != 0) {
                    addChargingStation(aiData);
                } else {
                    view.displayError("A IA não conseguiu compreender o texto informado!");
                }
            }  catch (AIServiceException e) {
                view.displayError("Erro ao processar dados com IA!"+e.getMessage());
            } catch (GreenRouteException e) {
                view.displayError(e.getMessage());
            } catch (Exception e) {
                view.displayError("Erro inesperado: "+e.getMessage());
            }
        }
    }
    private String validateChargingStation(String name, String location,String cityIdStr, String availableConnectorsType, String chargingPowerKWStr, String pricePerKWhStr, String availableVacanciesStr) {
        String error;
        error = isAnyBlank(name, location, cityIdStr, availableConnectorsType, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr);
        if (error != null) {
            return error;
        }
            error = isInt(cityIdStr, "Id da cidade deve ser um valor inteiro!");
            if (error == null) {
                error = isInt(availableVacanciesStr, "Quantidade de vagas inválida!");
            }
            if (error == null) {
                error = isDouble(chargingPowerKWStr, "Potência de carregamento inválida!");
            }
            if (error == null) {
                error = isDouble(pricePerKWhStr, "Preço por kWh inválido!");
            }
        return error;
    }
    public void addChargingStation(String[] aiData) {
        String[] datas = view.showStationFormDialog(aiData);
        if (datas == null) {
            return;
        }
        String name = datas[0];
        String location = datas[1];
        String cityIdStr = datas[2];
        String availableConnectorsTypeStr = datas[3];
        String chargingPowerKWStr = datas[4];
        String pricePerKWhStr = datas[5];
        String availableVacanciesStr  = datas[6];
        String error = validateChargingStation(name, location, cityIdStr, availableConnectorsTypeStr, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr);
        if (error != null) {
            view.displayError(error);
            return;
        }
        try {
            int cityId = Integer.parseInt(cityIdStr);
            double chargingPowerKW = Double.parseDouble(chargingPowerKWStr);
            double pricePerKWh = Double.parseDouble(pricePerKWhStr);
            int availableVacancies = Integer.parseInt(availableVacanciesStr);
            cityRepository.searchById(cityId);
            List<String> availableConnectorsType = Arrays.asList(availableConnectorsTypeStr.trim().split(","));
            ChargingStationModel chargingStation = new ChargingStationModel(name, location, cityId, availableConnectorsType, chargingPowerKW, pricePerKWh, availableVacancies);
            repository.add(chargingStation);
            view.displaySuccess("Eletroposto cadastrado no sistema!");
            view.refreshStationList(listChargingStations());
        } catch (NumberFormatException e) {
            view.displayError("dados númericos invalidos!");
        } catch (EntityNotFoundException e) {
            view.displayError(e.getMessage());
        } catch (GreenRouteException e) {
            view.displayError(e.getMessage());
        }

    }
    public void filterChargingStations() {
        String searchType = view.getSearchtype();
        String input = view.getSearchinput();
        if (input.isEmpty() || searchType.equals("Todos")) {
            view.refreshStationList(listChargingStations());
            return;
        }
        if (searchType.equals("ID")) {
            try {
                int id = Integer.parseInt(input);
                ChargingStationModel station = repository.searchById(id);
                view.refreshStationList(List.of(station));
            } catch (NumberFormatException e) {
                view.displayError("O ID informado deve ser um número inteiro válido!");
            } catch (EntityNotFoundException e) {
                view.refreshStationList(List.of());
                view.displayError(e.getMessage());
            } catch (GreenRouteException e) {
                view.displayError(e.getMessage());
            }
        }else if (searchType.equals("Cidade")){
            try {
                int id = Integer.parseInt(input);
                List<ChargingStationModel> cityStations = repository.searchByCityId(id);
                view.refreshStationList(cityStations);
                if (cityStations.isEmpty()) {
                    view.displayError("Nenhum eletroposto encontrado nesta cidade, (ID): " + input.toUpperCase());
                }
            } catch (NumberFormatException e) {
                view.displayError("O ID informado deve ser um número inteiro válido!");
            } catch (EntityNotFoundException e) {
                view.displayError(e.getMessage());
            }
        }
    }

    public void updateChargingStation() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.displayError("Por favor, selecione um eletroposto na tabela para editar!");
            return;
        }
        try {
            int id = view.getSelectedId();
            ChargingStationModel stationToEdit = repository.searchById(id);
            String currentConnectors = String.join(",", stationToEdit.getAvailableConnectorsType());
            String[] currentData = new String[]{
                    stationToEdit.getName(),
                    stationToEdit.getLocation(),
                    String.valueOf(stationToEdit.getCityId()),
                    currentConnectors,
                    String.valueOf(stationToEdit.getChargingPowerKW()),
                    String.valueOf(stationToEdit.getPricePerKWh()),
                    String.valueOf(stationToEdit.getAvailableVacancies())
            };
            String[] datas = view.showStationFormDialog(currentData);
            if (datas == null) {
                return;
            }
            String name = datas[0];
            String location = datas[1];
            String cityIdStr = datas[2];
            String availableConnectorsTypeStr = datas[3];
            String chargingPowerKWStr = datas[4];
            String pricePerKWhStr = datas[5];
            String availableVacanciesStr  = datas[6];
            if (name != null && !name.isBlank()) {
                stationToEdit.setName(name);
            }
            if (location != null && !location.isBlank()) {
                stationToEdit.setLocation(location);
            }
            if (availableConnectorsTypeStr != null && !availableConnectorsTypeStr.isBlank()) {
                List<String> availableConnectorsType = Arrays.asList(availableConnectorsTypeStr.trim().split(","));
                if (availableConnectorsType.size() != 0) {
                    stationToEdit.setAvailableConnectorsType(availableConnectorsType);
                }
            }
            if (chargingPowerKWStr != null && !chargingPowerKWStr.isBlank()) {
                if(validDouble(chargingPowerKWStr)) {
                    double chargingPowerKW = Double.parseDouble(chargingPowerKWStr);
                    if (chargingPowerKW > 0) {
                        stationToEdit.setChargingPowerKW(chargingPowerKW);
                    }else {
                        view.displayError("A potência do carregador deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Potência de carregamento inválida! (Será mantido o valor anterior)");
                }
            }
            if (pricePerKWhStr != null && !pricePerKWhStr.isBlank()) {
                if(validDouble(pricePerKWhStr)) {
                    double pricePerKWh = Double.parseDouble(pricePerKWhStr);
                    if (pricePerKWh >= 0) {
                        stationToEdit.setPricePerKWh(pricePerKWh);
                    }else {
                        view.displayError("O preço por quilowatt-hora deve ser no mínimo 0! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Preço por kWh inválido! (Será mantido o valor anterior)");
                }
            }
            if (availableVacanciesStr != null && !availableVacanciesStr.isBlank()) {
                if (validInt(availableVacanciesStr)) {
                    int availableVacancies = Integer.parseInt(availableVacanciesStr);
                    if (availableVacancies >= 0) {
                        stationToEdit.setAvailableVacancies(availableVacancies);
                    }else {
                        view.displayError("A quantidade de vagas disponíveis deve ser no mínimo 0! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Quantidade de vagas inválida! (Será mantido o valor anterior)");
                }
            }
            repository.update(stationToEdit);
            view.displaySuccess("Eletroposto atualizado com sucesso!");
            view.refreshStationList(listChargingStations());
        } catch (NumberFormatException e) {
            view.displayError("dados númericos invalidos!");
        } catch (EntityNotFoundException e) {
            view.displayError(e.getMessage());
        } catch (GreenRouteException e) {
            view.displayError(e.getMessage());
        } catch (Exception e) {
            view.displayError("Erro inesperado: "+e.getMessage());
        }
    }
    public void deleteChargingStationById() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.displayError("Por favor, selecione um eletroposto na tabela para excluir!");
            return;
        }
        try {
            int id = view.getSelectedId();
            ChargingStationModel stationToDelete = repository.searchById(id);
                String message = "Tem certeza que deseja excluir o eletroposto: " + stationToDelete.getName() + "?";
                boolean confirmation = view.confirmAction("Confirmar Exclusão", message);
                if (confirmation) {
                    repository.deleteById(id);
                    view.displaySuccess("Eletroposto deletado com sucesso!");
                    view.refreshStationList(listChargingStations());
                }
        } catch (EntityNotFoundException e) {
            view.displayError(e.getMessage());
        } catch (GreenRouteException e) {
            view.displayError(e.getMessage());
        } catch (Exception e) {
            view.displayError("Erro inesperado: "+e.getMessage());
        }
    }
    public List<ChargingStationModel> listChargingStations() {
        return repository.getStations();
    }
}