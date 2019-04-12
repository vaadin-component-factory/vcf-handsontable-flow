package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Column implements Serializable {
    private String data;
    private String type;
    private boolean readOnly;
    private String dateFormat;
    private NumericFormat numericFormat;
    private boolean correctFormat = false;
    private Object editor;
    private Object source;
    private String className;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public NumericFormat getNumericFormat() {
        return numericFormat;
    }

    public void setNumericFormat(NumericFormat numericFormat) {
        this.numericFormat = numericFormat;
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
