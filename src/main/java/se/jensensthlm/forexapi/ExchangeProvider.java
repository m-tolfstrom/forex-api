package se.jensensthlm.forexapi;

public interface ExchangeProvider {
    ExchangeDetails get(String sourceCurrency, String targetCurrency);


}
