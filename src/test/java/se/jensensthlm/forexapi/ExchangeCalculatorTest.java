package se.jensensthlm.forexapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeCalculatorTest {
    @InjectMocks
    ExchangeCalculator exchangeCalculator; //System under test. This system has dependencies that needs to be injected

    @Mock
    ExchangeProvider exchangeProvider;

    @Test
    @DisplayName("Buy 100 GBP")
    public void TestBuy(){
        var targetCurrency = "GBP";
        var amount = 100.0;
        var rate = 10.0;

        when(exchangeProvider.get("SEK", targetCurrency))
                .thenReturn(new ExchangeDetails("SEK", targetCurrency, rate));

        var expectedPriceInSek = 1000;
        var actualPriceInSek = exchangeCalculator.calculateBuy(targetCurrency, amount);
        Assertions.assertEquals(expectedPriceInSek, actualPriceInSek);
    }

    @Test
    @DisplayName("Sell 100 GBP")
    public void TestSell(){
        var targetCurrency = "GBP";
        var amount = 100.0;
        var rate = 10.0;

        when(exchangeProvider.get("SEK", targetCurrency))
                .thenReturn(new ExchangeDetails("SEK", targetCurrency, rate));

        var expectedPriceInSek = 1000;
        var actualPriceInSek = exchangeCalculator.calculateSell(targetCurrency, amount);
        Assertions.assertEquals(expectedPriceInSek, actualPriceInSek);
    }
}
