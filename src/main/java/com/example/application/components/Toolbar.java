package com.example.application.components;

import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.vaadin.klaudeta.PaginatedGrid;


public class Toolbar extends HorizontalLayout {
    public TextField searchById;
    public TextField searchByName;

    MyDatePicker myDatePicker;

    MyPricePicker myPricePicker;
    Button addSouvenir;


    PaginatedGrid<Souvenirs, String> grid;
    CrmService service;


    public Toolbar(PaginatedGrid<Souvenirs, String> grid, CrmService service, Runnable updateList, Runnable addSouvenirFunc) {

        this.grid = grid;
        this.service = service;


        addClassName("toolbar");

        searchById = configureTextField(searchById, "Find by id", "0152a491", "id");
        searchByName = configureTextField(searchByName, "Find by name", "Alaska magnet", "name");

        myDatePicker = new MyDatePicker(grid, service);
        myPricePicker = new MyPricePicker(grid, service);

        addSouvenir = new Button("Add souvenir");
        addSouvenir.addClickListener(click -> addSouvenirFunc.run());

        setAlignItems(Alignment.BASELINE);
        add(searchById, searchByName, myDatePicker, myPricePicker, addSouvenir);
    }


    TextField configureTextField(TextField field, String label, String placeHolder, String fieldName) {
        field = new TextField();

        field.setLabel(label);
        field.setPlaceholder(placeHolder);
        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.LAZY); // затримка перед викликом події

        TextField finalField = field;
        field.addValueChangeListener(e -> grid.setItems(service.searchByField(fieldName, finalField.getValue())));
        return field;
    }
}
