var data = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda'],
  ['2017', -5, '', 12, 13],
  ['2018', '', -11, 14, 13],
  ['2019', '', 15, -12, 'readOnly']
];

function handsontableRenderer(instance, td, row, col, prop, value, cellProperties) {
  Handsontable.renderers.TextRenderer.apply(this, arguments);

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

function createHandsontbale(container) {
  // maps function to lookup string
  Handsontable.renderers.registerRenderer('handsontableRenderer', handsontableRenderer);

  var hot = new Handsontable(container, {
    licenseKey: 'non-commercial-and-evaluation',
    data: data,
    rowHeaders: true,
    colHeaders: true,
    mergeCells: true,
    cells: function (row, col) {
      var cellProperties = {};
      var data = this.instance.getData();

      if (row === 0 || data[row] && data[row][col] === 'readOnly') {
        cellProperties.readOnly = true; // make cell read-only if it is first row or the text reads 'readOnly'
      }
      cellProperties.renderer = "handsontableRenderer"; // uses lookup map

      return cellProperties;
    }
  });

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

}
