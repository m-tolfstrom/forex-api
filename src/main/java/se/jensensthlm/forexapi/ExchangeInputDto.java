package se.jensensthlm.forexapi;

public record ExchangeInputDto(String currency, double amount) {
    @Override
    public String toString() {

        return "%s, %f".formatted(currency, amount);
    }
}
