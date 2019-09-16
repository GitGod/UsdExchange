package USDExchange.USDExchange.gui;

import USDExchange.USDExchange.controller.CurrencyController;
import USDExchange.USDExchange.model.Rate;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Route("")
public class CurrencyGui extends VerticalLayout {
    @Autowired
    public CurrencyGui(CurrencyController currencyController) {
        DatePicker datePicker = new DatePicker();
        Grid<Rate> tamaGrid = new Grid<>();
        tamaGrid.addColumn(Rate::getEffectiveDate).setHeader("Date");
        tamaGrid.addColumn(Rate::getAsk).setHeader("Ask");
        tamaGrid.addColumn(Rate::getBid).setHeader("Bid");
        tamaGrid.addColumn(Rate::getAskDiff).setHeader("AskDiff");
        tamaGrid.addColumn(Rate::getBidDiff).setHeader("BidDiff");   
        add(datePicker);
        datePicker.addValueChangeListener(
                event -> {               
                    List<Rate> rateList = currencyController.getCurrencyRates(datePicker.getValue()).getRates();
                    Double elementBeforeBid = currencyController.getCurrencyRates(datePicker.getValue()).getRates().get(0).getBid();
                    Double elementBeforeAsk = currencyController.getCurrencyRates(datePicker.getValue()).getRates().get(0).getAsk();
                    for (Rate element : rateList) {
                        element.setAskDiff(BigDecimal.valueOf(element.getAsk()).subtract(BigDecimal.valueOf(elementBeforeAsk)));
                        element.setBidDiff(BigDecimal.valueOf(element.getBid()).subtract(BigDecimal.valueOf(elementBeforeBid)));
                        elementBeforeAsk = element.getAsk();
                        elementBeforeBid = element.getBid();
                    }                  
                    tamaGrid.setItems(rateList);
                    add(tamaGrid);
                    
                });

    }

}
