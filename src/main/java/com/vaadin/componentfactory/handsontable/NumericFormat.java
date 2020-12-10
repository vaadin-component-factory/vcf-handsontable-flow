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
