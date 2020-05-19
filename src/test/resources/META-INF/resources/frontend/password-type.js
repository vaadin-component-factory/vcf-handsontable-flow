/**
 * @param {Object} instance Handsontable instance
 * @param {Element} td Table cell where to render
 * @param {Number} row
 * @param {Number} col
 * @param {String|Number} prop Row object property name
 * @param value Value to render (remember to escape unsafe HTML before inserting to DOM!)
 * @param {Object} cellProperties Cell properties (shared by cell renderer and editor)
 */
function passwordRenderer(instance, td, row, col, prop, value, cellProperties, ...args) {
    if (cellProperties.valid !== false) {
        const displayValue = value ? `${String(value).substring(0, 2)}***` : '';
        td.innerHTML = `<span style='color: red'>${displayValue}</span>`
    } else {
        td.innerHTML = `ERROR`;
    }
}

/**
 * @param {*} value - Value of edited cell
 * @param {Function} callback - Callback called with validation result
 */
function passwordValidator(value, callback) {
    const valid = Boolean(value && String(value).length > 5);
    callback(valid);
}

class PasswordEditor extends Handsontable.editors.TextEditor {
    createElements() {
        super.createElements();

        this.TEXTAREA = this.hot.rootDocument.createElement('input');
        this.TEXTAREA.setAttribute('type', 'password');
        this.TEXTAREA.className = 'handsontableInput';
        this.TEXTAREA.setAttribute('data-hot-input', ''); // Makes the element recognizable by HOT as its own component's element.
        this.textareaStyle = this.TEXTAREA.style;
        this.textareaStyle.width = 0;
        this.textareaStyle.height = 0;

        Handsontable.dom.empty(this.TEXTAREA_PARENT);
        this.TEXTAREA_PARENT.appendChild(this.TEXTAREA);
    }
}

Handsontable.cellTypes.registerCellType('password', {
    renderer: passwordRenderer,
    editor: PasswordEditor,
    validator: passwordValidator,
    // You can add additional options to the cell type based on Handsontable settings
    className: 'password-cell',
    allowInvalid: true,
    // Or you can add custom properties which will be accessible in `cellProperties`
    editStatus: 'unedited',
});