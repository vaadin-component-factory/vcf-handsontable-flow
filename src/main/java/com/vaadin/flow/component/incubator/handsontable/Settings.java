package com.vaadin.flow.component.incubator.handsontable;

import java.util.List;

public class Settings {
    private List<Column> columns;
    private List<Cell> cells;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public static class Column {
        private String type;
        private boolean readOnly;
        private String format;
    }

    public static class Cell {
        private int row;
        private int col;
        private String type;
        private boolean readOnly;
        private String format;
        private boolean bold;
        private boolean italic;
        private boolean strikeThrough;
        private boolean underscore;
        private boolean border;
    }
}
