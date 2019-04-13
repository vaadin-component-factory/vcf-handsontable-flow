package com.vaadin.flow.component.incubator.handsontable;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.Route;

@Route("formatting")
@StyleSheet("frontend://my-styles.css")
public class FormattingDemoView extends AbstractDemoView {
    private static final String SAMPLE_JSON_DATA = "[[1000, 20000, \"05/21/2018\"],[23000, 50000, \"09/13/1978\"]]";

    public FormattingDemoView() {
        Handsontable handsontable = new Handsontable(createJsonArray(SAMPLE_JSON_DATA));
        handsontable.setId("hot1");

        Settings settings = new Settings();
        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setType("numeric");
        column.setNumericFormat(new NumericFormat("0,0.00"));
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        column.setNumericFormat(new NumericFormat("0,0"));
        columns.add(column);

        column = new Column();
        column.setType("date");
        column.setDateFormat("MM/DD/YYYY");
        column.setClassName("yellow-on-blue");
        columns.add(column);
        settings.setColumns(columns);

        handsontable.setSettings(settings);

        add(handsontable);
    }
}
