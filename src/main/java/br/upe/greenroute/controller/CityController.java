package br.upe.greenroute.controller;

import br.upe.greenroute.model.CityModel;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.view.ViewManager;
import iaService.AIPlannerService;

import java.util.List;

public class CityController extends BaseController{
    private final CityRepository repository;
    private final ViewManager view;
    private final ChargingStationRepository stationRepository;
    private final AIPlannerService aiService;
    public CityController (CityRepository repository, ViewManager view, ChargingStationRepository stationRepository, AIPlannerService aiService) {
        this.repository = repository;
        this.view = view;
        this.stationRepository = stationRepository;
        this.aiService = aiService;
    }
    public void fastAddCity() {
        String userText = view.showAiFormDialog();
        if (userText != null && !userText.trim().isEmpty()) {
            try{
               String[] aiData = aiService.parseCity(userText);
               if (aiData != null && aiData.length != 0) {
                   addCity(aiData);
               }else {
                   view.displayError("A IA não conseguiu compreender o texto informado!");
               }
            } catch (Exception e) {
                view.displayError("Erro ao processar dados com IA!, por favor, tente novamente.");
            }
        }
    }
    public void addCity (String[] aiData) {
        String[] data = view.showCityFormDialog(aiData);
        if (data == null) {
            return;
        }
        String name = data[0];
        String state = data[1].toUpperCase();
        String capitalDistanceStr = data[2];
        String error;
        error = isAnyBlank(name, state, capitalDistanceStr);
        if (error == null) {
            error = isDouble(capitalDistanceStr, "A distância deve ser um valor decimal");
        }
        if (error != null) {
            view.displayError(error);
            return;
        }
        try {
            double capitalDistance = Double.parseDouble(capitalDistanceStr);
            CityModel city = new CityModel(name, state, capitalDistance);
            repository.add(city);
            view.displaySuccess("Cidade cadastrada com sucesso!");
            view.refreshCityList(listCities());
        }catch (NumberFormatException e) {
            view.displayError("A distância da capital deve ser um numeral!");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }
    public void filterCities() {
        String searchType = view.getSearchtype();
        String input = view.getSearchinput();
        if (input.isEmpty() || searchType.equals("Todos")) {
            view.refreshCityList(listCities());
            return;
        }
        if (searchType.equals("ID")) {
            try {
                int id = Integer.parseInt(input);
                CityModel city = repository.searchById(id);
                if (city != null) {
                    view.refreshCityList(List.of(city));
                } else{
                    view.refreshCityList(List.of());
                    view.displayError("Nenhuma cidade encontrada com o ID: "+id);
                }
            } catch (NumberFormatException e) {
                view.displayError("O ID informado deve ser um número inteiro válido!");
            }
        } else if (searchType.equals("Estado (UF)")) {
            List<CityModel> filteredCities = repository.searchByState(input.toUpperCase());
            view.refreshCityList(filteredCities);
            if (filteredCities.isEmpty()) {
                view.displayError("Nenhuma cidade encontrada no estado: "+ input.toUpperCase());
            }
        }
    }
    public void updateCity() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.displayError("Por favor, selecione uma cidade na tabela para editar!");
            return;
        }
        int id = view.getSelectedId();
        CityModel cityToEdit = repository.searchById(id);
        if (cityToEdit != null) {
            String[] currentData = new String[] {
                    cityToEdit.getName(),
                    cityToEdit.getState(),
                    String.valueOf(cityToEdit.getCapitalDistance())
            };
            String[] datas = view.showCityFormDialog(currentData);
            if (datas == null) {
                return;
            }
            String name = datas[0];
            String state = datas[1];
            String capitalDistanceStr = datas[2];
            if (name != null && !name.isBlank()) {
                cityToEdit.setName(name);
            }
            if (state != null && !state.isBlank()) {
                cityToEdit.setState(state);
            }
            try {
                if (capitalDistanceStr != null && !capitalDistanceStr.isBlank()) {
                    double capitalDistance = Double.parseDouble(capitalDistanceStr);
                    if (capitalDistance >= 0) {
                        cityToEdit.setCapitalDistance(capitalDistance);
                    }else {
                        view.displayError("A distância da capital deve ser maior que zero! (Será mantido o valor anterior)");
                    }
                }
                boolean result = repository.update(cityToEdit);
                if (result) {
                    view.displaySuccess("Cidade atualizada com sucesso!");
                    view.refreshCityList(listCities());
                } else {
                    view.displayError("Cidade não pode ser atualizada!");
                }
            } catch (NumberFormatException e) {
                view.displayError("A distância da capital deve ser um numeral!");
            } catch (IllegalArgumentException e) {
                view.displayError(e.getMessage());
            }
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
    }
    public void deleteCityById() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.displayError("Por favor, selecione uma cidade na tabela para excluir!");
            return;
        }
        int id = view.getSelectedId();
        CityModel cityToEdit = repository.searchById(id);
        if (cityToEdit != null) {
            int stationsCount = stationRepository.countStationsByCityId(id);
            String message;
            if (stationsCount > 0) {
                message = "Tem certeza que deseja excluir a cidade: " + cityToEdit.getName() + "?\n"
                        + "ATENÇÃO: Esta cidade possui " + stationsCount + " eletroposto(s) "
                        + "vinculado(s) que também sera/serão excluído(s) do sistema!";
            } else {
                message = "Tem certeza que deseja excluir a cidade: " + cityToEdit.getName() + "?\n"
                        + "(Não há eletropostos vinculados a esta cidade).";
            }
            boolean confirmation = view.confirmAction("Confirmar Exclusão", message);
            if (confirmation) {
                try {
                    if (stationsCount > 0) {
                        if (stationRepository.deleteStationsByCityId(id)) {
                            view.refreshStationList(stationRepository.getStations());
                        }else {
                            view.displayError("Não foi possivel deletar os eletropostos!");
                        }
                    }
                    if (repository.deleteById(id)) {
                        view.displaySuccess("Cidade deletada com sucesso!");
                        view.refreshCityList(listCities());
                    }
                } catch (Exception e) {
                    view.displayError("Erro ao tentar excluir a cidade: " + e.getMessage());
                }
            }
        } else {
            view.displayError("Cidade não encontrada no sistema!");
        }
    }
    public List<CityModel> listCities() {
        return repository.getCities();
    }
}