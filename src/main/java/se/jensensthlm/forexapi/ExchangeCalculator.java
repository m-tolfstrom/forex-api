package se.jensensthlm.forexapi;

public class ExchangeCalculator {
    private String referenceCurrency = "SEK";
    private ExchangeProvider exchangeProvider;

    public ExchangeCalculator(ExchangeProvider exchangeProvider) {
        this.exchangeProvider = exchangeProvider;
    }

    public String getReferenceCurrency() {
        return referenceCurrency;
    }

    /**
     * Calculate how many units of [referenceCurrency] you have to pay in
     * order to buy [amount] of [targetCurrency]
     * @param targetCurrency
     * @param amount
     * @return
     */
    public double calculateBuy(String targetCurrency, double amount){
        checkTargetCurrency(targetCurrency, "Can't buy same currency");
        var rate = getExchangeRate(targetCurrency);
        return amount * rate;
        
    }

    public double calculateSell(String sourceCurrency, double amount){
        checkTargetCurrency(sourceCurrency, "Can't buy same currency");
        var rate = getExchangeRate(sourceCurrency);
        return amount / rate;
    }

    private void checkTargetCurrency(String targetCurrency, String message) {
        if (referenceCurrency.equals(targetCurrency)){
            throw new IllegalArgumentException(message);
        }
    }

    private double getExchangeRate(String targetCurrency){
        var details = exchangeProvider.get(referenceCurrency, targetCurrency);
        if (details == null) {
            throw new IllegalArgumentException("Currency %s does not exist".formatted(targetCurrency));
        }
        return details.rate();
    }
}
/*
Key points here

Single responsibility principle: A class should have only ONE reason to change

*/
