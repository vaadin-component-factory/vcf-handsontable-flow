package com.vaadin.flow.component.incubator.handsontable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.SerializableConsumer;

@JavaScript("frontend://handsontable/dist/handsontable.full.js")
@StyleSheet("frontend://handsontable/dist/handsontable.full.css")
@JavaScript("frontend://handsontableConnector.js")
@StyleSheet("frontend://handsontable-extra.css")
public class Handsontable extends Div {
    private Map<UUID, Consumer<JsonArray>> jsonArrayConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<List<String[]>>> listOfStringArrayConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<List<Cell>>> cellListConsumers = new HashMap<>(1);

    public Handsontable() {
        String initFunction = "createHandsontable($0);";
        UI.getCurrent().getPage().executeJavaScript(initFunction, this);
    }

    public Handsontable(JsonArray data) {
        String initFunction = "createHandsontable($0, $1);";
        UI.getCurrent().getPage().executeJavaScript(initFunction, this, data.toString());
    }

    public void setData(JsonArray data) {
        getElement().callFunction("$handsontable.setData", data.toString());
    }

    public void retrieveData(Consumer<JsonArray> callback) {
        UUID uuid = UUID.randomUUID();
        jsonArrayConsumers.put(uuid, callback);
        getElement().callFunction("$handsontable.retrieveData", uuid.toString());
    }

    public void retrieveDataAsArray(Consumer<List<String[]>> callback) {
        UUID uuid = UUID.randomUUID();
        listOfStringArrayConsumers.put(uuid, callback);
        getElement().callFunction("$handsontable.retrieveDataAsArray", uuid.toString());
    }

    public void setCellsMeta(List<Cell> cellsSettings) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String stringValue = mapper.writeValueAsString(cellsSettings);
            getElement().callFunction("$handsontable.setCellsMeta", stringValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void retrieveCellsMeta(Consumer<List<Cell>> callback) {
        UUID uuid = UUID.randomUUID();
        cellListConsumers.put(uuid, callback);
        getElement().callFunction("$handsontable.retrieveCellsMeta", uuid.toString());
    }

    public void setSettings(Settings settings) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, settings);
            String stringValue = writer.toString();
            writer.close();
            getElement().callFunction("$handsontable.setSettings", stringValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void retrieveSettings(Consumer<Settings> callback) {
        throw new UnsupportedOperationException("setSettings isn't been implemented yet!");
    }

    private List<String[]> convertToListOfStringArray(JsonArray jsonArray) {
        List<String[]> list = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            String[] array = new String[jsonArray.getJsonArray(i).size()];
            for (int j = 0; j < array.length; j++)
                array[j] = jsonArray.getJsonArray(i).get(j).toString();
            list.add(array);
        }

        return list;
    }

    @ClientCallable
    private void receiveListOfStringArray(String uuidStr, String data) {
        UUID uuid = UUID.fromString(uuidStr);
        Consumer<List<String[]>> consumer = listOfStringArrayConsumers.remove(uuid);
        Objects.requireNonNull(consumer, "listOfStringArrayConsumer with the given UUID was not found!");

        JsonReader reader = Json.createReader(new StringReader(data));
        JsonArray jsonArray = reader.readArray();

        List<String[]> list = convertToListOfStringArray(jsonArray);
        reader.close();
        consumer.accept(list);
    }

    @ClientCallable
    private void receiveJsonArray(String uuidStr, String data) {
        UUID uuid = UUID.fromString(uuidStr);
        Consumer<JsonArray> consumer = jsonArrayConsumers.remove(uuid);
        Objects.requireNonNull(consumer, "jsonArrayConsumer with the given UUID was not found!");

        JsonReader reader = Json.createReader(new StringReader(data));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        consumer.accept(jsonArray);
    }

    @ClientCallable
    private void receiveCellsMeta(String uuidStr, String cellsMeta) {
        UUID uuid = UUID.fromString(uuidStr);
        Consumer<List<Cell>> consumer = cellListConsumers.remove(uuid);
        Objects.requireNonNull(consumer, "cellListConsumer with the given UUID was not found!");

        JsonReader reader = Json.createReader(new StringReader(cellsMeta));
        JsonArray jsonArray = reader.readArray();

        List<Cell> list = convertToListOfCellsArray(jsonArray);
        reader.close();
        consumer.accept(list);
    }

    private List<Cell> convertToListOfCellsArray(JsonArray jsonArray) {
        List<Cell> list = new ArrayList<>(jsonArray.size());
        try {
            ObjectMapper mapper = new ObjectMapper();
            for (JsonValue jsonValue : jsonArray) {
                Cell cell = mapper.readValue(jsonValue.toString(), Cell.class);
                list.add(cell);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

    public void setNestedHeaders(JsonArray nestedHeaders) {
        getElement().callFunction("$handsontable.setNestedHeaders", nestedHeaders.toString());
    }
}
