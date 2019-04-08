package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {
    private List<Column> columns;
    private List<Cell> cell;
    private String licenseKey;

    /*
        'A', 'B', 'C', ...
     */
    private Object rowHeaders = true;
    private Object colHeaders = true;
    private Boolean allowInsertColumn;
    private Boolean allowInsertRow;
    private Boolean allowRemoveColumn;
    private Boolean allowRemoveRow;
    private Boolean autoWrapRow;
    private Object colWidths;
    private Object rowHeights;
    private Boolean correctFormat = false;
    private boolean formulas = true;
    private String height;
    private Object mergeCells;
    private int minCols = 0;
    private int minRows = 0;
    private Boolean readOnly;
    private int minSpareRows = 0;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Cell> getCell() {
        return cell;
    }

    public void setCell(List<Cell> cell) {
        this.cell = cell;
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

    public boolean getFormulas() {
        return formulas;
    }

    public void setFormulas(boolean formulas) {
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

    public int getMinSpareRows() {
        return minSpareRows;
    }

    public void setMinSpareRows(int minSpareRows) {
        this.minSpareRows = minSpareRows;
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
