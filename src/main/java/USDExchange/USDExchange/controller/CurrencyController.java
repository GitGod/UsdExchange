package USDExchange.USDExchange.controller;

import USDExchange.USDExchange.model.CurrencyModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class CurrencyController {

    public CurrencyModel getCurrencyRates(LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyModel forObject = restTemplate.getForObject(
                "http://api.nbp.pl/api/exchangerates/rates/C/USD/"+date+"/"+LocalDate.now()+"/",
                CurrencyModel.class);
        return forObject;
    }

}
