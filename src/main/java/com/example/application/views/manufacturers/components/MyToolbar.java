package com.example.application.views.manufacturers.components;

import com.example.application.data.Manufacturers;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.vaadin.klaudeta.PaginatedGrid;

public class MyToolbar extends HorizontalLayout {
    public TextField searchById;
    public TextField searchByName;
    public TextField searchByCountry;

    PaginatedGrid<Manufacturers, String> grid;
    CrmService service;
    Button addManufacturer;

    public MyToolbar(PaginatedGrid<Manufacturers, String> grid, CrmService service, Runnable addManufacturerFunc) {
        addClassName("toolbar");
        this.grid = grid;
        this.service = service;

        searchById = configureTextField(searchById, "Find by id", "0152a121", "id");
        searchByName = configureTextField(searchByName, "Find by name", "Marvel dc", "name");
        searchByCountry = configureTextField(searchByCountry, "Find by country", "Ukraine", "country");
        addManufacturer = new Button("Add manufacturer");

        addManufacturer.addClickListener(click -> addManufacturerFunc.run());


        setAlignItems(Alignment.BASELINE);
        add(searchById, searchByName, searchByCountry, addManufacturer);
    }

    TextField configureTextField(TextField field, String label, String placeHolder, String fieldName) {
        field = new TextField();

        field.setLabel(label);
        field.setPlaceholder(placeHolder);
        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.LAZY); // затримка перед викликом події

        TextField finalField = field;
        field.addValueChangeListener(e -> grid.setItems(service.searchManufacturersByField(fieldName, finalField.getValue())));
        return field;
    }
}
