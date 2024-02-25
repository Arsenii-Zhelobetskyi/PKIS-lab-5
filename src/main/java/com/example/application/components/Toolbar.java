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
    Button addSouvenir;


    public Toolbar(PaginatedGrid<Souvenirs, String> grid, CrmService service, Runnable updateList, Runnable addSouvenirFunc) {
        addClassName("toolbar");

        searchById = configureTextField(searchByName, "Find by id", "0152a491", updateList);
        searchByName = configureTextField(searchByName, "Find by name", "Alaska magnet", updateList);

        myDatePicker = new MyDatePicker(grid, service);

        addSouvenir = new Button("Add souvenir");
        addSouvenir.addClickListener(click -> addSouvenirFunc.run());

        setAlignItems(Alignment.BASELINE);
        add(searchById, searchByName, myDatePicker, addSouvenir);
    }


    TextField configureTextField(TextField field, String label, String placeHolder, Runnable onChange) {
        field = new TextField();

        field.setLabel(label);
        field.setPlaceholder(placeHolder);
        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.LAZY); // затримка перед викликом події

        field.addValueChangeListener(e -> onChange.run());
        return field;
    }
}
