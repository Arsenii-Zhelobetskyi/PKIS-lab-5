package com.example.application.views.souvenirs.components;


import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.Locale;


public class MyDatePicker extends HorizontalLayout  {

    DatePicker start = new DatePicker("From");
    DatePicker end = new DatePicker("To");



    public MyDatePicker(PaginatedGrid<Souvenirs, String> grid, CrmService service) {
        setAlignItems(Alignment.BASELINE);

        add(start,new Text(" – "), end);
        start.setPlaceholder("01.01.2021");
        end.setPlaceholder("01.01.2024");
        start.setLocale(new Locale("uk", "UA"));
        end.setLocale(new Locale("uk", "UA"));
        start.addValueChangeListener(e -> {
            if (start.getValue()==null && end.getValue()==null) {
                grid.setItems(service.getSouvenirs());
            }

            if (end.getValue() != null) {
                grid.setItems(service.searchByDateRange(start.getValue(), end.getValue()));
            }

        });

        end.addValueChangeListener(e -> {
            if (start.getValue()==null && end.getValue()==null) {
                grid.setItems(service.getSouvenirs());
            }
            if (start.getValue() != null) {
                grid.setItems(service.searchByDateRange(start.getValue(), end.getValue()));
            }
        });
    }



}
