package com.vaadin.flow.component.incubator.handsontable;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("nestedheaders")
public class NestedHeadersDemoView extends AbstractDemoView {
    private static final String SAMPLE_JSON_DATA = "[[\"AT\", 0, 0, 0, 1, \"Landtag\", 1, 1],\n" +
            "[\"Laufbahngruppe 2.2\", 1, 0, 0, 0, \"\", 1, 1],\n" +
            "[\"Laufbahngruppe 2.1\", 1, 0, 0, 0, \"\", 1, 1],\n" +
            "[\"Laufbahngruppe 1.2\", 7, 0, 0, 1, \"Arbeitgeberverband NRW\", 8, 8],\n" +
            "[\"Laufbahngruppe 1.1\", 1, 0, 0, 0, \"\", 1, 1],\n" +
            "[\"Gesamt\", \"=sum(b1:b5)\", \"=sum(c1:c5)\", \"=sum(d1:d5)\", \"=sum(e1:e5)\", \"\", \"=sum(g1:g5)\", \"=sum(h1:h5)\"]]\n";
    private static final String HEADERS = "[[{\"label\": \"Leerstellen für Arbeitnehmerinnen und Arbeitnehmer\", \"colspan\": 8}],\n" +
            "[\"\", {\"label\": \"Beurlaubung wegen § 28 TV-L\", \"colspan\":4}, \"\", {\"label\": \"\", \"colspan\": 2}],\n" +
            "[\"\", \"fam. Gründe,\", \"\", \"\", \"\", \"Erläuterungen\", \"Gesamt\", \"Gesamt\"],\n" +
            "[\"haushaltstechnische\", \"Elternzeit\", \"(Familien-)\", \"arbeitsmarktpol\", \"sonstige\", \"\", \"\", \"\"],\n" +
            "[\"Einreihung\", \"entspr. § 64 LBG\", \"Pflegezeit\", \"Gründe\", \"Gründe\", \"\", \"\", \"\"],\n" +
            "[\"vergleichbar\", \"§ 6 MuSchEltZV\", \"entspr. § 67 LBG\", \"entspr. § 70 LBG\", \"\", \"\", \"2018\", \"2017\"]]\n";

    private Handsontable handsontable;

    public NestedHeadersDemoView() {
        add(new H1("Nested Headers"));

        JsonArray data = createJsonArray(SAMPLE_JSON_DATA);
        handsontable = new Handsontable(data);
        handsontable.setId("hot1");
        handsontable.setNestedHeaders(createJsonArray(HEADERS));
        Settings settings = new Settings();
        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        column = new Column();
        column.setType("numeric");
        columns.add(column);

        settings.setColumns(columns);

        handsontable.setSettings(settings);

        add(handsontable);
    }
}
