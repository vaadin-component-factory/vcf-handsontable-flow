package com.vaadin.componentfactory.handsontable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractDemoView extends VerticalLayout {
    protected JsonArray createJsonArray(String str) {
        JsonReader reader = Json.createReader(new StringReader(str));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        return jsonArray;
    }
}
