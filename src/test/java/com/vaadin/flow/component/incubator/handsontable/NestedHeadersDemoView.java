package com.vaadin.flow.component.incubator.handsontable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

import java.io.StringReader;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("nestedheaders")
public class NestedHeadersDemoView extends VerticalLayout {
    private static final String SAMPLE_JSON_DATA = "[[\"AT\", 0, 0, 0, 1, \"Landtag\", 1, 1],\n" +
            "[\"Laufbahngruppe 2.2\", 1, 0, 0, 0, \"\", 1, 1],\n" +
            "[\"Laufbahngruppe 2.1\", 1, 0, 0, 0, \"\", 1, 1],\n" +
            "[\"Laufbahngruppe 1.2\", 7, 0, 0, 1, \"Arbeitgeberverband NRW\", 8, 8],\n" +
            "[\"Laufbahngruppe 1.1\", 1, 0, 0, 0, \"\", 1, 1]]\n";
    private static final String HEADERS = "[[{\"label\": \"Leerstellen für Arbeitnehmerinnen und Arbeitnehmer\", \"colspan\": 8}],\n" +
            "[\"\", {\"label\": \"Beurlaubung wegen § 28 TV-L\", \"colspan\":4}, \"\", {\"label\": \"\", \"colspan\": 2}],\n" +
            "[\"\", \"fam. Gründe,\", \"\", \"\", \"\", \"Erläuterungen\", \"Gesamt\", \"Gesamt\"],\n" +
            "[\"haushaltstechnische\", \"Elternzeit\", \"(Familien-)\", \"arbeitsmarktpol\", \"sonstige\", \"\", \"\", \"\"],\n" +
            "[\"Einreihung\", \"entspr. § 64 LBG\", \"Pflegezeit\", \"Gründe\", \"Gründe\", \"\", \"\", \"\"],\n" +
            "[\"vergleichbar\", \"§ 6 MuSchEltZV\", \"entspr. § 67 LBG\", \"entspr. § 70 LBG\", \"\", \"\", \"2018\", \"2017\"]]\n";

    private Handsontable handsontable;

    public NestedHeadersDemoView() {
        JsonArray data = createJsonArray(SAMPLE_JSON_DATA);
        handsontable = new Handsontable(data);
        handsontable.setNestedHeaders(createJsonArray(HEADERS));
        add(handsontable);
    }

    private JsonArray createJsonArray(String str) {
        JsonReader reader = Json.createReader(new StringReader(str));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        return jsonArray;
    }
}
