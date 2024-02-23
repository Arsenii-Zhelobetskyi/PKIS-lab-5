package com.example.application.views.list;

import com.example.application.data.Souvenirs;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route(value = "")
@PageTitle("Souvenirs")
public class ListView extends VerticalLayout {
    Grid<Souvenirs> grid = new Grid<>(Souvenirs.class);
    TextField filterText = new TextField();
    SouvenirForm form;
    CrmService service;


    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
//        form = new SouvenirForm(Collections.emptyList());
        form = new SouvenirForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveSouvenir);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e -> closeEditor());
    }
    private void saveSouvenir(SouvenirForm.SaveEvent event) {
        service.saveSouvenir(event.getSouvenir());
        updateList();
        closeEditor();
    }

    private void deleteContact(SouvenirForm.DeleteEvent event) {
        service.deleteSouvenir(event.getSouvenir());
        updateList();
        closeEditor();
    }
    private void configureGrid() {
        grid.addClassNames("souvenirs-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name", "manufacturer_s_details", "date", "price");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editSouvenir(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addSouvenir());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public void editSouvenir(Souvenirs souvenir) {
        if (souvenir == null) {
            closeEditor();
        } else {
            form.setSouvenir(souvenir);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    private void closeEditor() {
        form.setSouvenir(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private void addSouvenir() {
        grid.asSingleSelect().clear();
        editSouvenir(new Souvenirs());
    }
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
}