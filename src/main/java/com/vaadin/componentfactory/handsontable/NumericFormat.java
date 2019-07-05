package com.vaadin.componentfactory.handsontable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Display format for numeric typed renderers
 * @see <a href="https://handsontable.com/docs/7.0.2/Options.html#numericFormat"></a>
 */
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

    /**
     * @see #setCulture(String)
     * @return
     */
    public String getCulture() {
        return culture;
    }

    /**
     *
     * @param culture e.g. en-US or de-DE
     */
    public void setCulture(String culture) {
        this.culture = culture;
    }
}
