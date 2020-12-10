package com.vaadin.componentfactory.handsontable;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route("copy-rows")
public class CopyRowsLimitView extends VerticalLayout {

	Button toggleCopy = null;
	
    public CopyRowsLimitView() {
        Handsontable table = createTableSimple(7, 1500);

        NumberField copyRowsLimit = new NumberField("Set rows limit");
        copyRowsLimit.addValueChangeListener(
                e -> table.setCopyRowsLimit(e.getValue().intValue()));

        NumberField copyColumnsLimit = new NumberField("Set columns limit");
        copyColumnsLimit.addValueChangeListener(
                e -> table.setCopyColumnsLimit(e.getValue().intValue()));

        toggleCopy = new Button("Toggle copy-ablity", e -> { 
            table.setCopyEnabled(!table.isCopyEnabled());
            if (table.isCopyEnabled()) {
               toggleCopy.setIcon(VaadinIcon.COPY.create());
            } else {
                toggleCopy.setIcon(null);
            }
        });
        toggleCopy.setIcon(VaadinIcon.COPY.create());

        add(new HorizontalLayout(copyRowsLimit, copyColumnsLimit), toggleCopy,
                table);
    }

    private Handsontable createTableSimple(int cols, int rows) {

        String grid = "[";

        for (int row = 1; row <= rows; row++) {
            String cells = "[";
            for (int col = 1; col <= cols; col++) {
                cells += String.format("\"%d\"%s", row, col < cols ? "," : "");
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

    private JsonArray createJsonArray(String str) {
        JsonReader reader = Json.createReader(new StringReader(str));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        return jsonArray;
    }
}
