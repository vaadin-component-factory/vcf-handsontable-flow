package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * contains options for one cell that is specified by <code>row</code> and
 * <code>col</code>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cell implements Serializable {
    private int row;
    private int col;

    private String type;
    private boolean readOnly;
    private String dateFormat;
    private NumericFormat numericFormat;
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

    /**
     * @see #setRow(int)
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * zero-based row index
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @see #setCol(int)
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * zero-based col index
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

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

    public boolean isReadOnly() {
        return readOnly;
    }

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

    /**
     * @see #setBorder(boolean)
     * @return
     */
    public boolean isBorder() {
        return border;
    }

    /**
     * If set to true a 1 pixel solid black border is added to the cell.
     * @param border
     */
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

    /**
     *
     * @param correctFormat
     */
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
     * sets the css class name(s) for this cell. For example, to align the cell
     * content horizontally, htLeft, htCenter, htRight and htJustify class names
     * can be used and, for vertical alignment, htTop, htMiddle, htBottom.
     * @param className
     */
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
                ", dateFormat='" + dateFormat + '\'' +
                ", bold=" + bold +
                ", italic=" + italic +
                ", strikethrough=" + strikethrough +
                ", underscore=" + underscore +
                ", border=" + border +
                '}';
    }
}
