package com.example.application.views;

import com.example.application.data.Manufacturers;
import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "manufacturers", layout = MainLayout.class)
@PageTitle("Manufacturers")
public class ManufacturersView extends VerticalLayout {
    CrmService service; // сервіс для роботи з базою даних

    Grid<Manufacturers> grid = new Grid<>(Manufacturers.class); // таблиця для відображення сувенірів

    public  ManufacturersView(CrmService service) { // конструктор класу
        this.service = service;
        addClassName("list-view");
        setSizeFull(); // встановлюємо розмір вікна на весь екран
        configureGrid(); // налаштовуємо таблицю
        add(grid);
        updateList(); // оновлюємо список сувенірів
    }

    private void configureGrid() {
        grid.addClassName("manufacturers-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "country");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() { // метод для оновлення списку сувенірів
//        grid.setItems(service.findAllManufacturers(filterText.getValue()));
        grid.setItems(service.findAllManufacturers(""));
    }

}




