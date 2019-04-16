package com.vaadin.flow.component.incubator.handsontable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @see Settings#setMergeCells(Object)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeCell implements Serializable {
    private Integer row;
    private Integer col;
    private Integer rowspan;
    private Integer colspan;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }
}
