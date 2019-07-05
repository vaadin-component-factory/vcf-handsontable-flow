package com.vaadin.componentfactory.handsontable;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("414")
public class TableType414DemoView extends AbstractDemoView {
    private static final String HEADERS = "[[{\"label\": \"Stellen für Auszubildende\", \"colspan\": 3}],\n" +
            "[\"Bezeichnung\", \"2018\", \"2017\"]]";
    private static final String SAMPLE_JSON_DATA = "[[\"1. Nach dem Berufsbildungsgesetz\", \"\", \"\"],\n" +
            "[\" a) verwaltungsbezogen\", 0, 0],\n" +
            "[\" b) nicht verwaltungsbezogen\", 2, 2],\n" +
            "[\"2. Praktikantinnen und Praktikanten\", 3, 3],\n" +
            "[\"3. Schülerinnen und Schüler\", \"\", \"\"],\n" +
            "[\" a) mit Entgelt\", 0, 0],\n" +
            "[\" b) ohne Entgelt\", 0, 0],\n" +
            "[\"4. in einem öffentlich-rechtlichen ...\", 0, 0],\n" +
            "[\"Zusammen\", \"=sum(b1:b8)\", \"=sum(c1:c8)\"]]";

    private Handsontable handsontable;

    public TableType414DemoView() {
        add(new H1("Table Type 414"));

        JsonArray data = createJsonArray(SAMPLE_JSON_DATA);
        handsontable = new Handsontable(data);
        handsontable.setId("hot1");
        handsontable.setNestedHeaders(createJsonArray(HEADERS));

        Settings settings = new Settings();

        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setReadOnly(true);
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        settings.setColumns(columns);
        settings.setRowHeaders(false);
        settings.setMinSpareRows(1);
        settings.setColWidths(new int[] {600, 100, 100});
        handsontable.setSettings(settings);
        handsontable.setWidth("100%");

        add(handsontable);
    }
}

