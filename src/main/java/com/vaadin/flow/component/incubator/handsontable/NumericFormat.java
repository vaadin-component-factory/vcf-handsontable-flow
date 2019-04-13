package com.vaadin.flow.component.incubator.handsontable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NumericFormat {
    private String pattern;
    private String culture;

    public NumericFormat(String pattern) {
        this.pattern = pattern;
    }

    public NumericFormat(String pattern, String culture) {
        this.pattern = pattern;
        this.culture = culture;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }
}
