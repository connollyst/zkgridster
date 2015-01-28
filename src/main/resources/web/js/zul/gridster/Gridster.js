zul.gridster.Gridster = zk.$extends(zk.Widget, {
    $define: {
        gridster: function (g) {
            var n = this.$n();
            if (n)
                n.gridster = g;
        },
        widgetMargins: null,
        widgetBaseDimensions: null,
        extraRows: null,
        extraCols: null,
        minRows: null,
        minCols: null,
        maxCols: null,
        maxSizeX: null
    },
    bind_: function () {
        this.$supers(zul.gridster.Gridster, 'bind_', arguments);
        var config = {
            widget_margins: this._widgetMargins,
            widget_base_dimensions: this._widgetBaseDimensions,
            extra_rows: this._extraRows,
            extra_cols: this._extraCols,
            min_rows: this._minRows,
            min_cols: this._minCols,
            max_cols: this._maxCols,
            max_size_x: this._maxSizeX
        };
        var g = $('#' + this.uuid + ' ul').gridster(config).data('gridster');
        this.setGridster(g);
    },
    insertChildHTML_: function (child, before, desktop) {
        var ben, html = child.redrawHTML_();
        var sizex = child._sizex,
            sizey = child._sizey,
            col = child._col,
            row = child._row;
        this._gridster.add_widget(html, sizex, sizey, col, row);
        // TODO support 'before'
        child.bind(desktop);
    },
    removeChildHTML_: function (child, ignoreDom) {
        this._gridster.remove_widget(child);
    },
    domClass_: function (no) {
        var classes = this.$supers("domClass_", no) || '';
        if (classes) {
            classes += ' ';
        }
        classes += 'gridster';
        return classes.trim();
    }
});