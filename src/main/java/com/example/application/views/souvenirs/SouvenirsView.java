package com.example.application.views.souvenirs;

import com.example.application.views.souvenirs.components.Toolbar;
import com.example.application.data.Souvenirs;

import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
//import com.example.application.views.klaudeta.PaginatedGrid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.souvenirs.components.SouvenirForm;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.klaudeta.PaginatedGrid;


/**
 * цей клас відображає список сувенірів у вигляді таблиці
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Souvenirs")
public class
SouvenirsView extends VerticalLayout {
    PaginatedGrid<Souvenirs, String> grid = new PaginatedGrid<>(Souvenirs.class);
    SouvenirForm form; // форма для редагування сувенірів
    CrmService service; // сервіс для роботи з базою даних
    Toolbar toolbar;


    public SouvenirsView(CrmService service) { // конструктор класу
        this.service = service;
        toolbar=new Toolbar(grid,service, this::addSouvenir); // панель інструментів
        VaadinSession.getCurrent().setAttribute("service",service);
        addClassName("list-view");
        setSizeFull(); // встановлюємо розмір вікна на весь екран
        configureGrid(); // налаштовуємо таблицю
        configureForm(); // налаштовуємо форму

        add(toolbar, getContent()); // додаємо панель інструментів та контент на форму
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
        grid.setItems(service.getSouvenirs());
    }
}