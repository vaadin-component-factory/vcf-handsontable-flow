package com.vaadin.componentfactory.handsontable;

import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

@Route("custom-type")
@JavaScript("./handsontable/handsontableConnector.js")
@JavaScript("./password-type.js")
public class CustomTypeView extends AbstractDemoView {

    private static final String[] HEADERS = {"Username", "Password"};
    private static final String SAMPLE_JSON_DATA =
            "[[\"JohnDoe\",\"hunter1\"]," +
            "[\"admin\",\"password\"]," +
            "[\"zZ_pWn3r^_Zz\",\"iliketrains\"]," +
            "[\"user_2323\",\"abc123\"]]]" ;

    public CustomTypeView() {
        add(new H1("Custom Type"));

        JsonArray data = createJsonArray(SAMPLE_JSON_DATA);
        Handsontable handsontable = new Handsontable(data);
        handsontable.setId("hot1");

        Settings settings = new Settings();

        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setReadOnly(true);
        columns.add(column);

        column = new Column();
        column.setType("password");
        columns.add(column);

        settings.setColumns(columns);
        settings.setColHeaders(HEADERS);
        settings.setRowHeaders(false);
        handsontable.setSettings(settings);

        add(handsontable);
    }
}
