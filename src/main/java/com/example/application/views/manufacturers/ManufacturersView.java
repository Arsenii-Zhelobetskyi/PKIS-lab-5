package com.example.application.views.manufacturers;

import com.example.application.data.Manufacturers;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.example.application.views.manufacturers.components.ManufacturerForm;
import com.example.application.views.manufacturers.components.MyToolbar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.klaudeta.PaginatedGrid;

/**
 * Цей клас відображає список виробників у вигляді таблиці
 */

@Route(value = "manufacturers", layout = MainLayout.class)
@PageTitle("Manufacturers")
public class ManufacturersView extends VerticalLayout {
    CrmService service; // сервіс для роботи з базою даних

    PaginatedGrid<Manufacturers, String> grid = new PaginatedGrid<>(Manufacturers.class);
    ManufacturerForm form;
    MyToolbar toolbar;

    public  ManufacturersView(CrmService service) { // конструктор класу
        this.service = service;
        addClassName("list-view");
        toolbar=new MyToolbar(grid,service, this::addManufacturer); // панель інструментів

        setSizeFull(); // встановлюємо розмір вікна на весь екран
        configureGrid(); // налаштовуємо таблицю
        configureForm(); // налаштовуємо форму

        add(toolbar,getContent());
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
    private void configureGrid() {
        grid.addClassName("manufacturers-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "country");
        grid.setPageSize(10);
        grid.setPaginatorSize(5);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editManufacturer(event.getValue()));

    }

    private void updateList() { // метод для оновлення списку сувенірів
        grid.setItems(service.findAllManufacturers(""));
    }
    private void configureForm() { // метод для налаштування форми
        form = new ManufacturerForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveManufacturer);
        form.addDeleteListener(this::deleteManufacturer);
        form.addCloseListener(e -> closeEditor());
    }
    private void saveManufacturer(ManufacturerForm.SaveEvent event) {  // метод для збереження
        service.saveManufacturer(event.getSouvenir());
        updateList();
        closeEditor();
    }

    private void deleteManufacturer(ManufacturerForm.DeleteEvent event) { // метод для видалення
        service.deleteManufacturer(event.getSouvenir());
        updateList();
        closeEditor();
    }
    public void editManufacturer(Manufacturers manufacturer) { // метод для редагування
        if (manufacturer == null) {
            closeEditor();
        } else {
            form.setManufacturer(manufacturer);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    private void closeEditor() { // метод для закриття редактора
        form.setManufacturer(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private void addManufacturer() { // метод для додавання
        grid.asSingleSelect().clear();
        editManufacturer(new Manufacturers());
    }

}




