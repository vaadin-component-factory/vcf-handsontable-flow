package com.vaadin.componentfactory.handsontable;

import com.vaadin.flow.router.Route;

@Route("formula")
public class FormulaDemoView extends AbstractDemoView {
    private static final String SAMPLE_JSON_DATA = "[\n" +
            "    {\n" +
            "        \"Amount\": 120000,\n" +
            "        \"TaxRate\": 1.2,\n" +
            "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n" +
            "        \"Tax\": \"=[Payable]-[Amount]\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"Amount\": 50000,\n" +
            "        \"TaxRate\": 24,\n" +
            "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n" +
            "        \"Tax\": \"=[Payable]-[Amount]\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"Amount\": 300000,\n" +
            "        \"TaxRate\": 5,\n" +
            "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n" +
            "        \"Tax\": \"=[Payable]-[Amount]\"\n" +
            "    }\n" +
            "]";

    public FormulaDemoView() {
        Handsontable handsontable = new Handsontable(createJsonArray(SAMPLE_JSON_DATA));
        handsontable.setId("hot1");

        add(handsontable);
    }
}
