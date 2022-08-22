package se.jensensthlm.forexapi;




import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForexExchangeProvider implements ExchangeProvider {
    private final String API_ENDPOINT_FORMAT = "https://api.forex.se/currency/exchangeRates/SWE-%s-%s";

    @Override
    public ExchangeDetails get(String sourceCurrency, String targetCurrency) {
        var endpoint = buildEndpoint(sourceCurrency, targetCurrency);
        var jsonData = fetchJsonExchnageData(endpoint);
        var exchangeRate = extractExchangeRate(jsonData);
        return new ExchangeDetails(sourceCurrency, targetCurrency, exchangeRate);
    }

    private double extractExchangeRate(String jsonExchangeData){
        JSONObject json = new JSONObject(jsonExchangeData);
        return json
                .getJSONObject("data")
                .getJSONObject("attributes")
                .getDouble("rate");
    }

    private String fetchJsonExchnageData(String endpoint){
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(URI.create(endpoint))
                .build();
        try {
            var response =httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e){
            return null;
        }
    }

    private String buildEndpoint (String sourceCurrency, String targetCurrency){
        return API_ENDPOINT_FORMAT.formatted(sourceCurrency, targetCurrency);
    }


}
