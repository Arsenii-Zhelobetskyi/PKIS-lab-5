package com.example.application.views.souvenirs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;



public class Toolbar extends HorizontalLayout {
    TextField filterByName = new TextField("Find souvenir"); // поле для фільтрації сувенірів за назвою
    DatePicker start = new DatePicker("Date");
    DatePicker end = new DatePicker("Date");

    Button addSouvenir = new Button("Add souvenir");

    Toolbar(Runnable updateList, Runnable addSouvenirFunc) {
        addClassName("toolbar");
        filterByName.setPlaceholder("Find souvenir by name...");
        filterByName.setClearButtonVisible(true);
        filterByName.setValueChangeMode(ValueChangeMode.LAZY); // затримка перед викликом події
        filterByName.addValueChangeListener(e -> updateList.run());
        addSouvenir.addClickListener(click -> addSouvenirFunc.run());
        setAlignItems(Alignment.BASELINE);
        add(filterByName, start, end, addSouvenir);
    }
}
