package com.example.application.views.souvenirs.components;

import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import org.vaadin.klaudeta.PaginatedGrid;

public class MyPricePicker extends HorizontalLayout {

    NumberField start = new NumberField("From");
    NumberField end = new NumberField("To");
    public MyPricePicker(PaginatedGrid<Souvenirs, String> grid, CrmService service) {
        start.setPlaceholder("0");
        end.setPlaceholder("1000");
        setAlignItems(Alignment.BASELINE);
        add(start,new Text(" – "), end);

        setDollarSign(start);
        setDollarSign(end);

        start.addValueChangeListener(e -> {

            if (end.getValue() != null) {
                grid.setItems(service.searchByPriceRange(start.getValue(), end.getValue()));
            }

        });

        end.addValueChangeListener(e -> {
            if (start.getValue() != null) {
                grid.setItems(service.searchByPriceRange(start.getValue(), end.getValue()));
            }
        });
    }

    void setDollarSign(NumberField field){
        Div dollarPrefix= new Div();
        dollarPrefix.setText("$");
        field.setPrefixComponent(dollarPrefix);
    }
}
