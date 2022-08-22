package se.jensensthlm.forexapi;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange")
public class ExchangeController {
    private ExchangeCalculator exchangeCalculator = new ExchangeCalculator(new ForexExchangeProvider());

    @GetMapping("/buy")
    public ResponseEntity<ExchangeOutputDto> buy(@RequestBody ExchangeInputDto data){
        var amountToPay = exchangeCalculator.calculateBuy(data.currency(), data.amount());
        var responseBody = new ExchangeOutputDto(exchangeCalculator.getReferenceCurrency(), amountToPay);
        return ResponseEntity.ok().body(responseBody);
    }
    @GetMapping("/sell/{currency}/{amount}")
    public ResponseEntity<ExchangeOutputDto> sell(@PathVariable String currency, @PathVariable double amount){
        var amountToGet = exchangeCalculator.calculateSell(currency, amount);
        var responseBody = new ExchangeOutputDto(exchangeCalculator.getReferenceCurrency(), amountToGet);
        return ResponseEntity.ok().body(responseBody);
    }





}
