package com.vaadin.flow.component.incubator.handsontable;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new RouterLink("Basic usage", BasicDemoView.class));
        add(new RouterLink("Nested headers", NestedHeadersDemoView.class));
        add(new RouterLink("Editor types", EditorTypesDemoView.class));
    }
}
