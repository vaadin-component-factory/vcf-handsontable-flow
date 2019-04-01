package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Settings implements Serializable {
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

    public static class Column implements Serializable{
        private String type;
        private boolean readOnly;
        private String format;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cell implements Serializable {
        private int row;
        private int col;
        private String type;
        private boolean readOnly;
        private String format;
        private boolean bold;
        private boolean italic;
        private boolean strikethrough;
        private boolean underscore;
        private boolean border;
        private String name;

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isReadOnly() {
            return readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public boolean isStrikethrough() {
            return strikethrough;
        }

        public void setStrikethrough(boolean strikethrough) {
            this.strikethrough = strikethrough;
        }

        public boolean isUnderscore() {
            return underscore;
        }

        public void setUnderscore(boolean underscore) {
            this.underscore = underscore;
        }

        public boolean isBorder() {
            return border;
        }

        public void setBorder(boolean border) {
            this.border = border;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    ", type='" + type + '\'' +
                    ", readOnly=" + readOnly +
                    ", format='" + format + '\'' +
                    ", bold=" + bold +
                    ", italic=" + italic +
                    ", strikethrough=" + strikethrough +
                    ", underscore=" + underscore +
                    ", border=" + border +
                    '}';
        }
    }
}
