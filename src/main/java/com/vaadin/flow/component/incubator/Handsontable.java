package com.vaadin.flow.component.incubator;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;

@JavaScript("frontend://handsontable/dist/handsontable.full.js")
@StyleSheet("frontend://handsontable/dist/handsontable.full.css")
@JavaScript("frontend://handsontableConnector.js")
@StyleSheet("frontend://handsontable-extra.css")
public class Handsontable extends Div {
    public Handsontable() {
        String initFunction = "createHandsontbale($0);";
        UI.getCurrent().getPage().executeJavaScript(initFunction, this);
    }

    public void addRow(int index) {

    }

    public void addRow(int index, int amount) {

    }

    public void addColumn(int index) {

    }

    public void addColumn(int index, int amount) {

    }
}
