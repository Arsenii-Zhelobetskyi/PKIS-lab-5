package com.example.application.views.list;

import com.example.application.data.Souvenirs;
//import com.example.application.data.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


import java.util.List;

public class SouvenirForm extends FormLayout {


    TextField name = new TextField("Souvenir name");
//    ComboBox<Status> manufacturer_s_details = new ComboBox<>("Select manufacturer");
    TextField manufacturer_s_details = new TextField("Manufacturer details");
//    DatePicker date = new DatePicker("Date of production");
    TextField date = new TextField("Date of production");
    NumberField price = new NumberField("Price of the souvenir");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<Souvenirs> binder = new BeanValidationBinder<>(Souvenirs.class);

    public SouvenirForm() {
        addClassName("souvenir-form");
        binder.bindInstanceFields(this);

//        manufacturer_s_details.setItems(statuses);
//        manufacturer_s_details.setItemLabelGenerator(Status::getName);

        add(name,
                date,
                price,
                manufacturer_s_details,
                createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }



    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }


    public void setSouvenir(Souvenirs souvenir) {
        binder.setBean(souvenir);
    }

    // Events
    public static abstract class SouvenirFormEvent extends ComponentEvent<SouvenirForm> {
        private Souvenirs souvenir;

        protected SouvenirFormEvent(SouvenirForm source, Souvenirs souvenir) {
            super(source, false);
            this.souvenir = souvenir;
        }

        public Souvenirs getSouvenir() {
            return souvenir;
        }
    }

    public static class SaveEvent extends SouvenirFormEvent {
        SaveEvent(SouvenirForm source, Souvenirs contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends SouvenirFormEvent {
        DeleteEvent(SouvenirForm source, Souvenirs contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends SouvenirFormEvent {
        CloseEvent(SouvenirForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

}


// Events
