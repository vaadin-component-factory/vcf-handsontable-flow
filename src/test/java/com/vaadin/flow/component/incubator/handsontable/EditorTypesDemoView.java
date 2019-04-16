package com.vaadin.flow.component.incubator.handsontable;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("editor-types")
public class EditorTypesDemoView extends AbstractDemoView {
    private static final String SAMPLE_JSON_DATA = "[[0, 0, \"Titel\", \"Zu titel 428 0 1:\", 0, true, false, false, false, \"\"],\n" +
            "[8, 241, \"Personaltabelle\", \"Stellen ...\", 0, true, false, true, false, \"\"],\n" +
            "[2, 0, \"Text\", \"Die AT-Stellen ...\", 0, true, false, true, false, \"\"],\n" +
            "[9, 242, \"Personaltabelle\", \"Erläuterungen zu ...\", 0, true, false, true, false, \"\"],\n" +
            "[4, 248, \"Personaltabelle\", \"Leerstellen für ...\", 0, true, false, false, false, \"\"],\n" +
            "[10, 414, \"Personaltabelle\", \"Azubi\", 0, true, false, false, false, \"\"],\n" +
            "[11, 0, \"Text\", \"Die Stellen ...\", 0, true, false, false, false, \"\"]]";
    private static final String[] COL_HEADERS = {"Fix", "Tab", "Typ", "Beschreibung", "Gr", "D", "Ü", "Ä", "P", "Be..."};

    private Handsontable handsontable;

    public EditorTypesDemoView() {
        add(new H1("Editor types"));

        JsonArray data = createJsonArray(SAMPLE_JSON_DATA);
        handsontable = new Handsontable(data);
        handsontable.setId("hot1");

        Settings settings = new Settings();
        settings.setColHeaders(COL_HEADERS);
        List<Cell> cells = new ArrayList<>();
        Cell cell;

        for (int i = 0; i < 9; i++) {
            cell = new Cell(0, i);
            cell.setBold(true);
            cells.add(cell);
        }

        List<Column> columns = new ArrayList<>();
        Column column;

        column = new Column();
        column.setType("numeric");
        columns.add(column);


        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("text");
        column.setEditor("dropdown");
        column.setSource(new String[] {"Titel", "Personaltabelle", "Text"});
        columns.add(column);

        column = new Column();
        column.setType("text");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("checkbox");
        columns.add(column);

        column = new Column();
        column.setType("checkbox");
        columns.add(column);

        column = new Column();
        column.setType("checkbox");
        column.setReadOnly(true);
        columns.add(column);

        column = new Column();
        column.setType("checkbox");
        columns.add(column);

        column = new Column();
        column.setType("text");
        columns.add(column);

        settings.setCell(cells);
        settings.setColumns(columns);
        settings.setRowHeaders(false);
        handsontable.setSettings(settings);
        add(handsontable);
    }
}
