package iaService;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexaoGemini implements AIPlannerService{
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("GEMINI_API_KEY");
    ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
            .apiKey(apiKey)
            .modelName("gemini-2.5-flash")
            .build();

    @Override
    public String[] parseVehicle(String text) {
        String Prompt =
                ("Analyze the following text and extract the vehicle data\n"
                        + "Return ONLY the values separated by \";;\" in the following order:"
                        +"\"vehicle type;;vehicle model;;maximum range in Kms with full charge;;current battery charge in percentage from 0.0 to 100.0;;consumption of kWh per Km;;estimated time in minutes for full recharge;;type of vehicle connector;;fast recharge in minutes in high-power chargers;;the capacity in liters of the fuel tank;;fuel consumption in Km/l in the combustion engine;;type of fuel\".\n"
                        +"Attention: \n"
                        + "The vehicle type can ONLY strictly receive these two values: \"Elétrico\" or \"Híbrido\" (must be written exactly like this!) and must be returned obligatorily, if it is not in the text, look for the car model or analyze the rest of the text and find out which of the two types it belongs to..\n"
                        + "if the vehicle is \"Elétrico\" assume that the following data does not exist: \"the capacity in liters of the fuel tank,fuel consumption in Km/l in the combustion engine and type of fuel\""
                        + "if the vehicle is \"Híbrido\" assume that the following data does not exist: \"type of vehicle connector andfast recharge in minutes in high-power chargers\""
                        + "If maximum range in Kms with full charge, current battery charge in percentage from 0.0 to 100.0, consumption of kWh per Km, estimated time in minutes for full recharge, fast recharge in minutes in high-power chargers, the capacity in liters of the fuel tank or fuel consumption in Km/l in the combustion engine reported is less than 0, write \"Invalid\" in the field.\n"
                        + "If the data does not exist in the text, put '0' for numbers or '-' for texts.\n"
                        + "If and only if you do not find any data in the text return absolutely anything.\n"
                        + "Use Text:" + text);
        String[] aiExtractedData = chatWork(Prompt);
        return aiExtractedData;
    }
    @Override
    public String[] parseCity(String text) {
        String Prompt =
                ("Analyze the following text and extract the city data\n"
                + "Return ONLY the values separated by \";;\" in the following order: \"name;;state;;distance from the capital in Km\".\n"
                +"Attention: \n"
                + "In the State Field, it must be the Federative Unit (UF) of the state designated in accordance with the Brazilian Constitution. If the typed state is not Brazilian, return the phrase \"Not located!\" in the State field.\n"
                + "If the Distance to the capital reported is less than 0, write \"Invalid\" in the field.\n"
                + "If the data does not exist in the text, put '0' for numbers or '-' for texts.\n"
                + "If and only if you do not find any data in the text return absolutely anything.\n"
                + "Use Text:" + text);
        String[] aiExtractedData = chatWork(Prompt);
        return aiExtractedData;
    }
    @Override
    public String[] parseStation(String text) {
        String Prompt =
                ("Analyze the following text and extract the charging station data\n"
                        + "Return ONLY the values separated by \";;\" in the following order: \"Name;;Location;;city id;;connectors types available in the station;;charger power in kW;;price charged per kWh;;available vacancies \".\n"
                        +"Attention: \n"
                        + "city id is the numeric ID of the city where the station is located.\n"
                        + "If more than one type of connector present in the station is reported,return it in the data, separated by \",\" in this way: \"first,second, third...\".\n"
                        + "If the city id, charger power in kW, price charged per kWh or available vacancies reported is less than 0, write \"Invalid\" in the field.\n"
                        + "If the data does not exist in the text, put '0' for numbers or '-' for texts.\n"
                        + "If and only if you do not find any data in the text return absolutely anything.\n"
                        + "Use Text:" + text);
        String[] aiExtractedData = chatWork(Prompt);
        return aiExtractedData;
    }
    private String [] chatWork(String text) {
        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from(text))
                .build();
        ChatResponse chatResponse = model.chat(request);
        String responseStr = chatResponse.aiMessage().text();
        responseStr = responseStr.trim();
        String[] finalResponse = responseStr.split(";;");
        return finalResponse;
    }
}