package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * contains options for a column
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Column implements Serializable {
    private String type;
    private boolean readOnly;
    private String dateFormat;
    private NumericFormat numericFormat;
    private boolean correctFormat = false;
    private Object editor;
    private Object source;
    private String className;

    /**
     * @see #setType(String)
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type possible values:
     *             <ul><li>autocomplete</li>
     *             <li>checkbox</li>
     *             <li>date</li>
     *             <li>dropdown</li>
     *             <li>handsontable</li>
     *             <li>numeric</li>
     *             <li>password</li>
     *             <li>text</li>
     *             <li>time</li></ul>
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     *
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @see #setDateFormat(String)
     * @return
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * e.g. DD/MM/YYYY. It applies only to the cells with date type.
     * @param dateFormat
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @see #setNumericFormat(NumericFormat)
     * @return
     */
    public NumericFormat getNumericFormat() {
        return numericFormat;
    }

    /**
     * It applies only to the cells with numeric data type.
     * @param numericFormat
     */
    public void setNumericFormat(NumericFormat numericFormat) {
        this.numericFormat = numericFormat;
    }

    /**
     * @see #setCorrectFormat(boolean)
     * @return
     */
    public boolean isCorrectFormat() {
        return correctFormat;
    }

    /**
     *
     * @param correctFormat If true then dates will be automatically formatted
     *                      to match the desired format.
     *
     * Note, this option only works for date-typed cells.
     */
    public void setCorrectFormat(boolean correctFormat) {
        this.correctFormat = correctFormat;
    }

    /**
     * @see #setEditor(Object)
     * @return
     */
    public Object getEditor() {
        return editor;
    }

    /**
     * defines the editor for the cell. Its value can be one of the followings
     * strings. <ul>
     * <li>autocomplete</li>
     * <li>checkbox</li>
     * <li>date</li>
     * <li>dropdown</li>
     * <li>handsontable</li>
     * <li>mobile</li>
     * <li>password</li>
     * <li>select</li>
     * <li>text</li>
     * </ul>
     * It can also be used to disable the cell editing by setting its value to
     * <code>false</code>.
     * @param editor
     */
    public void setEditor(Object editor) {
        this.editor = editor;
    }

    public Object getSource() {
        return source;
    }

    /**
     * @param source Defines data source for Autocomplete or Dropdown cell
     *               types. It can be an array of String.
     */
    public void setSource(Object source) {
        this.source = source;
    }

    /**
     * @see #setClassName(String)
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * sets the css class name(s) for this column. For example, to align the
     * column content horizontally, htLeft, htCenter, htRight and htJustify
     * class names can be used and, for vertical alignment, htTop, htMiddle and
     * htBottom.
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }
}
