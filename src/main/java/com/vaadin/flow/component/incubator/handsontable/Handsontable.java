package com.vaadin.flow.component.incubator.handsontable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;

@JavaScript("frontend://handsontable/dist/handsontable.full.js")
@StyleSheet("frontend://handsontable/dist/handsontable.full.css")
@JavaScript("frontend://handsontableConnector.js")
@StyleSheet("frontend://handsontable-extra.css")
public class Handsontable extends Div {
    private Map<UUID, Consumer<JsonArray>> retrieveDataCallbacks = new HashMap<>(1);

    public Handsontable() {
        String initFunction = "createHandsontbale($0);";
        UI.getCurrent().getPage().executeJavaScript(initFunction, this);
    }

    public Handsontable(JsonArray data) {
        String initFunction = "createHandsontbale($0, $1);";
        UI.getCurrent().getPage().executeJavaScript(initFunction, this, data.toString());
    }

    public void setData(JsonArray data) {
        getElement().callFunction("$handsontable.setData", data.toString());
    }

    public void retrieveData(Consumer<JsonArray> callback) {
        UUID uuid = UUID.randomUUID();
        retrieveDataCallbacks.put(uuid, callback);
        getElement().callFunction("$handsontable.retrieveData", uuid.toString());
    }

    public void setSettings(Settings settings) {

    }

    public void retrieveSettings(Consumer<Settings> callback) {

    }

    @ClientCallable
    private void responseRetrieveData(String uuidStr, String data) {
        UUID uuid = UUID.fromString(uuidStr);
        Consumer<JsonArray> consumer = retrieveDataCallbacks.get(uuid);
        JsonReader reader = Json.createReader(new StringReader(data));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        consumer.accept(jsonArray);
    }
}
