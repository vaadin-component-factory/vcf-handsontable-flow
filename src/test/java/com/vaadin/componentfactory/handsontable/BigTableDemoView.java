package com.vaadin.componentfactory.handsontable;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("big")
public class BigTableDemoView extends AbstractDemoView {

    private int rows = 10;

    public BigTableDemoView() {
        VerticalLayout vl = new VerticalLayout();

        add(vl);
        Button btn = new Button("Add new free table (every call doubles the num of rows)");
        btn.addClickListener(
                e -> {
                    vl.add(createTableSimple(7, rows));
                    rows = rows * 2;
                });
        vl.add(btn);
        setSizeFull();
    }

    private Component createTableSimple(int cols, int rows) {

        String grid = "[";

        for (int row = 1; row <= rows; row++) {
            String cells = "[";
            for (int col = 1; col <= cols; col++) {
                cells += String.format("\"\"%s", col < cols ? "," : "");
            }
            cells += "],\n";
            grid += cells;
        }
        grid = grid.substring(0, grid.length() - 2);
        grid += "]";

        Handsontable table = new Handsontable(createJsonArray(grid));

        List<Column> columns = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            Column column = new Column();
            columns.add(column);
        }

        return table;
    }
}
