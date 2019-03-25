package com.vaadin.flow.component.incubator;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class HandsontableDemoView extends Div {
    public HandsontableDemoView() {
        Handsontable handsontable = new Handsontable();
        add(handsontable);
    }
}
