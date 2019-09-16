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
        // Label x = new Label();
        add(datePicker);


        //  add(x);
        // x.add("dd");
        datePicker.addValueChangeListener(
                event -> {
                  //remove(tamaGrid);

                    //  tamaGrid.setItems(currencyController.getCurrencyRates(datePicker.getValue()).getRates());
                    // tamaGrid.removeColumnByKey("Additional Properties");
                    // tamaGrid.removeColumnByKey("No");
                    List<Rate> x = currencyController.getCurrencyRates(datePicker.getValue()).getRates();
                    Double elementBeforeBid = currencyController.getCurrencyRates(datePicker.getValue()).getRates().get(0).getBid();
                    Double elementBeforeAsk = currencyController.getCurrencyRates(datePicker.getValue()).getRates().get(0).getAsk();
                    for (Rate element : x) {
                        element.setAskDiff(BigDecimal.valueOf(element.getAsk()).subtract(BigDecimal.valueOf(elementBeforeAsk)));
                        element.setBidDiff(BigDecimal.valueOf(element.getBid()).subtract(BigDecimal.valueOf(elementBeforeBid)));
                        elementBeforeAsk = element.getAsk();
                        elementBeforeBid = element.getBid();
                    }
                    //tamaGrid.removeAllColumns();

                    tamaGrid.setItems(x);


                    add(tamaGrid);
                    //Rate z  = currencyController.getCurrencyRates(datePicker.getValue()).getRates().get(1);
                    //   x.add(String.valueOf(z.getAsk()));
                });

    }

}
