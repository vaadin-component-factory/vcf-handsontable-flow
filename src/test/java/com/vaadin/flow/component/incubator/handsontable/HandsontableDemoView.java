package com.vaadin.flow.component.incubator.handsontable;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("")
public class HandsontableDemoView extends VerticalLayout {
    private static final String SAMPLE_JSON_DATA =
            " [ \n" +
                    "{\n" +
                    "   \"name\": \"Anne\", \n" +
                    "   \"age\" : 13, \n" +
                    "   \"isMarried\" : false, \n" +
                    "   \"address\": { \n" +
                    "     \"street\": \"#1234, Main Street\", \n" +
                    "     \"zipCode\": \"123456\" \n" +
                    "   }, \n" +
                    "   \"phoneNumber\": \"011-111-1111\"\n" +
                    " }, \n" +
                    "{\n" +
                    "   \"name\": \"John\", \n" +
                    "   \"age\" : 34, \n" +
                    "   \"isMarried\" : false, \n" +
                    "   \"addr ess\": { \n" +
                    "     \"street\": \"#1234, Kurala Street\", \n" +
                    "     \"zipCode\": \"123456\" \n" +
                    "   }, \n" +
                    "   \"phoneNumber\": \"011-222-1111\"\n" +
                    " },\n" +
                    "{\n" +
                    "   \"name\": \"Peter\", \n" +
                    "   \"age\" : 40, \n" +
                    "   \"isMarried\" : false, \n" +
                    "   \"address\": { \n" +
                    "     \"street\": \"#1234, Tor Street\", \n" +
                    "     \"zipCode\": \"123456\" \n" +
                    "   }, \n" +
                    "   \"phoneNumber\": \"011-222-1111\"\n" +
                    " },\n" +
                    "{\n" +
                    "   \"name\": \"Kate\", \n" +
                    "   \"age\" : 45, \n" +
                    "   \"isMarried\" : true, \n" +
                    "   \"address\": { \n" +
                    "     \"street\": \"#1234, Varange Street\", \n" +
                    "     \"zipCode\": \"123456\" \n" +
                    "   }, \n" +
                    "   \"phoneNumber\": \"011-333-1111\"\n" +
                    " }\n" +
                    "]";

    private TextArea textArea;
    private Handsontable handsontable;

    public HandsontableDemoView() {
        textArea = new TextArea("data");
        textArea.setValue(SAMPLE_JSON_DATA);
        textArea.setHeight("500px");
        textArea.setWidthFull();

        JsonArray data = createJsonObject();
        handsontable = new Handsontable(data);
        handsontable.setId("hot1");

        Button setDataButton = new Button("Set data", event -> {
            handsontable.setData(createJsonObject());
        });

        Button retrieveDataButton = new Button("Retrieve data", event -> {
            handsontable.retrieveData(jsonValues -> textArea.setValue(jsonValues.toString()));
        });

        Button retrieveDataAsArrayButton = new Button("Retrieve data as array", event -> {
            handsontable.retrieveDataAsArray(list -> textArea.setValue(list.stream().map(Arrays::toString).collect(Collectors.toList()).toString()));
        });

        Button setCellsMetaButton = new Button("Set cells meta", event -> {
            List<Settings.Cell> cells = new ArrayList<>();
            Settings.Cell cell;
            cell = new Settings.Cell();
            cell.setRow(1);
            cell.setCol(1);
            cell.setBold(true);
            cells.add(cell);

            cell = new Settings.Cell();
            cell.setRow(2);
            cell.setCol(1);
            cell.setStrikethrough(true);
            cells.add(cell);

            cell = new Settings.Cell();
            cell.setRow(1);
            cell.setCol(2);
            cell.setBorder(true);
            cells.add(cell);

            handsontable.setCellsMeta(cells);
        });

        Button retrieveCellsMetaButton = new Button("Retrieve Cells Meta", event -> {
            handsontable.retrieveCellsMeta(list -> textArea.setValue(list.toString()));
        });

        HorizontalLayout buttons = new HorizontalLayout(setDataButton, retrieveDataButton, retrieveDataAsArrayButton, setCellsMetaButton, retrieveCellsMetaButton);
        add(handsontable, textArea, buttons);
    }

    private JsonArray createJsonObject() {
        JsonReader reader = Json.createReader(new StringReader(textArea.getValue()));
        JsonArray jsonArray = reader.readArray();
        reader.close();
        return jsonArray;
    }
}
