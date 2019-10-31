import Handsontable from "./dist/handsontable.full.min.js";

function handsontableRenderer(instance, td, row, col, prop, value, cellProperties) {
  var renderer;

  switch (cellProperties.type) {
    case "autocomplete":
      renderer = Handsontable.renderers.AutocompleteRenderer;
      break;
    case "base":
      renderer = Handsontable.renderers.BaseRenderer;
      break;
    case "checkbox":
      renderer = Handsontable.renderers.CheckboxRenderer;
      break;
    case "date":
      renderer = Handsontable.renderers.DateRenderer;
      break;
    case "dropdown":
      renderer = Handsontable.renderers.DropdownRenderer;
      break;
    case "html":
      renderer = Handsontable.renderers.HtmlRenderer;
      break;
    case "numeric":
      renderer = Handsontable.renderers.NumericRenderer;
      break;
    case "password":
      renderer = Handsontable.renderers.PasswordRenderer;
      break;
    case "text":
      renderer = Handsontable.renderers.TextRenderer;
      break;
    case "time":
      renderer = Handsontable.renderers.TimeRenderer;
      break;
    default:
      renderer = Handsontable.renderers.TextRenderer;
  }

  renderer.apply(this, arguments);

  if (cellProperties.bold) {
    td.style.fontWeight = 'bold';
  }
  if (cellProperties.italic) {
    td.style.fontStyle = 'italic';
  }

  var textDecoration = '';
  if (cellProperties.strikethrough) {
    textDecoration = 'line-through ';
  }
  if (cellProperties.underscore) {
    textDecoration += 'underline';
  }
  td.style.textDecoration = textDecoration;

  if (cellProperties.border) {
    td.style.border = '1px solid black';
  }
}

// maps function to lookup string
Handsontable.renderers.registerRenderer('handsontableRenderer', handsontableRenderer);

function createHandsontable(container, language, data) {
  var json = data ? JSON.parse(data) : null;
  if (container.$handsontable) {
    return;
  }

  if (!Handsontable.languages.getLanguageDictionary(language)) {
    language = 'en-US';
  }

  var hot = new Handsontable(container, {
    licenseKey: 'non-commercial-and-evaluation',
    data: json,
    rowHeaders: true,
    colHeaders: true,
    mergeCells: true,
    formulas: true,
    language: language,
    cells: function (row, col) {
      return {
        renderer: "handsontableRenderer"
      };
    },
    afterGetColHeader: function (col, TH) {
      if (!this.headerClassNames)
        return;

      if (this.headerClassNames[col])
        if (!Handsontable.dom.hasClass(TH, this.headerClassNames[col])) {
          Handsontable.dom.addClass(TH, this.headerClassNames[col]);
        }
    }
  });

  container.$handsontable = hot;

  hot.toggleCellBooleanMeta = function (key, selection, clickEvent) {
    var hot = this;
    setTimeout(function () {
      var cellMeta = hot.getCellMeta(selection[0].start.row, selection[0].start.col);
      var newValue;
      if (cellMeta[key])
        newValue = false;
      else
        newValue = true;

      selection.forEach(function (sel) {
        for (var row = sel.start.row; row <= sel.end.row; row++)
          for (var col = sel.start.col; col <= sel.end.col; col++)
            hot.setCellMeta(row, col, key, newValue);
      });

      hot.updateSettings({});
    }, 0);
  };

  hot.getSelection = function () {
    var selected = this.getSelected();
    var selection = [];

    for (var index = 0; index < selected.length; index += 1) {
      var item = selected[index];
      var startRow = Math.min(item[0], item[2]);
      var endRow = Math.max(item[0], item[2]);
      var startCol = Math.min(item[1], item[3]);
      var endCol = Math.max(item[1], item[3]);

      selection[index] = { start: { row: startRow, col: startCol }, end: { row: endRow, col: endCol } };
    }

    return selection;
  }

  hot.getCurrentCell = function () {
    var selected = this.getSelectedRange();
    return selected[selected.length - 1].highlight;
  }

  hot.processShortKeys = function (event) {
    try {
      if (event.ctrlKey && event.key == 'b') // Bold
        this.toggleCellBooleanMeta('bold', this.getSelection())
      else if (event.ctrlKey && event.key == 'i') // Italic
        this.toggleCellBooleanMeta('italic', this.getSelection())
      else if (event.ctrlKey && event.key == 'u') // Underscore
        this.toggleCellBooleanMeta('underscore', this.getSelection())
      else if (event.ctrlKey && event.key == '-') // Strike-through
        this.toggleCellBooleanMeta('strikethrough', this.getSelection())
      else if (event.ctrlKey && event.key == '[') // Border
        this.toggleCellBooleanMeta('border', this.getSelection())
      else if (event.ctrlKey && event.key == 'r') // Insert row after
        this.alter('insert_row', this.getCurrentCell().row + 1)
      else if (event.ctrlKey && event.shiftKey && event.key == 'R') // Insert row before
        this.alter('insert_row', this.getCurrentCell().row)
      else if (event.ctrlKey && event.key == 'o') // Insert column after
        this.alter('insert_col', this.getCurrentCell().col + 1)
      else if (event.ctrlKey && event.shiftKey && event.key == 'O') // Insert column before
        this.alter('insert_col', this.getCurrentCell().col)
      else if (event.ctrlKey && event.key == '9') // Remove row
        this.alter('remove_row', this.getCurrentCell().row)
      else if (event.ctrlKey && event.key == '0') // Remove column
        this.alter('remove_col', this.getCurrentCell().col)
    } catch (err) {
      console.log(err.message);
    }
  }

  hot.updateContextMenu = function (language) {
    var hot = this;
    i18n.locale = language;
    hot.updateSettings({
      contextMenu: {
        items: {
          'row_above': 'row_above',
          'row_below': 'row_below',
          'remove_row': 'remove_row',
          'space1': '---------',
          'col_left': 'col_left',
          'col_right': 'col_right',
          'remove_col': 'remove_col',
          'space2': '---------',
          'mergeCells': 'mergeCells',
          'space3': '---------',
          'bold': {
            key: 'bold',
            name: i18n`Bold`,
            callback: hot.toggleCellBooleanMeta
          },
          'italic': {
            key: 'italic',
            name: i18n`Italic`,
            callback: hot.toggleCellBooleanMeta
          },
          'strikethrough': {
            key: 'strikethrough',
            name: i18n`Strike through`,
            callback: hot.toggleCellBooleanMeta
          },
          'underscore': {
            key: 'underscore',
            name: i18n`Underscore`,
            callback: hot.toggleCellBooleanMeta
          },
          'border': {
            key: 'border',
            name: i18n`Border`,
            callback: hot.toggleCellBooleanMeta
          },
        }
      },
      beforeKeyDown: hot.processShortKeys
    });
  }

  hot.updateContextMenu(language);

  hot.setData = function (data) {
    var json = JSON.parse(data);
    this.updateSettings({
      data: json
    });
  }

  hot.retrieveData = function (callId) {
    var data = this.getSourceData();
    var str = stringify(data);
    container.$server.receiveJsonArray(callId, str);
  }

  hot.retrieveDataAsArray = function (callId) {
    var data = this.getData();
    var str = stringify(data);
    container.$server.receiveListOfStringArray(callId, str);
  }

  hot.setCellsMeta = function (cellsMetaString) {
    var cellsMeta = JSON.parse(cellsMetaString);
    cellsMeta.forEach(function (cellMeta) {
      hot.setCellMetaObject(cellMeta.row, cellMeta.col, cellMeta);
    });
    this.updateSettings({});
  }

  hot.retrieveCellsMeta = function (callId) {
    var cellsMeta = this.getCellsMeta();
    var str = stringify(cellsMeta);
    container.$server.receiveCellsMeta(callId, str);
  }

  hot.setSettings = function (settings) {
    var settingsObject = JSON.parse(settings);
    if (settingsObject.language) {
      if (!Handsontable.languages.getLanguageDictionary(settingsObject.language)) {
        settingsObject.language = 'en-US';
      }
      this.updateContextMenu(settingsObject.language);
    }
    this.updateSettings(settingsObject);
  }

  hot.retrieveSettings = function (callId) {
    var settings = this.getSettings();
    var str = stringify(settings.__proto__);
    container.$server.receiveSettings(callId, str);
  }

  hot.retrieveDataAtCell = function (row, col, callId) {
    var value = this.getDataAtCell(row, col);
    container.$server.receiveString(callId, value);
  }

  hot.setNestedHeaders = function (nestedHeadersText) {
    var nestedHeadersArray = JSON.parse(nestedHeadersText);
    this.updateSettings({ nestedHeaders: nestedHeadersArray });
  }

  hot.setHeaderClassNames = function (classNamesStr) {
    hot.headerClassNames = JSON.parse(classNamesStr);
    this.updateSettings({});
  }
}

function stringify(obj) {
  var cache = [];
  return JSON.stringify(obj, function (key, value) {
    if (key == 'instance')
      return;
    if (typeof value === 'object' && value !== null) {
      if (cache.indexOf(value) !== -1) {
        // Duplicate reference found
        try {
          // If this value does not reference a parent it can be deduped
          return JSON.parse(JSON.stringify(value));
        } catch (error) {
          // discard key if value cannot be deduped
          return;
        }
      }
      // Store value in our collection
      cache.push(value);
    }
    return value;
  });
}

// ==== Internationalization ====
function i18n(template) {
  var localeDb = i18n.db[i18n.locale] || i18n.db['en-US']
  for (var
    info = localeDb[template.join('\x01')],
    out = [info.t[0]],
    i = 1, length = info.t.length; i < length; i++
  ) out[i] = arguments[1 + info.v[i - 1]] + info.t[i];
  return out.join('');
};

i18n.locale = 'en-US';
i18n.db = {};

i18n.set = locale => (tCurrent, ...rCurrent) => {
  const key = tCurrent.join('\x01');
  let db = i18n.db[locale] || (i18n.db[locale] = {});
  db[key] = {
    t: tCurrent.slice(),
    v: rCurrent.map((value, i) => i)
  };
  const config = {
    for: other => (tOther, ...rOther) => {
      db = i18n.db[other] || (i18n.db[other] = {});
      db[key] = {
        t: tOther.slice(),
        v: rOther.map((value, i) => rCurrent.indexOf(value))
      };
      return config;
    }
  };
  return config;
};

i18n.set('en-US')`Bold`
  .for('de-DE')`Fett`;

i18n.set('en-US')`Italic`
  .for('de-DE')`Kursiv`;

i18n.set('en-US')`Strike through`
  .for('de-DE')`Durchgestrichen`;

i18n.set('en-US')`Underscore`
  .for('de-DE')`Unterstreichen`;

i18n.set('en-US')`Border`
  .for('de-DE')`Rand`;

// ==============================
window.createHandsontable = createHandsontable;