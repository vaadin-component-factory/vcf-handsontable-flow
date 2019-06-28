package com.vaadin.flow.component.incubator.handsontable;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.router.Route;

@Route("formula")
public class FormulaDemoView extends AbstractDemoView {
  private static final String SAMPLE_JSON_DATA =
      "[\n"
          + "    {\n"
          + "        \"Amount\": 120000,\n"
          + "        \"TaxRate\": 1.2,\n"
          + "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n"
          + "        \"Tax\": \"=[Payable]-[Amount]\"\n"
          + "    },\n"
          + "    {\n"
          + "        \"Amount\": 50000,\n"
          + "        \"TaxRate\": 24,\n"
          + "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n"
          + "        \"Tax\": \"=[Payable]-[Amount]\"\n"
          + "    },\n"
          + "    {\n"
          + "        \"Amount\": 300000,\n"
          + "        \"TaxRate\": 5,\n"
          + "        \"Payable\": \"=[Amount]+[Amount]*[TaxRate]/100\",\n"
          + "        \"Tax\": \"=[Payable]-[Amount]\"\n"
          + "    }\n"
          + "]";

  private static final String[] COL_HEADERS = {"Amount", "TaxRate", "Payable", "Tax"};

  public FormulaDemoView() {
    Handsontable handsontable = new Handsontable(createJsonArray(SAMPLE_JSON_DATA));
    handsontable.setId("hot1");
    Settings settings = new Settings();
    settings.setColHeaders(COL_HEADERS);
    List<Column> columns = new ArrayList<>();
    columns.add(getNumericColumn("0.0,00"));
    columns.add(getNumericColumn("0.0,00"));
    columns.add(getNumericColumn("0.0,00"));
    columns.add(getNumericColumn("0.0,00"));
    settings.setColumns(columns);
    handsontable.setSettings(settings);

    add(handsontable);
  }

  private Column getNumericColumn(String format) {
    NumericFormat nf = new NumericFormat(format);
    nf.setCulture("de-DE");
    Column column = new Column();
    column.setType("numeric");
    column.setNumericFormat(nf);
    return column;
  }
}
