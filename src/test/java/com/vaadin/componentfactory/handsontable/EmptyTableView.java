package com.vaadin.componentfactory.handsontable;

import com.vaadin.flow.router.Route;

@Route("empty")
public class EmptyTableView  extends AbstractDemoView{
    public EmptyTableView() {
        Handsontable handsontable = new Handsontable();
        handsontable.setId("hot1");

        add(handsontable);
    }
}
