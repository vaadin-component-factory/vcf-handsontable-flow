package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Settings implements Serializable {
    private List<Column> columns;
    private List<Cell> cells;
    private String licenseKey;

    /*
        'A', 'B', 'C', ...
     */
    private Object rowHeaders;
    private Object colHeaders;
    private Boolean allowInsertColumn;
    private Boolean allowInsertRow;
    private Boolean allowRemoveColumn;
    private Boolean allowRemoveRow;
    private Boolean autoWrapRow;
    private Object colWidths;
    private Object rowHeights;
    private Boolean correctFormat = false;
    private Boolean formulas;
    private String height;
    private Object mergeCells;
    private int minCols = 0;
    private int minRows = 0;
    private Boolean readOnly;

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

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public Object isRowHeaders() {
        return rowHeaders;
    }

    public void setRowHeaders(Boolean rowHeaders) {
        this.rowHeaders = rowHeaders;
    }

    public Object getRowHeaders() {
        return rowHeaders;
    }

    public Object getColHeaders() {
        return colHeaders;
    }

    public void setColHeaders(Object colHeaders) {
        this.colHeaders = colHeaders;
    }

    public Boolean getAllowInsertColumn() {
        return allowInsertColumn;
    }

    public void setAllowInsertColumn(Boolean allowInsertColumn) {
        this.allowInsertColumn = allowInsertColumn;
    }

    public Boolean getAllowInsertRow() {
        return allowInsertRow;
    }

    public void setAllowInsertRow(Boolean allowInsertRow) {
        this.allowInsertRow = allowInsertRow;
    }

    public Boolean getAllowRemoveColumn() {
        return allowRemoveColumn;
    }

    public void setAllowRemoveColumn(Boolean allowRemoveColumn) {
        this.allowRemoveColumn = allowRemoveColumn;
    }

    public Boolean getAllowRemoveRow() {
        return allowRemoveRow;
    }

    public void setAllowRemoveRow(Boolean allowRemoveRow) {
        this.allowRemoveRow = allowRemoveRow;
    }

    public Boolean getAutoWrapRow() {
        return autoWrapRow;
    }

    public void setAutoWrapRow(Boolean autoWrapRow) {
        this.autoWrapRow = autoWrapRow;
    }

    public void setRowHeaders(Object rowHeaders) {
        this.rowHeaders = rowHeaders;
    }

    public Object getColWidths() {
        return colWidths;
    }

    public void setColWidths(Object colWidths) {
        this.colWidths = colWidths;
    }

    public Object getRowHeights() {
        return rowHeights;
    }

    public void setRowHeights(Object rowHeights) {
        this.rowHeights = rowHeights;
    }

    public Boolean getCorrectFormat() {
        return correctFormat;
    }

    public void setCorrectFormat(Boolean correctFormat) {
        this.correctFormat = correctFormat;
    }

    public Boolean getFormulas() {
        return formulas;
    }

    public void setFormulas(Boolean formulas) {
        this.formulas = formulas;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Object getMergeCells() {
        return mergeCells;
    }

    public void setMergeCells(Object mergeCells) {
        this.mergeCells = mergeCells;
    }

    public int getMinCols() {
        return minCols;
    }

    public void setMinCols(int minCols) {
        this.minCols = minCols;
    }

    public int getMinRows() {
        return minRows;
    }

    public void setMinRows(int minRows) {
        this.minRows = minRows;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public static class Column implements Serializable{
        private String type;
        private boolean readOnly;
        private String format;
        private boolean correctFormat = false;
        private Object editor;
        private Object source;

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

        public boolean isCorrectFormat() {
            return correctFormat;
        }

        public void setCorrectFormat(boolean correctFormat) {
            this.correctFormat = correctFormat;
        }

        public Object getEditor() {
            return editor;
        }

        public void setEditor(Object editor) {
            this.editor = editor;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }
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
        private boolean correctFormat = false;
        private Object editor;
        private Object source;

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

    public static class MergeCell implements Serializable {
        private int row;
        private int col;
        private int rowspan;
        private int colspan;

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

        public int getRowspan() {
            return rowspan;
        }

        public void setRowspan(int rowspan) {
            this.rowspan = rowspan;
        }

        public int getColspan() {
            return colspan;
        }

        public void setColspan(int colspan) {
            this.colspan = colspan;
        }
    }
}
