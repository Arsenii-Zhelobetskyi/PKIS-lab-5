package com.example.application.views.manufacturers.components;

import com.example.application.data.Manufacturers;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;

/**
 * Форма для редагування виробників. Цей клас використовується для відображення форми для редагування виробників.
 * Вона містить поля для введення даних про виробника, такі як назва, країна, опис, електронна пошта та веб-сайт.
 * Також вона містить кнопки для збереження, видалення та закриття форми.
 * <p>
 * Ця форма автоматично валідує введені дані та відображає помилки, якщо такі є. Та також вона автоматично зберігає введені дані в об'єкт Manufacturers.
 */
public class ManufacturerForm extends FormLayout{
TextField name = new TextField("Manufacturer name");
TextField country = new TextField("Country");

TextArea description = new TextArea("Description");

EmailField email = new EmailField("Email");

TextField website = new TextField("Website");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    Binder<Manufacturers> binder = new BeanValidationBinder<>(Manufacturers.class); // біндер для автоматичної валідації та збереження даних

    public ManufacturerForm(){
        addClassName("manufacturer-form");
        binder.bindInstanceFields(this); // біндер автоматично зв'язує поля форми з полями класу Manufacturers

        name.setPlaceholder("Apple");
        country.setPlaceholder("USA");
        email.setPlaceholder("test@gmail.com");
        website.setPlaceholder("www.apple.com");
        description.setPlaceholder("Apple Inc. is an American multinational technology company that specializes in consumer electronics, computer software, and online services.");

        description.setMaxLength(140);
        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + 140);
        });


        add(
                name,
                country,
                email,
                website,
                description,
                createButtonsLayout()
        );
    }

    public Component createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());

        // this: Це посилання на поточний об'єкт SouvenirForm, з якого відбувається відправка події.
        // binder.getBean(): Це об'єкт даних, який був зв'язаний з формою через binder. Він представляє дані, які були введені користувачем у форму.
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));

        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }
    private void validateAndSave() { // метод для валідації та збереження даних
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }
    public void setManufacturer(Manufacturers manufacturer) {
        binder.setBean(manufacturer);
    }  // метод для встановлення сувеніру, який буде відображатися в формі, при її відкритті



    public static abstract class ManufacturerFormEvent extends ComponentEvent<ManufacturerForm> { // абстрактний клас для подій форми, конвенція
        private Manufacturers manufacturer;

        protected ManufacturerFormEvent(ManufacturerForm source, Manufacturers manufacturer) { // конструктор класу
            super(source, false);
            this.manufacturer = manufacturer;
        }

        public Manufacturers getSouvenir() {
            return manufacturer;
        } // метод для отримання сувеніру, який був відредагований
    }

    public static class SaveEvent extends ManufacturerFormEvent { // клас для події збереження
        SaveEvent(ManufacturerForm source, Manufacturers manufacturer) {
            super(source, manufacturer);
        }
    }

    public static class DeleteEvent extends ManufacturerFormEvent { // клас для події видалення
        DeleteEvent(ManufacturerForm source, Manufacturers manufacturer) {
            super(source, manufacturer);
        }

    }

    public static class CloseEvent extends ManufacturerFormEvent {  // клас для події закриття
        CloseEvent(ManufacturerForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) { // метод для додавання слухача події видалення
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) { // метод для додавання слухача події збереження
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) { // метод для додавання слухача події закриття
        return addListener(CloseEvent.class, listener);
    }




}
