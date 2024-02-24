package com.example.application.views;

import com.example.application.views.manufacturers.ManufacturersView;
import com.example.application.views.souvenirs.SouvenirsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout { // AppLayout це макет Vaadin із заголовком і адаптивною висувною панеллю.

    public MainLayout() {
        createHeader(); // викликаємо метод для створення заголовку
        createDrawer(); // викликаємо метод для створення бокового меню
    }

    private void createHeader() {
        H1 logo = new H1("Souvenirs App");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE, // Замість стилізації тексту за допомогою необробленого CSS юзаемо класи Lumo Utility, які постачаються разом із темою за замовчуванням.
                LumoUtility.Margin.MEDIUM);

        var header = new HorizontalLayout(new DrawerToggle(), logo ); // DrawerToggle — це кнопка меню, яка перемикає видимість бічної панелі

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); // встановлюємо вертикальне вирівнювання елементів
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("List", SouvenirsView.class)//Створює RouterLink із текстом "List" і ListView.class як ціль посилання
                , new RouterLink("Manufacturers", ManufacturersView.class),

        new RouterLink("Dashboard", DashboardView.class)

        ));
    }
}