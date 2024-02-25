package com.example.application.views;

import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * Цей клас відображає статистику того скільки існує виробників та сувенірів
 */
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout {
    private final CrmService service;

    public DashboardView(CrmService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getSouvenirsStats(), getCompaniesChart());
    }

    private Component getSouvenirsStats() {
        Span stats = new Span(service.countSouvenirs() + " souvenirs");
        stats.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.Margin.Top.MEDIUM);
        return stats;
    }

    private Chart getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        // виводимо кількість виробників на графіку
        service.findAllManufacturers("").forEach(manufacturer ->
                dataSeries.add(new DataSeriesItem(manufacturer.getName(), manufacturer.getSouvenirsCount())));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}