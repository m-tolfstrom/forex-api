package se.jensensthlm.forexapi;

public record ExchangeDetails(String sourceCurrency, String targetCurrency, double rate) {
    @Override
    public String toString() {
        return "%s -> %s %f" .formatted(sourceCurrency, targetCurrency, rate);
    }
}
