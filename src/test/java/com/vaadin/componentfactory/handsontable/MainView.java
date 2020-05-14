package com.vaadin.componentfactory.handsontable;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new RouterLink("Basic usage", BasicDemoView.class));
        add(new RouterLink("Nested headers", NestedHeadersDemoView.class));
        add(new RouterLink("Editor types", EditorTypesDemoView.class));
        add(new RouterLink("Table Type 242", TableType242DemoView.class));
        add(new RouterLink("Table Type 414", TableType414DemoView.class));
        add(new RouterLink("Formatting", FormattingDemoView.class));
        add(new RouterLink("Formula", FormulaDemoView.class));
        add(new RouterLink("Empty Table", EmptyTableView.class));
        add(new RouterLink("Big Table", BigTableDemoView.class));
        add(new RouterLink("Custom Type", CustomTypeView.class));
    }
}
