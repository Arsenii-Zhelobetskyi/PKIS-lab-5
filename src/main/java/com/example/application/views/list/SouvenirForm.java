package com.example.application.views.list;

import com.example.application.data.Souvenirs;
//import com.example.application.data.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

/**
 * Форма для редагування сувенірів. Цей клас використовується для відображення форми для редагування сувенірів.
 * Вона містить поля для введення даних про сувенір, такі як назва, виробник, дата виготовлення та ціна.
 * Також вона містить кнопки для збереження, видалення та закриття форми.
 * <p>
 * Ця форма автоматично валідує введені дані та відображає помилки, якщо такі є. Та також вона автоматично зберігає введені дані в об'єкт Souvenirs.
 */
public class SouvenirForm extends FormLayout {
    TextField name = new TextField("Souvenir name");
    TextField manufacturer_s_details = new TextField("Manufacturer details");

    DatePicker date = new DatePicker("Date of production");
    NumberField price = new NumberField("Price of the souvenir");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<Souvenirs> binder = new BeanValidationBinder<>(Souvenirs.class); // біндер для автоматичної валідації та збереження даних

    public SouvenirForm() {
        addClassName("souvenir-form");
        binder.bindInstanceFields(this); // біндер автоматично зв'язує поля форми з полями класу Souvenirs


        // Встановлюємо плейсхолдери для полів вводу
        name.setPlaceholder("Alaska magnet");
        manufacturer_s_details.setPlaceholder("Alaska souvenirs id");
        date.setPlaceholder("2021-01-01");
        price.setPlaceholder("100");

        // Додамо значок долара перед полем вводу ціни
        Div dollarPrefix= new Div();
        dollarPrefix.setText("$");
        price.setPrefixComponent(dollarPrefix);


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


    public void setSouvenir(Souvenirs souvenir) {
        binder.setBean(souvenir);
    }  // метод для встановлення сувеніру, який буде відображатися в формі, при її відкритті

    // Events
    public static abstract class SouvenirFormEvent extends ComponentEvent<SouvenirForm> { // абстрактний клас для подій форми, конвенція
        private Souvenirs souvenir;

        protected SouvenirFormEvent(SouvenirForm source, Souvenirs souvenir) { // конструктор класу
            super(source, false);
            this.souvenir = souvenir;
        }

        public Souvenirs getSouvenir() {
            return souvenir;
        } // метод для отримання сувеніру, який був відредагований
    }

    public static class SaveEvent extends SouvenirFormEvent { // клас для події збереження
        SaveEvent(SouvenirForm source, Souvenirs souvenir) {
            super(source, souvenir);
        }
    }

    public static class DeleteEvent extends SouvenirFormEvent { // клас для події видалення
        DeleteEvent(SouvenirForm source, Souvenirs souvenir) {
            super(source, souvenir);
        }

    }

    public static class CloseEvent extends SouvenirFormEvent {  // клас для події закриття
        CloseEvent(SouvenirForm source) {
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



