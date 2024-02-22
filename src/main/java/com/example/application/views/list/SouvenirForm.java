package com.example.application.views.list;

import com.example.application.data.Company;
import com.example.application.data.Status;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class SouvenirForm extends FormLayout {
    TextField name = new TextField("Souvenir name");
    ComboBox<Status> manufacturer_s_details = new ComboBox<>("Select manufacturer");
    DatePicker date = new DatePicker("Date of production");
    EmailField price = new EmailField("Price of the souvenir");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public SouvenirForm(List<Status> statuses) {
        addClassName("souvenir-form");
        manufacturer_s_details.setItems(statuses);
        manufacturer_s_details.setItemLabelGenerator(Status::getName);

        add(name,
                date,
                price,
                manufacturer_s_details,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }
}