package com.vaadin.componentfactory.handsontable;

/*
 * #%L
 * Handsontable for Flow
 * %%
 * Copyright (C) 2019 Vaadin
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    private Boolean formulas = true;
    private String height;
    private Object mergeCells;
    private Integer minCols = 0;
    private Integer minRows = 0;
    private Boolean readOnly;
    private Integer minSpareRows = 0;
    private Boolean manualColumnResize;
    private Boolean manualRowResize;
    private String language;

    /**
     * @return
     * @see #setColumns(List)
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * sets list of columns to be shown in the table. Note that if the columns
     * are set by this method no column is automatically generated based on the
     * data.
     *
     * @param columns
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * @return
     * @see #setCell(List)
     */
    public List<Cell> getCell() {
        return cell;
    }

    /**
     * sets options and meta-data for the given cells.
     *
     * @param cell
     */
    public void setCell(List<Cell> cell) {
        this.cell = cell;
    }

    /**
     * @return
     * @see #setLicenseKey(String)
     */
    public String getLicenseKey() {
        return licenseKey;
    }

    /**
     * sets Handsontable's license key.
     *
     * @param licenseKey
     */
    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    /**
     * @return
     * @see #setRowHeaders(Object)
     */
    public Object getRowHeaders() {
        return rowHeaders;
    }

    /**
     * either sets row headers texts or hide row headers.
     *
     * @param rowHeaders can be either a Boolean value to show/hide row headers
     *                   or an array of String to set row headers texts. The
     *                   default headers are numbers starting from one.
     */
    public void setRowHeaders(Object rowHeaders) {
        this.rowHeaders = rowHeaders;
    }

    /**
     * @return
     * @see #setColHeaders(Object)
     */
    public Object getColHeaders() {
        return colHeaders;
    }

    /**
     * @param colHeaders can be either a boolean, for showing or hiding columns
     *                   headers, or an array of String, to show the headers and
     *                   set the columns headers. The default headers texts are
     *                   alphabet characters starting from A.
     */
    public void setColHeaders(Object colHeaders) {
        this.colHeaders = colHeaders;
    }

    /**
     * @return
     * @see #setAllowInsertColumn(Boolean)
     */
    public Boolean getAllowInsertColumn() {
        return allowInsertColumn;
    }

    /**
     * @param allowInsertColumn if true, allows user to add column by choosing
     *                          corresponding commands in context-menu.
     */
    public void setAllowInsertColumn(Boolean allowInsertColumn) {
        this.allowInsertColumn = allowInsertColumn;
    }

    /**
     * @return
     * @see #setAllowInsertRow(Boolean)
     */
    public Boolean getAllowInsertRow() {
        return allowInsertRow;
    }

    /**
     * @param allowInsertRow if true, allows user to add row by choosing
     *                       corresponding commands in context-menu.
     */
    public void setAllowInsertRow(Boolean allowInsertRow) {
        this.allowInsertRow = allowInsertRow;
    }

    /**
     * @return
     * @see #setAllowRemoveColumn(Boolean)
     */
    public Boolean getAllowRemoveColumn() {
        return allowRemoveColumn;
    }

    /**
     * @param allowRemoveColumn if true, allows user to remove column by
     *                          choosing the corresponding command in
     *                          context-menu.
     */
    public void setAllowRemoveColumn(Boolean allowRemoveColumn) {
        this.allowRemoveColumn = allowRemoveColumn;
    }

    /**
     * @return
     * @see #setAllowRemoveRow(Boolean)
     */
    public Boolean getAllowRemoveRow() {
        return allowRemoveRow;
    }

    /**
     * @param allowRemoveRow if true, allows user to remove row by choosing
     *                       the corresponding command in context-menu.
     */
    public void setAllowRemoveRow(Boolean allowRemoveRow) {
        this.allowRemoveRow = allowRemoveRow;
    }

    /**
     * @return
     * @see #setAutoWrapRow(Boolean)
     */
    public Boolean getAutoWrapRow() {
        return autoWrapRow;
    }

    /**
     * @param autoWrapRow if true, pressing TAB or right arrow in the last
     *                    column will move to first column in next row.
     */
    public void setAutoWrapRow(Boolean autoWrapRow) {
        this.autoWrapRow = autoWrapRow;
    }

    /**
     * @return
     * @see #setColWidths(Object)
     */
    public Object getColWidths() {
        return colWidths;
    }

    /**
     * defines column widths in pixels.
     *
     * @param colWidths can be either Integer, String (e.g. "100px") or array of
     *                  Integers.
     */
    public void setColWidths(Object colWidths) {
        this.colWidths = colWidths;
    }

    /**
     * @return
     * @see #setRowHeights(Object)
     */
    public Object getRowHeights() {
        return rowHeights;
    }

    /**
     * defines row heights in pixels.
     *
     * @param rowHeights can be either Integer, String (e.g. "100px") or array of
     *                   Integers.
     */
    public void setRowHeights(Object rowHeights) {
        this.rowHeights = rowHeights;
    }

    /**
     * @see #setCorrectFormat(Boolean)
     * @return
     */
    public Boolean getCorrectFormat() {
        return correctFormat;
    }

    /**
     * @see Cell#setCorrectFormat(boolean)
     * @param correctFormat
     */
    public void setCorrectFormat(Boolean correctFormat) {
        this.correctFormat = correctFormat;
    }

    /**
     * @see #setFormulas(Boolean)
     * @return
     */
    public Boolean isFormulas() {
        return formulas;
    }

    /**
     * enable/disable formula
     * @param formulas
     */
    public void setFormulas(Boolean formulas) {
        this.formulas = formulas;
    }

    /**
     * @see #setHeight(String)
     * @return
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height can be a number or a String (e.g. "100px")
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @see #setMergeCells(Object)
     * @return
     */
    public Object getMergeCells() {
        return mergeCells;
    }

    /**
     *
     * @param mergeCells can be either a Boolean value to enable/disable merging
     *                   cells or a list of <code>MergeCell</code> to merge
     *                   cells programmatically.
     */
    public void setMergeCells(Object mergeCells) {
        this.mergeCells = mergeCells;
    }

    /**
     *
     * @return
     * @see #setMinCols(Integer)
     */
    public Integer getMinCols() {
        return minCols;
    }

    /**
     * sets the minimum number of columns.
     * @param minCols
     */
    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    /**
     * @see #setMinRows(Integer)
     * @return
     */
    public Integer getMinRows() {
        return minRows;
    }

    /**
     * sets the minimum number of rows.
     * @param minRows
     */
    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    /**
     *
     * @return
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     *
     * @param readOnly
     */
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     *
     * @return
     * @see #setMinSpareRows(Integer)
     */
    public Integer getMinSpareRows() {
        return minSpareRows;
    }

    /**
     *
     * @param minSpareRows number of empty rows at the end of the table.
     */
    public void setMinSpareRows(Integer minSpareRows) {
        this.minSpareRows = minSpareRows;
    }

    /**
     * @see #setLanguage(String)
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language e.g. en-US or de-DE
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @see #setManualColumnResize(Boolean)
     * @return
     */
    public Boolean getManualColumnResize() {
        return manualColumnResize;
    }

    /**
     * Turns on/off manual column resize
     * @param manualColumnResize
     */
    public void setManualColumnResize(Boolean manualColumnResize) {
        this.manualColumnResize = manualColumnResize;
    }

    /**
     * @see #setManualRowResize(Boolean)
     * @return
     */
    public Boolean getManualRowResize() {
        return manualRowResize;
    }

    /**
     * Turns on/off manual row resize
     * @param manualRowResize
     */
    public void setManualRowResize(Boolean manualRowResize) {
        this.manualRowResize = manualRowResize;
    }
}
