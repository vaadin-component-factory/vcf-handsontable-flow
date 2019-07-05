package com.vaadin.componentfactory.handsontable;

/*
 * #%L
 * Handsontable for Flow
 * %%
 * Copyright (C) 2019 Vaadin
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

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
