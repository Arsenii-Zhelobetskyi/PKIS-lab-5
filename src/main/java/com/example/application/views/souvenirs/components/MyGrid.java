package com.example.application.views.souvenirs.components;

import com.example.application.data.Souvenirs;
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.List;
import java.util.function.Consumer;

public class MyGrid<T> extends PaginatedGrid<T,String> {

    PaginatedGrid grid ;
    public MyGrid(Class<T> entityType,Consumer<T> edit, List<String> columns){
        grid = new PaginatedGrid<>(Souvenirs.class);
        grid.addClassNames("souvenirs-grid");
        grid.setSizeFull();

        columns.forEach(col -> grid.addColumn(col));
//        grid.setColumns("id", "name", "manufacturer_s_details", "date", "price");
        grid.setPageSize(10);
        grid.setPaginatorSize(5);
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                edit.accept((T) event.getValue()));
    }
    public PaginatedGrid getGrid(){
        return grid;
    }
}
