package com.example.application.views.list;

import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
//import com.example.application.views.klaudeta.PaginatedGrid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Bottom;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.klaudeta.PaginatedGrid;

/**
 * цей клас відображає список сувенірів у вигляді таблиці
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Souvenirs")
public class
ListView extends VerticalLayout {
    //    Grid<Souvenirs> grid = new Grid<>(Souvenirs.class); // таблиця для відображення сувенірів
    PaginatedGrid<Souvenirs, String> grid = new PaginatedGrid<>(Souvenirs.class);
    TextField filterText = new TextField(); // поле для фільтрації сувенірів
    SouvenirForm form; // форма для редагування сувенірів
    CrmService service; // сервіс для роботи з базою даних


    public ListView(CrmService service) { // конструктор класу
        this.service = service;
        addClassName("list-view");
        setSizeFull(); // встановлюємо розмір вікна на весь екран
        configureGrid(); // налаштовуємо таблицю
        configureForm(); // налаштовуємо форму

        add(getToolbar(), getContent()); // додаємо панель інструментів та контент на форму
        updateList(); // оновлюємо список сувенірів
        closeEditor(); // закриваємо редактор
    }

    private HorizontalLayout getContent() { // метод для отримання контенту

        HorizontalLayout content = new HorizontalLayout(new VerticalLayout(grid), form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() { // метод для налаштування форми
        form = new SouvenirForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveSouvenir);
        form.addDeleteListener(this::deleteSouvenir);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveSouvenir(SouvenirForm.SaveEvent event) {  // метод для збереження сувеніру
        service.saveSouvenir(event.getSouvenir());
        updateList();
        closeEditor();
    }

    private void deleteSouvenir(SouvenirForm.DeleteEvent event) { // метод для видалення сувеніру
        service.deleteSouvenir(event.getSouvenir());
        updateList();
        closeEditor();
    }

    private void configureGrid() { // метод для налаштування таблиці
        grid.addClassNames("souvenirs-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "manufacturer_s_details", "date", "price");
        grid.setPageSize(10);
        grid.setPaginatorSize(5);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editSouvenir(event.getValue()));

    }

    private HorizontalLayout getToolbar() { // метод для отримання панелі інструментів
        filterText.setPlaceholder("Find souvenir by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); // затримка перед викликом події
        filterText.addValueChangeListener(e -> updateList());
        Button addContactButton = new Button("Add souvenir");
        addContactButton.addClickListener(click -> addSouvenir());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editSouvenir(Souvenirs souvenir) { // метод для редагування сувеніру
        if (souvenir == null) {
            closeEditor();
        } else {
            form.setSouvenir(souvenir);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() { // метод для закриття редактора
        form.setSouvenir(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addSouvenir() { // метод для додавання сувеніру
        grid.asSingleSelect().clear();
        editSouvenir(new Souvenirs());
    }

    private void updateList() { // метод для оновлення списку сувенірів
        grid.setItems(service.findAllSouvenirs(filterText.getValue()));
    }
}