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

function createHandsontbale(container, data) {
  var json = data ? JSON.parse(data) : null;
  if (container.$handsontable) {
    return;
  }

  var hot = new Handsontable(container, {
    licenseKey: 'non-commercial-and-evaluation',
    data: json,
    rowHeaders: true,
    colHeaders: true,
    mergeCells: true,
    formulas: true,
    cells: function (row, col) {
      var cellProperties = {};
      var data = this.instance.getData();

      cellProperties.renderer = "handsontableRenderer";

      return cellProperties;
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
          name: 'Bold',
          callback: hot.toggleCellBooleanMeta
        },
        'italic': {
          key: 'italic',
          name: 'Italic',
          callback: hot.toggleCellBooleanMeta
        },
        'strikethrough': {
          key: 'strikethrough',
          name: 'Strike through',
          callback: hot.toggleCellBooleanMeta
        },
        'underscore': {
          key: 'underscore',
          name: 'Underscore',
          callback: hot.toggleCellBooleanMeta
        },
        'border': {
          key: 'border',
          name: 'Border',
          callback: hot.toggleCellBooleanMeta
        },
      }
    }
  });

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
    this.updateSettings(settingsObject);
  }

  hot.setNestedHeaders = function (nestedHeadersText) {
    var nestedHeadersArray = JSON.parse(nestedHeadersText);
    this.updateSettings({ nestedHeaders: nestedHeadersArray });
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
