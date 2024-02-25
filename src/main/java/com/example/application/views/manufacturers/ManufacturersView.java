package com.example.application.views.manufacturers;

import com.example.application.data.Manufacturers;
import com.example.application.services.CrmService;
import com.example.application.views.MainLayout;
import com.example.application.views.manufacturers.components.ManufacturerForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.klaudeta.PaginatedGrid;

@Route(value = "manufacturers", layout = MainLayout.class)
@PageTitle("Manufacturers")
public class ManufacturersView extends VerticalLayout {
    CrmService service; // сервіс для роботи з базою даних

    PaginatedGrid<Manufacturers, String> grid = new PaginatedGrid<>(Manufacturers.class);
    ManufacturerForm form;

    public  ManufacturersView(CrmService service) { // конструктор класу
        this.service = service;
        addClassName("list-view");
        setSizeFull(); // встановлюємо розмір вікна на весь екран
        configureGrid(); // налаштовуємо таблицю
        configureForm(); // налаштовуємо форму

        add(getContent());
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
//        grid.setItems(service.findAllManufacturers(filterText.getValue()));
        grid.setItems(service.findAllManufacturers(""));
    }
    private void configureForm() { // метод для налаштування форми
        form = new ManufacturerForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveSouvenir);
        form.addDeleteListener(this::deleteSouvenir);
        form.addCloseListener(e -> closeEditor());
    }
    private void saveSouvenir(ManufacturerForm.SaveEvent event) {  // метод для збереження сувеніру
        service.saveManufacturer(event.getSouvenir());
        updateList();
        closeEditor();
    }

    private void deleteSouvenir(ManufacturerForm.DeleteEvent event) { // метод для видалення сувеніру
        service.deleteManufacturer(event.getSouvenir());
        updateList();
        closeEditor();
    }
    public void editManufacturer(Manufacturers manufacturer) { // метод для редагування сувеніру
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


}




