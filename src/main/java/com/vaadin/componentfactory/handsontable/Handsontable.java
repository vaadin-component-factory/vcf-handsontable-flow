package com.vaadin.componentfactory.handsontable;

/*
 * #%L
 * Handsontable for Flow
 * %%
 * Copyright (C) 2019 Vaadin
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

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
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.UsageStatistics;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.pro.licensechecker.LicenseChecker;

@JavaScript("./handsontable/dist/handsontable.full.min.js")
@StyleSheet("context://handsontable.full.min.css")
@JavaScript("./handsontable/dist/languages/all.min.js")
@JavaScript("./handsontable/dist/numbro/languages.min.js")
@JavaScript("./handsontable/handsontableConnector.js")
@StyleSheet("context://handsontable-extra.css")
public class Handsontable extends Div {
    private static final String PROJECT_NAME = "vcf-handsontable-flow";
    private static final String PROJECT_VERSION = Handsontable.class.getPackage().getImplementationVersion();

    private static boolean licenceVerified;

    private Map<UUID, Consumer<JsonArray>> jsonArrayConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<List<String[]>>> listOfStringArrayConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<List<Cell>>> cellListConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<Settings>> settingsConsumers = new HashMap<>(1);
    private Map<UUID, Consumer<String>> stringConsumers = new HashMap<>(1);
    
    private boolean copyable;
    private int columnsLimit;
    private int rowsLimit;

    /**
     * creates an empty Handsontable
     */
    public Handsontable() {
        String language = UI.getCurrent().getLocale().toString().replaceAll("_", "-");
        String initFunction = "createHandsontable($0, $1);";
        UI.getCurrent().getPage().executeJs(initFunction, this, language);
    }

    /**
     * creates a Handsontable and initialize it with the given data.
     *
     * @param data
     */
    public Handsontable(JsonArray data) {
        String language = UI.getCurrent().getLocale().toString().replaceAll("_", "-");
        String initFunction = "createHandsontable($0, $1, $2);";
        UI.getCurrent().getPage().executeJs(initFunction, this, language, data.toString());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        verifyLicense(VaadinSession.getCurrent().getConfiguration().isProductionMode());
    }

    /**
     * @param data
     */
    public void setData(JsonArray data) {
        getElement().callJsFunction("$handsontable.setData", data.toString());
    }

    /**
     * retrieves data with the same structure that passed by
     * <code>setData</code>.
     *
     * @param callback When the data is ready, <code>callback</code> is called
     *                 and the received data is passed to it.
     */
    public void retrieveData(Consumer<JsonArray> callback) {
        UUID uuid = UUID.randomUUID();
        jsonArrayConsumers.put(uuid, callback);
        getElement().callJsFunction("$handsontable.retrieveData", uuid.toString());
    }

    /**
     * retrieves data with in a form of a list of array of String.
     *
     * @param callback When the data is ready, <code>callback</code> is called
     *                 and the received data is passed to it.
     */
    public void retrieveDataAsArray(Consumer<List<String[]>> callback) {
        UUID uuid = UUID.randomUUID();
        listOfStringArrayConsumers.put(uuid, callback);
        getElement().callJsFunction("$handsontable.retrieveDataAsArray", uuid.toString());
    }

    /**
     * sets meta-data for the given cells.
     *
     * @param cells
     */
    public void setCellsMeta(List<Cell> cells) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String stringValue = mapper.writeValueAsString(cells);
            getElement().callJsFunction("$handsontable.setCellsMeta", stringValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * retrieves meta-data of all cells.
     *
     * @param callback When the data is ready, <code>callback</code> is called
     *                 and the received data is passed to it.
     */
    public void retrieveCellsMeta(Consumer<List<Cell>> callback) {
        UUID uuid = UUID.randomUUID();
        cellListConsumers.put(uuid, callback);
        getElement().callJsFunction("$handsontable.retrieveCellsMeta", uuid.toString());
    }

    /**
     * @param settings
     */
    public void setSettings(Settings settings) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, settings);
            String stringValue = writer.toString();
            writer.close();
            getElement().callJsFunction("$handsontable.setSettings", stringValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param callback When the settings is ready, <code>callback</code> is
     *                 called and the received settings is passed to it.
     */
    public void retrieveSettings(Consumer<Settings> callback) {
        UUID uuid = UUID.randomUUID();
        settingsConsumers.put(uuid, callback);
        getElement().callJsFunction("$handsontable.retrieveSettings", uuid.toString());
    }

    @ClientCallable
    private void receiveSettings(String uuidStr, String settingsStr) {
        try {
            UUID uuid = UUID.fromString(uuidStr);
            Consumer<Settings> consumer = settingsConsumers.remove(uuid);
            Objects.requireNonNull(consumer, "settingsConsumer with the given UUID was not found!");
            ObjectMapper mapper = new ObjectMapper();
            Settings settings = mapper.readValue(settingsStr, Settings.class);
            consumer.accept(settings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * sets headers of the table. It is used when the table has multiple header
     * rows or merged cells in the header.
     *
     * @param nestedHeaders
     * @see <a href="https://handsontable.com/docs/7.0.2/demo-nested-headers.html">
     * Nested headers document on Handsontable website</a>
     */
    public void setNestedHeaders(JsonArray nestedHeaders) {
        getElement().callJsFunction("$handsontable.setNestedHeaders", nestedHeaders.toString());
    }

    /**
     * inserts empty columns at the given index.
     *
     * @param index  starting index where new columns should be inserted
     * @param amount the number of columns
     */
    public void insertCol(int index, int amount) {
        getElement().callJsFunction("$handsontable.alter", "insert_col", index, amount);
    }

    /**
     * inserts empty rows at the given index.
     *
     * @param index  starting index where new rows should be inserted
     * @param amount the number of rows
     */
    public void insertRow(int index, int amount) {
        getElement().callJsFunction("$handsontable.alter", "insert_row", index, amount);
    }

    /**
     * removes columns at the given index.
     *
     * @param index  starting index where columns should be removed
     * @param amount the number of columns to be removed
     */
    public void removeCol(int index, int amount) {
        getElement().callJsFunction("$handsontable.alter", "remove_col", index, amount);
    }

    /**
     * removes rows at the given index.
     *
     * @param index  starting index where rows should be removed
     * @param amount the number of rows to be removed
     */
    public void removeRow(int index, int amount) {
        getElement().callJsFunction("$handsontable.alter", "remove_row", index, amount);
    }
    
    /**
     * Returns whether the table's content can be copied.
     *
     */
    public boolean isCopyEnabled() {
        return copyable;
    }
    
    /**
     * Sets whether the table's content can be copied
     *
     * @param copyable
     */
    public void setCopyEnabled(boolean copyable) {
        this.copyable = copyable;
        getElement().callJsFunction("$handsontable.setCopyEnabled", copyable);
    }

    /**
     * Returns the maximum numbers of columns that can be copied.
     *
     */
    public int getCopyColumnsLimit() {
        return columnsLimit;
    }

    /**
     * Sets the maximum numbers of columns that can be copied.
     *
     * Note, will re-enable copy even if it was disabled using
     * {@link Handsontable#setCopyEnabled(false)}.
     *
     * @param columnsLimit
     */
    public void setCopyColumnsLimit(int columnsLimit) {
        if (columnsLimit < 1) {
            throw new IllegalArgumentException(
                    "Input must be a positive integer");
        }
        copyable = true;
        this.columnsLimit = columnsLimit;
        getElement().callJsFunction("$handsontable.setCopyColumnsLimit",
                columnsLimit);
    }
       
    /**
     * Returns the maximum numbers of rows that can be copied.
     *
     */
    public int getCopyRowsLimit() {
        return rowsLimit;
    }

    /**
     * Sets the maximum numbers of rows that can be copied.
     *
     * Note, will re-enable copy even if it was disabled using
     * {@link Handsontable#setCopyEnabled(false)}.
     *
     * @param rowsLimit
     */
    public void setCopyRowsLimit(int rowsLimit) {
        if (rowsLimit < 1) {
            throw new IllegalArgumentException(
                    "Input must be a positive integer");
        }
        copyable = true;
        this.rowsLimit = rowsLimit;
        getElement().callJsFunction("$handsontable.setCopyRowsLimit",
                rowsLimit);
    }

    /**
     *
     * @param row
     * @param col
     * @param value
     */
    public void setDataAtCell(int row, int col, String value) {
        getElement().callJsFunction("$handsontable.setDataAtCell", row, col, value);
    }

    /**
     *
     * @param row
     * @param col
     * @param callback
     */
    public void retrieveDataAtCell(int row, int col, Consumer<String> callback) {
        UUID uuid = UUID.randomUUID();
        stringConsumers.put(uuid, callback);
        getElement().callJsFunction("$handsontable.retrieveDataAtCell", row, col, uuid.toString());
    }

    @ClientCallable
    private void receiveString(String uuidStr, String data) {
        UUID uuid = UUID.fromString(uuidStr);
        Consumer<String> consumer = stringConsumers.remove(uuid);
        Objects.requireNonNull(consumer, "stringConsumer with the given UUID was not found!");
        consumer.accept(data);
    }

    /**
     *
     * @param classNames
     */
    public void setHeaderClassNames(String[] classNames) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String stringValue = mapper.writeValueAsString(classNames);
            getElement().callJsFunction("$handsontable.setHeaderClassNames", stringValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void verifyLicense(boolean productionMode) {
        if (!productionMode && !licenceVerified) {
            LicenseChecker.checkLicense(PROJECT_NAME, PROJECT_VERSION);
            UsageStatistics.markAsUsed(PROJECT_NAME, PROJECT_VERSION);
            licenceVerified = true;
        }
    }
}
