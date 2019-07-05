package com.vaadin.componentfactory.handsontable;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("242")
public class TableType242DemoView extends AbstractDemoView {
    private static final String HEADERS = "[[{\"label\": \"Erläuterungen zu den ...\", \"colspan\": 4}],\n"+
            "[\"haushaltstechnische\", \"Erläuterungen\", \"Zugang\", \"Abgang\"],\n"+
            "[\"Einreihung\", \"\", \"\", \"\"],\n"+
            "[\"vergleichbar\", \"\", \"\", \"\"]]";
    private static final String SAMPLE_JSON_DATA = "[[\"AT\",\"\", 0, 0],\n" +
            "[\"Laufbahngruppe 2.2\",\"Budgetneutrale Umwandlung in eine Planstelle der Bes. Gr. A 15\", 0, 1],\n" +
            "[\"\",\"\", 0, 0],\n" +
            "[\"Insgesamt\",\"\", \"=sum(c1:c3)\", \"=sum(d1:d3)\"],\n" +
            "[\"Laufbahngruppe 2.1\",\"Budgetneutrale Hebung aus vglb. LG 1.2 ...\", 1, 0],\n" +
            "[\"\",\"\", 0, 0],\n" +
            "[\"Insgesamt\",\"\", \"=sum(c5:c6)\", \"=sum(d5:d6)\"],\n" +
            "[\"Laufbahngruppe 1.2\",\"Budgetneutrale Hebung nach LG 2.1 zur ...\", 0, 1],\n" +
            "[\"\",\"Umsetzung einer Stelle aus Kapitel 12 050 ...\", 1, 0],\n" +
            "[\"Insgesamt\",\"\", \"=sum(c8:c9)\", \"=sum(d8:d9)\"],\n" +
            "[\"Laufbahngruppe 1.1\",\"\", 0, 0],\n" +
            "[\"Zusammen\",\"\", \"=c4+c7+c10\", \"=d4+d7+d10\"]]";

    private Handsontable handsontable;

    public TableType242DemoView() {
        add(new H1("Table Type 242"));

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
        handsontable.setSettings(settings);

        add(handsontable);
    }
}

