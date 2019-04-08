package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

public class Column implements Serializable {
    private String type;
    private boolean readOnly;
    private String format;
    private boolean correctFormat = false;
    private Object editor;
    private Object source;
    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
