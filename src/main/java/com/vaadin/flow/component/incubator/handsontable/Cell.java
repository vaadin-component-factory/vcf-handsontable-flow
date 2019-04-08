package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cell implements Serializable {
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
    private String className;

    public Cell() {
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

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
